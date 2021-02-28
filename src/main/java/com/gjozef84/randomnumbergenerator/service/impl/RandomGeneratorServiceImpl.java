package com.gjozef84.randomnumbergenerator.service.impl;

import com.gjozef84.randomnumbergenerator.common.OperatorType;
import com.gjozef84.randomnumbergenerator.service.RandomGeneratorService;
import com.gjozef84.randomnumbergenerator.service.ResultGenerator;
import com.gjozef84.randomnumbergenerator.service.ResultPrinter;
import com.gjozef84.randomnumbergenerator.validators.GenerateRangeValueValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RandomGeneratorServiceImpl implements RandomGeneratorService {

    private final InternalNumberProviderService internalNumberProviderService;
    private final ExternalNumberProviderService externalNumberProviderService;
    private final GenerateRangeValueValidator generateRangeValueValidator;
    private final ResultGenerator resultGenerator;
    private final ResultPrinter resultPrinter;

    @Value("${random-number-generator.range.min}")
    private int min;

    @Value("${random-number-generator.range.max}")
    private int max;

    @Value("${random-number-generator.operator}")
    private String stringedOperator;

    @Override
    public void generateRandomNumber() {
        generateRangeValueValidator.validRangeValues(min, max);

        Integer numberGeneratedByInternalProvider = internalNumberProviderService.generateRandomValue(min, max);
        log.debug("InternalNumberProviderService generated number {}", numberGeneratedByInternalProvider);
        Integer numberGeneratedByExternalProvider = externalNumberProviderService.generateRandomValue(min, max);
        log.debug("ExternalNumberProviderService generated number {}", numberGeneratedByExternalProvider);

        OperatorType operatorType = OperatorType.parseOperator(stringedOperator);
        log.debug("ExternalNumberProviderService generated number {}", numberGeneratedByExternalProvider);

        Integer calculatedResult = resultGenerator.calculateGeneratedResult(numberGeneratedByInternalProvider, numberGeneratedByExternalProvider, operatorType);
        log.debug("ResultGenerator calculated result {}", calculatedResult);

        resultPrinter.printResult(calculatedResult, min, max, stringedOperator);
    }
}
