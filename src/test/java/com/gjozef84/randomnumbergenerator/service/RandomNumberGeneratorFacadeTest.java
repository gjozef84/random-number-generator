package com.gjozef84.randomnumbergenerator.service;

import com.gjozef84.randomnumbergenerator.common.OperatorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RandomNumberGeneratorFacadeTest {

    @Mock
    private InternalNumberProviderServiceImpl internalNumberProviderServiceImpl;

    @Mock
    private ExternalNumberProviderServiceImpl externalNumberProviderServiceImpl;

    @Mock
    private ResultGenerator resultGenerator;

    private int min;
    private int max;
    private String stringedOperator;

    private RandomNumberGeneratorFacade underTest;

    private static Object[] parametersForTestGenerateRandomNumber() {
        return new Object[] {
            new Object[] {2, 2, 4},
            new Object[] {2, null, 2}
        };
    }

    @BeforeEach
    public void init() {
        min = 1;
        max = 10;
        stringedOperator = "+";
        underTest = new RandomNumberGeneratorFacade(internalNumberProviderServiceImpl, externalNumberProviderServiceImpl,
            resultGenerator, min, max, stringedOperator);
    }

    @ParameterizedTest
    @MethodSource(value = "parametersForTestGenerateRandomNumber")
    void testGenerateRandomNumber(Integer numberByInternatProvider, Integer numberByExternalProvider, Integer expected) {
        when(internalNumberProviderServiceImpl.generateRandomValue(min, max)).thenReturn(numberByInternatProvider);
        when(externalNumberProviderServiceImpl.generateRandomValue(min, max)).thenReturn(numberByExternalProvider);
        when(resultGenerator.calculateGeneratedResult(numberByInternatProvider, numberByExternalProvider, OperatorType.ADDITION)).thenReturn(expected);

        Integer actual = underTest.generateRandomNumber();

        assertEquals(expected, actual);
        verify(internalNumberProviderServiceImpl).generateRandomValue(anyInt(), anyInt());
        verifyNoMoreInteractions(internalNumberProviderServiceImpl);
        verify(externalNumberProviderServiceImpl).generateRandomValue(anyInt(), anyInt());
        verifyNoMoreInteractions(externalNumberProviderServiceImpl);
    }
}