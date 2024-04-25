package com.challenge1.module.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testing Address entity")
class AddressTest {
    
    @Test
    @DisplayName("Testing address build")
    void testTask() {
        Address address = new Address.Builder()
                                     .partialStreet("12345")
                                     .street("101 MAIN STREET")
                                     .city("SAN FRANCISCO")
                                     .state("CA")
                                     .postalCode("20101")
                                     .build();

        assertEquals("12345", address.getPartialStreet());
        assertEquals("101 MAIN STREET", address.getStreet());
        assertEquals("SAN FRANCISCO", address.getCity());
        assertEquals("CA", address.getState());
        assertEquals("20101", address.getPostalCode());
    }
}
