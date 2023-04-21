package jpabook.jpashop.response;

import jpabook.jpashop.domain.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AddressResponse {

    private final String city;
    private final String street;
    private final String zipcode;

    public static AddressResponse toAddress(Address address) {
        return AddressResponse.builder()
                .city(address.getCity())
                .street(address.getStreet())
                .zipcode(address.getZipcode())
                .build();
    }

    @Builder
    public AddressResponse(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
