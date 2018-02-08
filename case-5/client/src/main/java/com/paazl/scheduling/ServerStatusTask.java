package com.paazl.scheduling;

import com.paazl.dto.BalanceDto;
import com.paazl.dto.SheepStatussesDto;
import com.paazl.gui.GuiInterface;
import com.paazl.service.ShepherdService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Component
public class ServerStatusTask {
    public static final Logger logger = LoggerFactory.getLogger(ServerStatusTask.class);

    /*
     *  The task will update the gui per instructed by the CRON job set in the properties file. But now it only prints a hard coded string.
     *  Change the code so this task prints the server status obtained from the server instead of the hard coded string.
     */

    private GuiInterface guiInterface;

    @Autowired
    private ShepherdService service;

    @Autowired
    public ServerStatusTask(GuiInterface guiInterface) {
        this.guiInterface = guiInterface;
    }

    @Scheduled(cron="${scheduling.server_status.cron}")
    public void getServerStatus() {
        SheepStatussesDto sheepStatusses = service.getSheepStatusses();

        guiInterface.addServerFeedback(sheepStatusses.toString());
        logger.info("SheepStatusses: " + sheepStatusses);
    }

    @Scheduled(cron="${scheduling.server_status.cron}")
    public void getBalance() {
        BalanceDto balance = service.getBalance();

        guiInterface.addServerFeedback(balance.toString());
        logger.info("Balance: " + balance);
    }
}
