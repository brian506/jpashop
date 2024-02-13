package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "order_Item")
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_Item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;


    //  생성 메서드 //
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); // 넘어온 수량만큼 재고 감소
        return orderItem;
    }

    //비즈니스 로직 //
    public void cancel() { // 주문을 취소했을때 재고가 다시 들어오므로 원복해줌
        getItem().addStock(count);
    }

    // 조회 로직 //
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
