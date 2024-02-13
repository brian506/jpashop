package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //상품 저장
    public void save(Item item){
        if (item.getId() == null){
            em.persist(item);// 처음에 데이터가 없으므로 persist로 저장
        } else {
            em.merge(item); // 데이터가 있을땐 merge(업데이트 느낌)로 저장
        }
    }

    public Item findOne(Long id){ // 상품 하나 조회
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){ // 여러개 조회하는 건 jpql작성
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }
}
