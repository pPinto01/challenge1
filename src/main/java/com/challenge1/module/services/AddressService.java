package com.challenge1.module.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.challenge1.module.models.Address;
import com.challenge1.module.utils.facade.AddressComplete;

@Service
public class AddressService {
    private AddressComplete facade;

    public AddressService() {
        this.facade = new AddressComplete();
    }

    public List<Address> completeAddress(Address address) {
        return facade.completeAddress(address);
    }

}
