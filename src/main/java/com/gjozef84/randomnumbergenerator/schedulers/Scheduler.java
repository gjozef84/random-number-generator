package com.gjozef84.randomnumbergenerator.schedulers;

import com.gjozef84.randomnumbergenerator.service.RandomGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final RandomGeneratorService randomGeneratorService;

    @Scheduled(fixedRateString = "${random-number-generator.scheduled.generate-every-milliseconds}")
    private void runGenerateRandomNumber(){
        randomGeneratorService.generateRandomNumber();
    }
}
