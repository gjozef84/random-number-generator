package com.gjozef84.randomnumbergenerator.service.impl;

import com.gjozef84.randomnumbergenerator.service.RandomNumberProviderService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternalNumberProviderServiceTest {

    private final RandomNumberProviderService underTest = new InternalNumberProviderService();

    @Test
    void testGenerateRandomValue() {
        int min = 2;
        int max = 2;
        int expected = 2;

        Integer actual = underTest.generateRandomValue(min, max);

        assertEquals(expected, actual);
    }
}