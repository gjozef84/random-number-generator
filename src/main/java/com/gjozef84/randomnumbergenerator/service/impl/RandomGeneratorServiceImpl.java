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
@Slf4j
public class RandomGeneratorServiceImpl implements RandomGeneratorService {

    private final InternalNumberProviderService internalNumberProviderService;
    private final ExternalNumberProviderService externalNumberProviderService;
    private final GenerateRangeValueValidator generateRangeValueValidator;
    private final ResultGenerator resultGenerator;
    private final ResultPrinter resultPrinter;
    private final int min;
    private final int max;
    private final String stringedOperator;

    public RandomGeneratorServiceImpl(InternalNumberProviderService internalNumberProviderService,
                                      ExternalNumberProviderService externalNumberProviderService,
                                      GenerateRangeValueValidator generateRangeValueValidator,
                                      ResultGenerator resultGenerator, ResultPrinter resultPrinter,
                                      @Value("${random-number-generator.range.min}") int min,
                                      @Value("${random-number-generator.range.max}") int max,
                                      @Value("${random-number-generator.operator}") String stringedOperator) {
        this.internalNumberProviderService = internalNumberProviderService;
        this.externalNumberProviderService = externalNumberProviderService;
        this.generateRangeValueValidator = generateRangeValueValidator;
        this.resultGenerator = resultGenerator;
        this.resultPrinter = resultPrinter;
        this.min = min;
        this.max = max;
        this.stringedOperator = stringedOperator;
    }

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
