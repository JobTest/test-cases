package com.paazl.configuration;

import com.paazl.ws.CheckSheepStatussesRest;
import com.paazl.ws.BalanceRest;
import com.paazl.ws.OrderSheepRest;
import com.paazl.ws.SheepStatussesRest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
@ComponentScan({"com.paazl"})
public class ApplicationConfiguration {

    @Value("${remote.server.read_timeout}")
    private Integer readTimeout;

    @Value("${remote.server.connect_timeout}")
    private Integer connectTimeout;

    @Value("${remote.server.connection_request_timeout}")
    private Integer connectionRequestTimeout;

    @Bean
    public BalanceRest balanceRest() {
        return new BalanceRest(getClientHttpRequestFactory());
    }

    @Bean
    public SheepStatussesRest sheepStatussesRest() {
        return new SheepStatussesRest(getClientHttpRequestFactory());
    }

    @Bean
    public OrderSheepRest orderSheepRest() {
        return new OrderSheepRest(getClientHttpRequestFactory());
    }

    @Bean
    public CheckSheepStatussesRest checkSheepStatussesRest() {
        return new CheckSheepStatussesRest(getClientHttpRequestFactory());
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(readTimeout);
        factory.setConnectTimeout(connectTimeout);
        factory.setConnectionRequestTimeout(connectionRequestTimeout);
        return factory;
    }
}
