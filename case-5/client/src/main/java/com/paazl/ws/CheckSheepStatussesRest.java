package com.paazl.ws;

import com.google.common.collect.ImmutableMap;
import com.paazl.exception.ClientWSException;
import com.paazl.exception.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CheckSheepStatussesRest extends RestTemplate {

    public CheckSheepStatussesRest(){
        super();
    }

    public CheckSheepStatussesRest(ClientHttpRequestFactory requestFactory) {
        this();
        this.setRequestFactory(requestFactory);
    }

    @Value("${remote.server.order_url}")
    private String url;

    private final String endpoint = "checksheepstates";

    public ResponseEntity<Boolean> get()
            throws ClientWSException
    {
        try {
            return getForEntity(
                    url,
                    Boolean.class,
                    ImmutableMap.of("endpoint", endpoint));
        } catch (RuntimeException e) {
            throw new ClientWSException(
                    ErrorCodeEnum.UNAVAILABLE_ERROR.getDefaultMessage(),
                    e.getLocalizedMessage(),
                    e.getClass());
        }
    }
}
