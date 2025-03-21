package com.companies.enterprise.domain.entities;

import com.companies.enterprise.dtos.in.RequestAddress;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String country;
    private String fu;
    private Integer number;
    private String postal_code;
    private String street;


    public Address(RequestAddress requestAddress) {
        this.city = requestAddress.city();
        this.country = requestAddress.country();
        this.fu = requestAddress.fu();
        this.number = requestAddress.number();
        this.postal_code = requestAddress.postal_code();
        this.street = requestAddress.street();

    }
}
