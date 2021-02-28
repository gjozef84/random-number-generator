package com.gjozef84.randomnumbergenerator.service.impl;

import com.gjozef84.randomnumbergenerator.service.ResultPrinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.gjozef84.randomnumbergenerator.common.Constants.DATE_TIME_FORMATTER;

@Service
@Slf4j
public class ResultPrinterImpl implements ResultPrinter {

    @Override
    public void printResult(Integer generatedResult, int min, int max, String operator) {
        log.info("Application generated ({}) new number from range [{} - {}] using arithmetic operator '{}': {}\n",
            LocalDateTime.now().format(DATE_TIME_FORMATTER), min, max, operator, generatedResult);
    }
}
