package jpabook.jpashop.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {

    private Long orderId;
    private String name;
    private List<OrderItemResponse> orderItem;
    private int count;
    private Enum orderStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime localDateTime;

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
