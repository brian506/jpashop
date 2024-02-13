package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

    public List<Order> findAllBySpring(OrderSearch orderSearch){

       return em.createQuery("select o from Order o join o.member m" +
                "where o.status = :status "+
                "and m.name like :name", Order.class)
               .setParameter("stauts",orderSearch.getOrderStatus())
               .setParameter("name",orderSearch.getMemberName())
               .setMaxResults(100) // 최대 100건만 가능
               .getResultList();
    }
}
