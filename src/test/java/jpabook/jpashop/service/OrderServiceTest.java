package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","경기","123-123"));
        em.persist(member);

        Item book= new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        //when
        int orderCount =2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order order = orderRepository.findOne(orderId);

        assertEquals( OrderStatus.ORDER, order.getStatus(),"상품 주문시 상태는 ORDER");
        assertEquals(1,order.getOrderItems().size());
        assertEquals(10000*orderCount,order.getTotalPrice());
        assertEquals(8,book.getStockQuantity());

    }

    @Test
    public void 주문취소() throws Exception{
        //given

        //when

        //then


    }

    @Test
    public void 상품주문_재고수량초과() throws Exception{
        //given

        //when

        //then


    }
}