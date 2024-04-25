package com.challenge1.module.utils.chain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.challenge1.module.models.Address;

@Service
public abstract class AddressAbstractHandler {
    protected AddressAbstractHandler nextHandler;

    public void setNextHandler(AddressAbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract List<Address> completeAddress(Address address);

    public abstract boolean supportsType(String handlerType);
}
