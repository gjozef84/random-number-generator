package com.gjozef84.randomnumbergenerator.service.impl;

import com.gjozef84.randomnumbergenerator.service.RandomNumberProviderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InternalNumberProviderService implements RandomNumberProviderService {

    @Override
    public Integer generateRandomValue(int min, int max) {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

        log.debug("Trying to generate random value for range [min:{} - max:{}]", min, max);
        Integer generationResult = randomDataGenerator.nextInt(min, max);
        log.debug("InternalNumberProviderService generated value = {}", generationResult);

        return generationResult;
    }
}
