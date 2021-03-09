package com.gjozef84.randomnumbergenerator.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InternalNumberProviderServiceImplTest {

    private final RandomNumberProviderService underTest = new InternalNumberProviderServiceImpl();

    @Test
    void testGenerateRandomValue() {
        int min = 2;
        int max = 2;
        int expected = 2;

        Integer actual = underTest.generateRandomValue(min, max);

        assertEquals(expected, actual);
    }
}