package com.gjozef84.randomnumbergenerator.common;

import com.gjozef84.randomnumbergenerator.exceptions.ApplicationInitializationException;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

@Getter
public enum OperatorType {
    ADDITION("+") {
        @Override
        public int apply(int x1, int x2) {
            return x1 + x2;
        }
    },

    SUBTRACTION("-") {
        @Override
        public int apply(int x1, int x2) {
            return x1 - x2;
        }
    },

    DIVIDE("/") {
        @Override
        public int apply(int x1, int x2) {
            return x1 / x2;
        }
    },

    MULTIPLE("*") {
        @Override
        public int apply(int x1, int x2) {
            return x1 * x2;
        }
    };

    private final String stringedOperator;

    OperatorType(String stringedOperator) {
        this.stringedOperator = stringedOperator;
    }

    public abstract int apply(int x1, int x2);

    @Override
    public String toString() {
        return stringedOperator;
    }

    public static OperatorType parseOperator(String operator) {
        return Arrays.stream(OperatorType.values())
            .filter(operatorType -> Objects.equals(operatorType.getStringedOperator(), operator))
            .findFirst()
            .orElseThrow(() -> new ApplicationInitializationException(Collections.singletonList(String.valueOf(operator)), "The value set for the merging operator is not supported."));
    }
}
