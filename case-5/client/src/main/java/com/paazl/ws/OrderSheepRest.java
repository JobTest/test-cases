package com.paazl.ws;

import com.google.common.collect.ImmutableMap;
import com.paazl.domain.OrderSheep;
import com.paazl.domain.SheepStatusses;
import com.paazl.exception.ClientWSException;
import com.paazl.exception.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderSheepRest extends RestTemplate {

    public OrderSheepRest(){
        super();
    }

    public OrderSheepRest(ClientHttpRequestFactory requestFactory) {
        this();
        this.setRequestFactory(requestFactory);
    }

    @Value("${remote.server.order_url}")
    private String url;

    private final String endpoint = "ordersheep";

    public ResponseEntity<SheepStatusses> post(OrderSheep orderSheep)
            throws ClientWSException
    {
        try {
            return postForEntity(
                    url,
                    orderSheep,
                    SheepStatusses.class,
                    ImmutableMap.of("endpoint", endpoint));
        } catch (RuntimeException e) {
            throw new ClientWSException(
                    ErrorCodeEnum.UNAVAILABLE_ERROR.getDefaultMessage(),
                    e.getLocalizedMessage(),
                    e.getClass());
        }
    }
}
