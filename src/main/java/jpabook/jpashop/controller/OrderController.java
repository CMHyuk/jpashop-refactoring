package jpabook.jpashop.controller;

import jpabook.jpashop.request.OrderRequest;
import jpabook.jpashop.request.OrderSearch;
import jpabook.jpashop.response.ItemResponse;
import jpabook.jpashop.response.MemberResponse;
import jpabook.jpashop.response.OrderResponse;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
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
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<MemberResponse> members = memberService.findMembers();
        List<ItemResponse> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@ModelAttribute OrderRequest request) {
        orderService.order(request.getMemberId(), request.getItemId(), request.getCount());
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute OrderSearch orderSearch, Model model) {
        List<OrderResponse> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
