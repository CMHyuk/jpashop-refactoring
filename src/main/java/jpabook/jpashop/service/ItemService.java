package jpabook.jpashop.service;

import jpabook.jpashop.controller.BookForm;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.ItemNotFound;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(BookForm form) {
        Book book = Book.builder()
                .name(form.getName())
                .price(form.getPrice())
                .stockQuantity(form.getStockQuantity())
                .author(form.getAuthor())
                .isbn(form.getIsbn())
                .build();

        itemRepository.save(book);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotFound::new);

        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
    }

    public List<ItemResponse> findItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(i -> ItemResponse.toItem(i.getId(), i.getName(),
                        i.getPrice(), i.getStockQuantity()))
                .collect(toList());
    }

    public ItemResponse findOne(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotFound::new);

        return ItemResponse.toItem(item.getId(), item.getName(), item.getPrice(), item.getStockQuantity());
    }

}
