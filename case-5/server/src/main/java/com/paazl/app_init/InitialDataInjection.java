package com.paazl.app_init;

import com.paazl.data.CurrentBalance;
import com.paazl.data.Sheep;
import com.paazl.data.repositories.CurrentBalanceRepository;
import com.paazl.data.repositories.SheepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Component
public class InitialDataInjection implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${initial.startBalance}")
    private BigInteger startBalance;

    @Value("${initial.startNofSheep}")
    private  Integer startNofSheep;

    @Autowired private CurrentBalanceRepository currentBalanceRepository;
    @Autowired private SheepRepository sheepRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        setInitBalance();
        setInitSheep();
    }

    private void setInitSheep() {
        sheepRepository.deleteAll();
        for (int i=0; i<startNofSheep;++i) {
            sheepRepository.save(new Sheep());
        }
    }

    private void setInitBalance() {
        currentBalanceRepository.deleteAll();
        currentBalanceRepository.save(new CurrentBalance(startBalance));
    }
}
