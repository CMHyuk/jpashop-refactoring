package jpabook.jpashop.domain.item;

import jpabook.jpashop.controller.BookForm;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class Book extends Item {

    private String author;
    private String isbn;

    public static Book build(BookForm form) {
        return Book.builder()
                .name(form.getName())
                .price(form.getPrice())
                .stockQuantity(form.getStockQuantity())
                .author(form.getAuthor())
                .isbn(form.getIsbn())
                .build();
    }

    @Builder
    public Book(String name, int price, int stockQuantity, String author, String isbn) {
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public void updateItem(BookForm form) {
        super.updateItem(form);
        this.setAuthor(form.getAuthor());
        this.setIsbn(form.getIsbn());
    }
}
