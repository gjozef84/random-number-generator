package com.gjozef84.randomnumbergenerator.controller;

import com.gjozef84.randomnumbergenerator.common.ProviderName;
import com.gjozef84.randomnumbergenerator.service.RandomNumberGeneratorFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NumberGeneratorControllerTest {

    private static List<ProviderName> CURRENTLY_USED_NUMBER_PROVIDERS = Arrays.asList(ProviderName.INTERNAL, ProviderName.EXTERNAL);

    @Mock
    private RandomNumberGeneratorFacade randomNumberGeneratorFacade;

    private NumberGeneratorController underTest;

    @BeforeEach
    public void init() {
        underTest = new NumberGeneratorController(randomNumberGeneratorFacade);
    }

    @Test
    void testGetGeneratedNumber() {
        Integer expectedResult = 10;
        when(randomNumberGeneratorFacade.generateRandomNumber(CURRENTLY_USED_NUMBER_PROVIDERS)).thenReturn(expectedResult);

        final Object actualResult = underTest.getGeneratedNumber();

        assertEquals(expectedResult, actualResult);
        verify(randomNumberGeneratorFacade).generateRandomNumber(CURRENTLY_USED_NUMBER_PROVIDERS);
    }
}