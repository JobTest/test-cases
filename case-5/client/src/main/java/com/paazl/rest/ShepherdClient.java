package com.paazl.rest;

import com.paazl.domain.OrderSheep;
import com.paazl.dto.OrderSheepDto;
import com.paazl.dto.SheepStatussesDto;
import com.paazl.gui.GuiInterface;
import com.paazl.gui.OrderRequestListener;
import com.paazl.service.ShepherdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShepherdClient {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /*
     *  Use a Rest client to obtain the server status, so this client can be used to obtain that status.
     */

    private GuiInterface guiInterface;

    @Autowired
    private ShepherdService service;

    @Autowired
    public ShepherdClient(GuiInterface guiInterface) {
        this.guiInterface = guiInterface;

        guiInterface.addOrderRequestListener(new OrderRequestListener() {
            @Override
            public void orderRequest(int numberSheeps) {
                guiInterface.addServerFeedback("Number of sheep to order: " + numberSheeps);

                OrderSheepDto orderSheep = service.postOrderSheep(new OrderSheep(numberSheeps));

                guiInterface.addServerFeedback(orderSheep.toString());
                log.info("OrderSheep: " + orderSheep);
            }
        });
    }

    public String getServerStatus() {
        SheepStatussesDto sheepStatusses = service.getSheepStatusses();

        guiInterface.addServerFeedback(sheepStatusses.toString());
        log.info("SheepStatusses: " + sheepStatusses);

        return "Server status: " + service.checkSheepStatusses();
    }
}
