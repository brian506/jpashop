package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    // 실무에서 모든 연관관계는 지연로딩(LAZY)로 설정
    //~ToOne 관계는 기본이 즉시로딩이므로 직접 지연로딩으로 설정
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // STRING이 순서에 연연하지 않음
    private DeliveryStatus status; // READY,COMP

}
