package com.challenge1.module.utils.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import com.challenge1.module.models.Address;
import com.challenge1.module.utils.chain.AddressAbstractHandler;
import com.challenge1.module.utils.chain.LobCompletionHandler;
import com.challenge1.module.utils.chain.SmartyCompletionHandler;
import com.challenge1.module.utils.factory.HandlerFactory;

@Component
public class AddressComplete {
    private AddressAbstractHandler handler;

    public AddressComplete() {
        buildChain();
    }

    private void buildChain() {
        handler = HandlerFactory.createHandler(LobCompletionHandler.TYPE);
        handler.setNextHandler(HandlerFactory.createHandler(SmartyCompletionHandler.TYPE));
    }

    public List<Address> completeAddress(Address address) {
        return handler.completeAddress(address);
    }
}
