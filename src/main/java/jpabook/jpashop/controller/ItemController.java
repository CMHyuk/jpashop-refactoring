package jpabook.jpashop.controller;

import jpabook.jpashop.response.BookResponse;
import jpabook.jpashop.response.ItemResponse;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        itemService.saveItem(form);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<ItemResponse> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        ItemResponse item = itemService.findOne(itemId);
        BookResponse bookResponse = new BookResponse(item);

        BookForm form = new BookForm();
        form.setId(bookResponse.getItemId());
        form.setName(bookResponse.getName());
        form.setPrice(bookResponse.getPrice());
        form.setStockQuantity(bookResponse.getStockQuantity());
        form.setAuthor(bookResponse.getAuthor());
        form.setIsbn(bookResponse.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/items";
    }
}





