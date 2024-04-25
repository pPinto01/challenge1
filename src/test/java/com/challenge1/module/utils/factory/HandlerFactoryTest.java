package com.challenge1.module.utils.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import com.challenge1.module.utils.chain.AddressAbstractHandler;
import com.challenge1.module.utils.chain.LobCompletionHandler;
import com.challenge1.module.utils.chain.SmartyCompletionHandler;

@ExtendWith(MockitoExtension.class)
@Import({AddressAbstractHandler.class,
LobCompletionHandler.class,
SmartyCompletionHandler.class})
public class HandlerFactoryTest {

    @Test
    @DisplayName("test createHandler should return the class passed as parameter")
    void testCreateHandlerLob() {
        assertEquals(LobCompletionHandler.class, HandlerFactory.createHandler("Lob")
                                                                        .getClass());
        assertEquals(SmartyCompletionHandler.class, HandlerFactory.createHandler("Smarty")
                                                                           .getClass());
    }
}
