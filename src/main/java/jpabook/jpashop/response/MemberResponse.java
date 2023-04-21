package jpabook.jpashop.response;

import jpabook.jpashop.domain.Member;
import lombok.Builder;
import lombok.Getter;

/**
 * 이름
 * 도시
 * 주소
 * 우편번호
 */
@Getter
public class MemberResponse {

    private final Long id;
    private final String name;
    private final AddressResponse address;

    public static MemberResponse toMember(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .address(AddressResponse.toAddress(member.getAddress()))
                .build();
    }

    @Builder
    public MemberResponse(Long id, String name, AddressResponse address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
