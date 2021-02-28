package com.gjozef84.randomnumbergenerator.validators;

import com.gjozef84.randomnumbergenerator.exceptions.ApplicationInitializationException;
import junitparams.JUnitParamsRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnitParamsRunner.class)
class GenerateRangeValueValidatorTest {

    private final GenerateRangeValueValidator underTest = new GenerateRangeValueValidator();

    private static Object[] parametersForTestValidRangeValues() {
        return new Object[] {
            new Object[] {2, 1, false},
            new Object[] {1, 2, true},
            new Object[] {2, 2, true}
        };
    }

    @ParameterizedTest
    @MethodSource("parametersForTestValidRangeValues")
    public void testValidRangeValues(int min, int max, boolean valid) {
        if (!valid) {
            Exception exception = assertThrows(ApplicationInitializationException.class, () -> underTest.validRangeValues(min, max));

            String expectedMessage = "The minimum value of the generated number is greater than the maximum value.";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}