package com.gjozef84.randomnumbergenerator.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class InternalNumberProviderServiceImpl implements RandomNumberProviderService {

    @Override
    public Integer generateRandomValue(int min, int max) {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        randomDataGenerator.reSeedSecure();

        Integer generationResult = randomDataGenerator.nextSecureInt(min, max);
        log.info("InternalNumberProviderService generated value = {}", generationResult);

        return generationResult;
    }
}
