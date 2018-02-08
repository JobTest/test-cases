package com.paazl.rest;

import com.paazl.data.OrderSheep;
import com.paazl.exception.ErrorCodeEnum;
import com.paazl.service.ShepherdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ordermanager")
public class OrderSheepResource {
    public static final Logger logger = LoggerFactory.getLogger(OrderSheepResource.class);

    @Autowired
    private ShepherdService service;

    @RequestMapping(value = "/ordersheep", method = RequestMethod.POST)
    public ResponseEntity<?> orderSheep(@Valid @RequestBody OrderSheep orderSheep, Errors errors) {
        logger.info(orderSheep.toString());
        if (errors.hasErrors()
                || service.orderSheep(orderSheep.getNotSheepDesired())!=0) {
            logger.info(ErrorCodeEnum.FAIL.getDefaultMessage());
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        logger.info("In total " + orderSheep.getNotSheepDesired() + " sheep were ordered and added to your flock!");
        return new ResponseEntity<>(service.getSheepStatusses(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value ="/checksheepstates", method = RequestMethod.GET)
    public ResponseEntity<?> checkSheepStates() {
        boolean checkSheepStates = service.checkSheepStates();
        logger.info("checkSheepStates = " + checkSheepStates);
        if (checkSheepStates)
            return new ResponseEntity<>(checkSheepStates, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
    }

}
