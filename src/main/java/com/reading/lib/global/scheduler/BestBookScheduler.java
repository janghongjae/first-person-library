package com.reading.lib.global.scheduler;

import com.reading.lib.domain.bestbook.service.BestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BestBookScheduler {

    @Autowired
    private final BestBookService bestBookService;

    public BestBookScheduler(BestBookService bestBookService) {
        this.bestBookService = bestBookService;
    }

    @Scheduled(cron = "0 0 0 1*?")
    public void updateMonthlyBestBooks() {
        bestBookService.updateMonthlyBestBooks();
    }
}
