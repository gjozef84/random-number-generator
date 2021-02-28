package com.gjozef84.randomnumbergenerator.service.impl;

import com.gjozef84.randomnumbergenerator.common.OperatorType;
import com.gjozef84.randomnumbergenerator.service.ResultGenerator;
import com.gjozef84.randomnumbergenerator.validators.GenerateRangeValueValidator;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitParamsRunner.class)
class ResultGeneratorImplTest {

    private final ResultGenerator underTest = new ResultGeneratorImpl();

    private static Object[] parametersForTestCalculateGeneratedResult() {
        return new Object[] {
            new Object[] {2, 2, OperatorType.ADDITION, 4},
            new Object[] {2, 2, OperatorType.SUBTRACTION, 0},
            new Object[] {2, 2, OperatorType.DIVIDE, 1},
            new Object[] {2, 2, OperatorType.MULTIPLE, 4},
            new Object[] {2, null, OperatorType.ADDITION, 2}
        };
    }

    @ParameterizedTest
    @MethodSource(value = "parametersForTestCalculateGeneratedResult")
    void testCalculateGeneratedResult(Integer numberByInternatProvider, Integer numberByExternalProvider,
                                      OperatorType operatorType, Integer expected) {

        Integer actual = underTest.calculateGeneratedResult(numberByInternatProvider, numberByExternalProvider, operatorType);

        assertEquals(expected, actual);
    }
}