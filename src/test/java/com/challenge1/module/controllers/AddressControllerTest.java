package com.challenge1.module.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.challenge1.module.models.Address;
import com.challenge1.module.services.AddressService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddressControllerTest {

    @MockBean
    private AddressService addressService;

    @Autowired
    private MockMvc mvc;

    private static Address address;

    @BeforeAll
    public static void setUp() {
        address = new Address.Builder()
                .partialStreet("12345")
                .build();
    }

    @Test
    @DisplayName("Testing AddressController GET method")
    void testGetAddressController() throws Exception {
        Address addressResponse = new Address.Builder()
                .partialStreet("12345")
                .build();
        ArgumentCaptor<Address> argumentCaptor = ArgumentCaptor.forClass(Address.class);
        List<Address> list = new ArrayList<>();
        list.add(addressResponse);

        when(addressService.completeAddress(address)).thenReturn(list);

        mvc.perform(get("/challenge/address/auto_complete")
            .param("partial_address", "12345")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Basic ZGVtby11c2VybmFtZTpkZW1vLXBhc3N3b3Jk"))
            .andExpect(status().isOk());

        verify(addressService).completeAddress(argumentCaptor.capture());
        Address captureResponse = argumentCaptor.getValue();

        assertThat(captureResponse).usingRecursiveComparison().isEqualTo(addressResponse);
    }
}
