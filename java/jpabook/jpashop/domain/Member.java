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

    private String email;

    private int password;

    @Embedded // 내장 타입
    private Address address;

    @OneToMany(mappedBy = "member") // Order가 주인이다라는 것
    private List<Order> orders = new ArrayList<>();
    // order와 일대다 양방향 관계이므로 회원 엔티티에 컬랙션을 추가했다

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();
    //컬렉션은 필드에서 바로 초기화 하는 것이 안전하다



}
