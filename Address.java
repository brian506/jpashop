package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Address {

    private String city;
    private String street;

    //값 타입은 변경이 되면 안됨(생성자 필요)
    //값을 모두 초기화해서 변경 불가능한 클래스로 만든것
    protected Address(){
    }

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
}
