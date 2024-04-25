package com.challenge1.module.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge1.module.models.Address;
import com.challenge1.module.utils.facade.AddressComplete;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing AddressService")
public class AddressServiceTest {

    @Mock
    private AddressComplete facade;

    @InjectMocks
    private AddressService addressService;

    @Test
    @DisplayName("Testing completeAddress method.")
    void testSearchTimelineMethod() {
        List<Address> expectedResponse = new ArrayList<>();
        
        Address address = new Address.Builder()
                                     .partialStreet("12345")
                                     .build();

        Address addressResponse = new Address.Builder()
                                             .partialStreet("12345")
                                             .street("101 MAIN STREET")
                                             .city("SAN FRANCISCO")
                                             .state("CA")
                                             .postalCode("20101")
                                             .build();

        expectedResponse.add(addressResponse);
       
        when(facade.completeAddress(address)).thenReturn(expectedResponse);

        List<Address> response = addressService.completeAddress(address);

        assertThat(response).usingRecursiveComparison()
                            .isEqualTo(expectedResponse);
    }

}
