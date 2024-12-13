package com.companies.enterprise.controllers;

import com.companies.enterprise.entities.Address;
import com.companies.enterprise.repositories.AddressRepository;
import com.companies.enterprise.validation.RequestAddress;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    public ResponseEntity getAllAddresses() {
        if (addressRepository.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressRepository.findAll());
    }

    @PostMapping
    public ResponseEntity saveAddress(@RequestBody @Valid RequestAddress data) {
        Address address = new Address(data);
        addressRepository.save(address);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAddress(@PathVariable long id, @RequestBody @Valid RequestAddress data) {

        Optional<Address> optionalAddress = addressRepository.findById((id));
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setCity(data.city());
            address.setCountry(data.country());
            address.setNumber(data.number());
            address.setFu(data.fu());
            address.setPostal_code(data.postal_code());
            address.setStreet(data.street());

            return ResponseEntity.ok(addressRepository.save(address));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddress(@PathVariable Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            addressRepository.delete(address.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
     
