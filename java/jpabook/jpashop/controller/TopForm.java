package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TopForm {

    private Long id; // 상품 수정이 있으므로 id값을 받음

    private String name;
    private int price;
    private int stockQuantity;

    private String outer;
    private String sweats;

}
