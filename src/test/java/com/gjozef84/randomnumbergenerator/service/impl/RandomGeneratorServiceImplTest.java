package com.gjozef84.randomnumbergenerator.service.impl;

import com.gjozef84.randomnumbergenerator.common.OperatorType;
import com.gjozef84.randomnumbergenerator.service.RandomGeneratorService;
import com.gjozef84.randomnumbergenerator.service.ResultGenerator;
import com.gjozef84.randomnumbergenerator.service.ResultPrinter;
import com.gjozef84.randomnumbergenerator.validators.GenerateRangeValueValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RandomGeneratorServiceImplTest {

    @Mock
    private InternalNumberProviderService internalNumberProviderService;

    @Mock
    private ExternalNumberProviderService externalNumberProviderService;

    @Mock
    private GenerateRangeValueValidator generateRangeValueValidator;

    @Mock
    private ResultGenerator resultGenerator;

    @Mock
    private ResultPrinter resultPrinter;

    private int min;
    private int max;
    private String stringedOperator;

    private RandomGeneratorService underTest;

    @BeforeEach
    public void init() {
        min = 1;
        max = 10;
        stringedOperator = "+";
        underTest = new RandomGeneratorServiceImpl(internalNumberProviderService, externalNumberProviderService,
            generateRangeValueValidator, resultGenerator, resultPrinter, min, max, stringedOperator);
    }

    @Test
    void testGenerateRandomNumber() {
        doNothing().when(generateRangeValueValidator).validRangeValues(min, max);
        when(internalNumberProviderService.generateRandomValue(min, max)).thenReturn(1);
        when(externalNumberProviderService.generateRandomValue(min, max)).thenReturn(10);
        OperatorType operatorType = OperatorType.parseOperator(stringedOperator);
        when(resultGenerator.calculateGeneratedResult(min, max, operatorType)).thenReturn(11);

        underTest.generateRandomNumber();

        verify(generateRangeValueValidator).validRangeValues(anyInt(), anyInt());
        verifyNoMoreInteractions(generateRangeValueValidator);
        verify(internalNumberProviderService).generateRandomValue(anyInt(), anyInt());
        verifyNoMoreInteractions(internalNumberProviderService);
        verify(externalNumberProviderService).generateRandomValue(anyInt(), anyInt());
        verifyNoMoreInteractions(externalNumberProviderService);
        verify(resultGenerator).calculateGeneratedResult(anyInt(), anyInt(), any(OperatorType.class));
        verifyNoMoreInteractions(resultGenerator);
        verify(resultPrinter).printResult(anyInt(), anyInt(), anyInt(), anyString());
        verifyNoMoreInteractions(resultPrinter);
    }
}