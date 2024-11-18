package com.companies.enterprise.entities;

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


}
