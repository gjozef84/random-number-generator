package com.gjozef84.randomnumbergenerator.service;

import com.gjozef84.randomnumbergenerator.common.OperatorType;
import com.gjozef84.randomnumbergenerator.common.ProviderName;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RandomNumberGeneratorFacade {

    private final Map<ProviderName, RandomNumberProviderService> numberProviders;
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
        numberProviders = ImmutableMap.<ProviderName, RandomNumberProviderService>builder()
            .put(ProviderName.INTERNAL, internalNumberProviderServiceImpl)
            .put(ProviderName.EXTERNAL, externalNumberProviderServiceImpl)
            .build();
        this.resultGenerator = resultGenerator;
        this.min = min;
        this.max = max;
        this.operatorType = OperatorType.parseOperator(stringedOperator);
    }

    public Object generateRandomNumber(List<ProviderName> currentlyUsedProviders) {
        log.info("generateRandomNumber currently using providers: {}", currentlyUsedProviders.toString());
        List<Object> generatedNumbers = currentlyUsedProviders.stream()
            .map(numberProviders::get)
            .map(numberProvider -> numberProvider.generateRandomValue(min, max))
            .collect(Collectors.toList());

        Object generatedResult = null;
        if (CollectionUtils.isNotEmpty(generatedNumbers)) {
            generatedResult = resultGenerator.calculateGeneratedResult(generatedNumbers, operatorType);
            log.info("generateRandomNumber() return {}", generatedResult);
        }
        return generatedResult;
    }
}
