package com.gjozef84.randomnumbergenerator.validators;

import com.gjozef84.randomnumbergenerator.exceptions.ApplicationInitializationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class GenerateRangeValueValidator {
    public void validRangeValues(int min, int max) {
        if (min > max) {
            log.error("The minimum value {} of the generated number is greater than the maximum value {}.", min, max);
            throw new ApplicationInitializationException(Arrays.asList(String.valueOf(min), String.valueOf(max)),
                "The minimum value of the generated number is greater than the maximum value.");
        }
    }
}
