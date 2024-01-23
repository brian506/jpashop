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

    @OneToOne(mappedBy = "delivery") // order(주인)와 일대일 양방향관계
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // STRING이 순서에 연연하지 않음
    private Deliverystatus status; // READY,COMP

}
