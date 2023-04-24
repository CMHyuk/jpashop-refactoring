package jpabook.jpashop.exception;

public class MemberDuplicate extends RuntimeException {

    private static final String MESSAGE = "중복된 회원입니다.";

    public MemberDuplicate() {
        super(MESSAGE);
    }
}
