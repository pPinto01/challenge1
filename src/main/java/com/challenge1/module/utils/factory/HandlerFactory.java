package com.challenge1.module.utils.factory;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.challenge1.module.utils.chain.AddressAbstractHandler;
import com.challenge1.module.utils.chain.SmartyCompletionHandler;
import com.challenge1.module.utils.chain.LobCompletionHandler;

@Service
public class HandlerFactory {
    private static final Set<AddressAbstractHandler> HANDLERS = new HashSet<>();

    static {
        HANDLERS.add(new LobCompletionHandler());
        HANDLERS.add(new SmartyCompletionHandler());
    }

    public static AddressAbstractHandler createHandler(String handlerType) {
        for (AddressAbstractHandler handler : HANDLERS) {
            if (handler.supportsType(handlerType)) {
                return handler;
            }
        }
        return null;
    }
}
