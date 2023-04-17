package jpabook.jpashop.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderItemResponse {

    private String itemName;
    private int price;
    private int count;

    @Builder
    public OrderItemResponse(String itemName, int price, int count) {
        this.itemName = itemName;
        this.price = price;
        this.count = count;
    }
}
