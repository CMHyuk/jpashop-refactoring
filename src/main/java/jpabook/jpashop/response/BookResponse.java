package jpabook.jpashop.response;

import lombok.Getter;

@Getter
public class BookResponse extends ItemResponse {

    private String author;
    private String isbn;

    public BookResponse(ItemResponse item) {
        super(item.getItemId(), item.getName(), item.getPrice(), item.getStockQuantity());
    }
}
