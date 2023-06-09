package jpabook.jpashop.exception;

public class ItemNotFound extends RuntimeException {

    private static final String MESSAGE = "존재하지 않는 상품입니다.";

    public ItemNotFound() {
        super(MESSAGE);
    }
}
