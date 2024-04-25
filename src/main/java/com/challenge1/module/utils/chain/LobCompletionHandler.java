package com.challenge1.module.utils.chain;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openapitools.client.model.UsAutocompletions;
import org.openapitools.client.model.UsAutocompletionsWritable;
import org.springframework.stereotype.Component;

import com.challenge1.module.models.Address;
import com.lob.api.ApiClient;
import com.lob.api.ApiException;
import com.lob.api.Configuration;
import com.lob.api.auth.HttpBasicAuth;
import com.lob.api.client.UsAutocompletionsApi;

@Component
public class LobCompletionHandler extends AddressAbstractHandler {
    public static final String TYPE = "Lob";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Address> completeAddress(Address address) {

        ApiClient lobClient = Configuration.getDefaultApiClient();

        HttpBasicAuth basicAuth = (HttpBasicAuth) lobClient.getAuthentication("basicAuth");
        basicAuth.setUsername("live_pub_a15953d6b86c7028cbf7605ed360eee");
        
        UsAutocompletionsApi apiInstance = new UsAutocompletionsApi(lobClient);
        UsAutocompletionsWritable autoCompletionWritable = new UsAutocompletionsWritable();
        autoCompletionWritable.setAddressPrefix(address.getPartialStreet());
        autoCompletionWritable.setCity(address.getCity());
        autoCompletionWritable.setState(address.getState());
        autoCompletionWritable.setZipCode(address.getPostalCode());
        
        try {
            UsAutocompletions usAutocompletion = apiInstance.autocomplete(autoCompletionWritable);
            logger.info(String.format("[LobCompletionHandler] response body [body:%s]", usAutocompletion.toString()));
            List<Address> response = new ArrayList<>();
            
            
            usAutocompletion.getSuggestions().forEach(suggestion -> {
                Address addressSuggestion = new Address.Builder()
                                                       .street(suggestion.getPrimaryLine())
                                                       .city(suggestion.getCity())
                                                       .state(suggestion.getState())
                                                       .postalCode(suggestion.getZipCode())
                                                       .build();
                response.add(addressSuggestion);
            });
            
            return response;
        } catch (ApiException e) {
            logger.info(String.format("[LobCompletionHandler] response body [body:%s]", e.toString()));
            return nextHandler.completeAddress(address);     
        }
       
    }

    public boolean supportsType(String handlerType) {
        return TYPE.equals(handlerType);
    }
}
