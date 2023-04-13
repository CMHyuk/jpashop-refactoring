package jpabook.jpashop.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemResponse {

    private final Long itemId;
    private final String name;
    private final int price;
    private final int stockQuantity;

    public static ItemResponse toEntity(Long itemId, String name, int price, int stockQuantity) {
        return ItemResponse.builder()
                .itemId(itemId)
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }

    @Builder
    public ItemResponse(Long itemId, String name, int price, int stockQuantity) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
