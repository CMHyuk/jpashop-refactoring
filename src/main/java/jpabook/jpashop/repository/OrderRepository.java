package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("select o from Order o join o.member m" +
            " where (:status is null or o.status = :status)" +
            " and (:name is null or m.name like %:name%)")
    List<Order> findAllByString(@Param("status") OrderStatus status, @Param("name") String name);

}
