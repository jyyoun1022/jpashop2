package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
    public List<Order> findAllByString(OrderSearch orderSearch){

        String jpql = "select o from Order o join o.member m";
        boolean isFirstCondition = true;
        //주문 상태 검색
        if(orderSearch.getOrderStatus() != null){
            if(isFirstCondition){
                jpql += " where";
            }else{
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        // 회원 이름 검색
        if(StringUtils.hasText(orderSearch.getMemberName())){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition =false;
            }else{
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Order> orderTypedQuery = em.createQuery(jpql, Order.class)
                .setMaxResults(10000);

        if(orderSearch.getOrderStatus() != null){
            orderTypedQuery = orderTypedQuery.setParameter("status",orderSearch.getOrderStatus());
        }
        if(StringUtils.hasText(orderSearch.getMemberName())){
            orderTypedQuery = orderTypedQuery.setParameter("name",orderSearch.getMemberName());
        }

        List<Order> resultList = orderTypedQuery.getResultList();
        return resultList;
    }

    /**
     * JPA Criteria
     */
    public List<Order> findAllByCriteria(OrderSearch orderSearch){


    }
}
