package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String ID;

    private int password;

    @Embedded // 내장 타입
    private Address address;

    // order와 일대다관계
    @OneToMany(mappedBy = "member") // Order가 주인이다라는 것
    private List<Order> orders = new ArrayList<>();
    // order와 일대다 양방향 관계이므로 회원 엔티티에 컬랙션을 추가했다
    // 한명의 회원이 여러개의 주문을 할 수 있으므로 일대다관계
}
