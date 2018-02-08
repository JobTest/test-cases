package com.paazl.rest;

import com.paazl.dto.SheepStatussesDto;
import com.paazl.service.ShepherdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/shepherdmanager")
public class ShepherdResource {
    public static final Logger logger = LoggerFactory.getLogger(ShepherdResource.class);

    @Autowired
    ShepherdService service;

    @RequestMapping(value ="/balance", method = RequestMethod.GET)
    public ResponseEntity<?> getBalance() {
        BigInteger balance = service.getBalance();
        logger.info("getBalance = " + balance);
        if (balance!=null)
            return new ResponseEntity<>(balance, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/sheepstatusses", method = RequestMethod.GET)
    public ResponseEntity<?> getSheepStatusses() {
        SheepStatussesDto sheepStatusses = service.getSheepStatusses();
        logger.info("getSheepStatusses = " + sheepStatusses);
        if (sheepStatusses!=null)
            return new ResponseEntity<>(sheepStatusses, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
