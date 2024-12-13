package com.companies.enterprise.dto;

import com.companies.enterprise.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private long id;
    private String city;
    private String country;
    private String fu;
    private Integer number;
    private String postal_code;
    private String street;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.city = address.getCity();
        this.country = address.getCountry();
        this.fu = address.getFu();
        this.number = address.getNumber();
        this.postal_code = address.getPostal_code();
        this.street = address.getStreet();
    }
}
