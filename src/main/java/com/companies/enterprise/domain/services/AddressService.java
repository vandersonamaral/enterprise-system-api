package com.companies.enterprise.domain.services;

import com.companies.enterprise.domain.entities.Address;
import com.companies.enterprise.infrastructure.repositories.AddressRepository;
import com.companies.enterprise.dtos.in.RequestAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;


    public ResponseEntity getAllAddresses() {
        if (addressRepository.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressRepository.findAll());
    }

    public ResponseEntity saveAddress( RequestAddress data) {
        Address address = new Address(data);
        addressRepository.save(address);
        return ResponseEntity.ok(address);
    }


    public ResponseEntity updateAddress( long id, RequestAddress data) {

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

    public ResponseEntity deleteAddress( Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            addressRepository.delete(address.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
