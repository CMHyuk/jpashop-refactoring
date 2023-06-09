package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.ItemNotFound;
import jpabook.jpashop.exception.MemberNotFound;
import jpabook.jpashop.exception.OrderNotFound;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.request.OrderSearch;
import jpabook.jpashop.response.OrderItemResponse;
import jpabook.jpashop.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        //엔티티 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);

        Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotFound::new);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFound::new);
        //주문 취소
        order.cancel();
    }

    //검색
    public List<OrderResponse> findOrders(OrderSearch orderSearch) {
        List<Order> orders = orderRepository.findAllByString(orderSearch.getOrderStatus(), orderSearch.getMemberName());
        return OrderResponse.toOrders(orders);
    }
}
