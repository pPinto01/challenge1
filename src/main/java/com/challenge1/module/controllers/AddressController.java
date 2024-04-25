package com.challenge1.module.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge1.module.models.Address;
import com.challenge1.module.services.AddressService;

@RestController
@RequestMapping({ "/challenge/address/" })
public class AddressController {
    private AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping("/auto_complete")
    public ResponseEntity<List<Address>> completeAddress(@RequestParam(required = true, name = "partial_address") String partialAddress,
                                                         @RequestParam(required = false) String city,
                                                         @RequestParam(required = false) String state,
                                                         @RequestParam(required = false) String zipCode) {
        
        Address address = new Address.Builder()
                                     .partialStreet(partialAddress)
                                     .city(city)
                                     .state(state)
                                     .postalCode(zipCode)
                                     .build();
        
        List<Address> completedAddress = service.completeAddress(address);
        if (completedAddress != null) {
            return ResponseEntity.ok(completedAddress);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
