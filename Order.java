package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // 테이블 명을 따로 정한 건 db안에서 order와 같은 이름이 있어서
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne // member와 다대일관계
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order") // orderItem(주인)과 일대다 관계
    // 하나의 상품을 여러개의 상품으로 구매할 수 있으므로
    private List<OrderItem> orderItems = new ArrayList<>();
    // 일대다 관계이므로 하인 위치인 order클래스에 컬렉션 도임

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) //STRING이 순서에 관계 없으므로 STRING만 쓸것
    private OrderStatus status; // 주문상태[ORDER,CANCEL]
}
