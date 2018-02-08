package com.paazl.service;

import com.paazl.SpringWebservicesTestCaseApplication;
import com.paazl.data.CurrentBalance;
import com.paazl.data.Sheep;
import com.paazl.data.State;
import com.paazl.data.repositories.CurrentBalanceRepository;
import com.paazl.data.repositories.SheepRepository;
import com.paazl.dto.SheepStatussesDto;
import com.paazl.exception.ErrorCodeEnum;
import com.paazl.util.CurrentBalanceUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

import java.math.BigInteger;
import java.util.List;

@Service
public class ShepherdService {
    public static final Logger logger = LoggerFactory.getLogger(ShepherdService.class);

    @Value("${price_of_new_sheep}")
    private Integer priceOfSheep;

    @Autowired private SheepRepository sheepRepository;
    @Autowired private CurrentBalanceRepository currentBalanceRepository;

    public SheepStatussesDto getSheepStatusses() {
        List<Sheep> healthySheep = sheepRepository.findAllByState(State.HEALTHY);
        List<Sheep> deadSheep = sheepRepository.findAllByState(State.DEAD);

        return SheepStatusses.write(healthySheep, deadSheep);
    }

    public BigInteger getBalance() {
        return currentBalanceRepository.findFirstByOrderByTimestampDesc().getBalance();
    }

    public CurrentBalance getCurrentBalance() {
        return currentBalanceRepository.findFirstByOrderByTimestampDesc();
    }

    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public int orderSheep(int notSheepDesired) {
        int cost = notSheepDesired * priceOfSheep;
        CurrentBalance subtractBalance =
                CurrentBalanceUtils.subtractBalance(getCurrentBalance(), BigInteger.valueOf(cost));

        try {
            if (0 < subtractBalance.getBalance().compareTo(BigInteger.ZERO)) {
                updateCurrentBalance(subtractBalance);
                addSheep(notSheepDesired);
                return ErrorCodeEnum.OK.getErrorCode();
            }
        } catch (Exception e) {
            logger.error("Unexpected exception the order is faild to your flock");
        }
        return ErrorCodeEnum.FAIL.getErrorCode();
    }

    public boolean checkSheepStates() {
        List<Sheep> aliveSheep = sheepRepository.findAllByState(State.HEALTHY);
        List<Sheep> deadSheep = sheepRepository.findAllByState(State.DEAD);

        if (checkSheepRemaining(aliveSheep)) {
            logger.info("You lost! All sheep died, the sheep herder goes bankrupt!");
        } else {
            logger.info(
                    "Balance: {}, Nof sheep healthy and dead: [{}, {}]",
                    currentBalanceRepository.findFirstByOrderByTimestampDesc().getBalance().toString(),
                    aliveSheep.size(),
                    deadSheep.size());
        }

        return checkSheepRemaining(aliveSheep);
    }

    public void updateCurrentBalance(CurrentBalance currentBalance) {
        currentBalanceRepository.save(currentBalance);
    }

    public void addSheep(int healthySheeps) {
        for (int healthySheep=0; healthySheep<healthySheeps; healthySheep++) {
            updateAliveSheep(new Sheep(State.HEALTHY));
        }
    }

    private void updateAliveSheep(Sheep healthySheeps) {
        sheepRepository.save(healthySheeps);
    }

    private boolean checkSheepRemaining(List<Sheep> alive) {
        if (alive.isEmpty()) {
            SpringWebservicesTestCaseApplication.context.close();
            return false;
        }
        return true;
    }
}
