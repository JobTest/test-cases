package com.paazl.ws;

import com.google.common.collect.ImmutableMap;
import com.paazl.domain.SheepStatusses;
import com.paazl.exception.ClientWSException;
import com.paazl.exception.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SheepStatussesRest extends RestTemplate {

    public SheepStatussesRest(){
        super();
    }

    public SheepStatussesRest(ClientHttpRequestFactory requestFactory) {
        this();
        this.setRequestFactory(requestFactory);
    }

    @Value("${remote.server.shepherd_url}")
    private String url;

    private final String endpoint = "sheepstatusses";

    public ResponseEntity<SheepStatusses> get()
            throws ClientWSException
    {
        try {
            return getForEntity(
                    url,
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
