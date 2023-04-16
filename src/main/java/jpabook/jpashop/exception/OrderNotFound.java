package jpabook.jpashop.exception;

public class OrderNotFound extends RuntimeException {

    private static final String MESSAGE = "존재하지 않는 주문입니다.";

    public OrderNotFound() {
        super(MESSAGE);
    }
}
