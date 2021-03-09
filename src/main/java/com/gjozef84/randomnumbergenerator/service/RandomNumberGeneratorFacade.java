package com.gjozef84.randomnumbergenerator.service;

import com.gjozef84.randomnumbergenerator.common.OperatorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RandomNumberGeneratorFacade {

    private final InternalNumberProviderServiceImpl internalNumberProviderServiceImpl;
    private final ExternalNumberProviderServiceImpl externalNumberProviderServiceImpl;
    private final ResultGenerator resultGenerator;
    private final int min;
    private final int max;
    private final OperatorType operatorType;

    public RandomNumberGeneratorFacade(InternalNumberProviderServiceImpl internalNumberProviderServiceImpl,
                                       ExternalNumberProviderServiceImpl externalNumberProviderServiceImpl,
                                       ResultGenerator resultGenerator,
                                       @Value("${random-number-generator.range.min}") int min,
                                       @Value("${random-number-generator.range.max}") int max,
                                       @Value("${random-number-generator.operator}") String stringedOperator) {
        this.internalNumberProviderServiceImpl = internalNumberProviderServiceImpl;
        this.externalNumberProviderServiceImpl = externalNumberProviderServiceImpl;
        this.resultGenerator = resultGenerator;
        this.min = min;
        this.max = max;
        this.operatorType = OperatorType.parseOperator(stringedOperator);
    }

    public Integer generateRandomNumber() {
        Integer numberGeneratedByInternalProvider = internalNumberProviderServiceImpl.generateRandomValue(min, max);
        Integer numberGeneratedByExternalProvider = externalNumberProviderServiceImpl.generateRandomValue(min, max);

        Integer generatedResult = resultGenerator.calculateGeneratedResult(numberGeneratedByInternalProvider, numberGeneratedByExternalProvider, operatorType);
        log.info("generateRandomNumber() return {}", generatedResult);
        return generatedResult;
    }


}
