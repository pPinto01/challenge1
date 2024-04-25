package com.challenge1.module.utils.chain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.challenge1.module.models.Address;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class SmartyCompletionHandler extends AddressAbstractHandler {
    public static final String TYPE = "Smarty";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Address> completeAddress(Address address) {
        logger.info(String.format("[SmartyCompletionHandler]"));
        OkHttpClient client = new OkHttpClient();

        String url = String.format("https://us-autocomplete-pro.api.smarty.com/lookup?key=197551208587144637&search=%s&max_results=10", address.getPartialStreet());

        Request request = new Request.Builder()
                .url(url)
                .addHeader("referer", "ss.net")
                .build();

        try {
            Response response = client.newCall(request).execute();
            List<Address> returnResponse = new ArrayList<>();

            if (response.isSuccessful()) {
                String body = response.body().string();
                logger.info(String.format("[SmartyCompletionHandler] response body [body:%s]", body));
                
                
                JSONObject responseBody = new JSONObject(body);
                responseBody.getJSONArray("suggestions").forEach(sug -> {
                    JSONObject suggestion = (JSONObject) sug;
                    Address addressSuggestion = new Address.Builder()
                                                           .street(suggestion.getString("street_line"))
                                                           .city(suggestion.getString("city"))
                                                           .state(suggestion.getString("state"))
                                                           .postalCode(suggestion.getString("zipcode"))
                                                           .build();
                    returnResponse.add(addressSuggestion);
                });

            } else {
                // Print the error message if the request was not successful
                System.out.println("Error: " + response.code() + " " + response.message());
            }
            return returnResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean supportsType(String handlerType) {
        return TYPE.equals(handlerType);
    }
}
