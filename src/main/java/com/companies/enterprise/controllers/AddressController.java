package com.companies.enterprise.controllers;


import com.companies.enterprise.services.AddressService;
import com.companies.enterprise.validation.RequestAddress;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/endereco")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @PostMapping
    public ResponseEntity saveAddress(@RequestBody @Valid RequestAddress data) {
        return addressService.saveAddress(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAddress(@PathVariable long id, @RequestBody @Valid RequestAddress data) {
        return addressService.updateAddress(id, data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddress(@PathVariable Long id) {
        return addressService.deleteAddress(id);
    }

}
     
