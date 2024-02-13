package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jpabook.jpashop.domain.QOrderItem.orderItem;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // member와 다대일관계
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    //cascade는 orderItems에 있는 항목들을 테이블에 자동으로 전부 저장해준다
    //em.persist을 일일이 하지 않고 한번에 저장해준다
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태[ORDER,CANCEL]

    //연관관계 메서드// --> 주로 연관관계의 주인인 쪽 클래스에 작성

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
        // 양방향 연관관계를 메서드로 편입한 것
        // getOrders는 일대다 관계이므로
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
        // 오더로 딜리버리에 접근이 가능해야 하듯이
        // 딜리버리를 통해 이 오더에도 접근이 가능하도록 하는 것
    }

    // 생성 메서드 //
    // 주문 상품, 가격,수량 정보를 사용해서 주문상품 엔티티 생성
    public static Order createOrder(Member member , Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER); // 주문을 했을때
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    // 비즈니스 로직 //
    //주문 취소
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송 완료된 상품은 취소가 불가능합니다");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
        //orderItem 클래스에서 getItem().addStock(count)와 상응
    }

    // 조회 로직 // 
    // 전체 주문 가격 조회 // 
    public int getTotalPrice(){
        int totalPrice = 0;
        for (OrderItem orderItem :orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        //OrderItem 클래스에서 getTotalPrice()와 상응
        return totalPrice;
    }
}
