 상품 도메인

package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 싱글테이블( 한테이블에 다 떄려박는다)
@DiscriminatorColumn(name = "dtype") // bottom,tp,shoes을 한번에 묶어준다
@Getter @Setter
public abstract class Item {
    //추상 클래스인 이유(구체적이지 않은 클래스)
    //Item은 Book, Album, Movie의 공통 기능을 코드로 명세화해놓기 위해 사용한 설계도 같은 녀석이다.
    //설계도니까 굳이 객체 인스턴스로 만들어 사용할 일이 없기 때문에 그 부분을 명확히 하기 위해 추상 클래스로 만들었다.

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    //orderItem 객체 선언이 없는 이유는 단방향이기 때문
    //모든 주문상품을 볼때 상품들을 각각 볼 필요가 없음

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    @OneToOne(mappedBy = "item",fetch = FetchType.LAZY)
    private Review review;

}
