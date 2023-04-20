package jpabook.jpashop.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jpabook.jpashop.domain.Order;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class OrderResponse {

    private Long orderId;
    private String name;
    private List<OrderItemResponse> orderItem;
    private int count;
    private Enum orderStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime localDateTime;

    public static List<OrderResponse> toOrders(List<Order> orders) {
        return orders.stream().map(o -> OrderResponse.builder()
                        .orderId(o.getId())
                        .name(o.getMember().getName())
                        .orderItem(o.getOrderItems().stream().map(i -> OrderItemResponse.builder()
                                        .itemName(i.getItem().getName())
                                        .count(i.getCount())
                                        .price(i.getItem().getPrice())
                                        .build())
                                .collect(toList()))
                        .orderStatus(o.getStatus())
                        .localDateTime(o.getOrderDate())
                        .build())
                .collect(toList());
    }

    @Builder
    public OrderResponse(Long orderId, String name, List<OrderItemResponse> orderItem, int count, Enum orderStatus, LocalDateTime localDateTime) {
        this.orderId = orderId;
        this.name = name;
        this.orderItem = orderItem;
        this.count = count;
        this.orderStatus = orderStatus;
        this.localDateTime = localDateTime;
    }
}
