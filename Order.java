package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}

