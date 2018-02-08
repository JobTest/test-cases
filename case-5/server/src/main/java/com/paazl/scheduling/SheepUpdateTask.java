package com.paazl.scheduling;

import com.paazl.data.Sheep;
import com.paazl.data.State;
import com.paazl.data.repositories.SheepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SheepUpdateTask {

    @Autowired private SheepRepository sheepRepository;

    @Value("${chance.sheep.dies}")
    private int chanceSheepDies;

    @Scheduled(cron="${scheduling.update_sheep_cron}")
    @Transactional
    public void updateSheepStates() {
        updateAliveSheep();
    }

    private void updateAliveSheep() {
        List<Sheep> aliveSheep = sheepRepository.findAllByState(State.HEALTHY);
        for (Sheep sheep : aliveSheep) {
            int rolled = (int) (Math.random() * 100);
            if (rolled < chanceSheepDies) sheep.setState(State.DEAD);
            sheepRepository.save(sheep);
        }
    }
}