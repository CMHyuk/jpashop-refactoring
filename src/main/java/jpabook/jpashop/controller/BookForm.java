package jpabook.jpashop.controller;

import jpabook.jpashop.response.BookResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BookForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public static BookForm toBook(BookResponse bookResponse) {
        return BookForm.builder()
                .id(bookResponse.getItemId())
                .name(bookResponse.getName())
                .price(bookResponse.getPrice())
                .stockQuantity(bookResponse.getStockQuantity())
                .author(bookResponse.getAuthor())
                .isbn(bookResponse.getIsbn())
                .build();
    }

    @Builder
    public BookForm(Long id, String name, int price, int stockQuantity, String author, String isbn) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }
}
