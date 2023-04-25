package jpabook.jpashop.response;

import jpabook.jpashop.domain.item.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemResponse {

    private final Long itemId;
    private final String name;
    private final int price;
    private final int stockQuantity;

    public static ItemResponse toItem(Item item) {
        return ItemResponse.builder()
                .itemId(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
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
