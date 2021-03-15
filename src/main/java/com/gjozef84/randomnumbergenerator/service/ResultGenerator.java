package com.gjozef84.randomnumbergenerator.service;

import com.gjozef84.randomnumbergenerator.common.OperatorType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ResultGenerator {

    public Object calculateGeneratedResult(List<Object> generatedValues, OperatorType operatorType) {
        Object result = generatedValues.get(0);

        for (int i = 1; i < generatedValues.size(); i++) {
            Object generatedValue = generatedValues.get(i);
            if (generatedValue instanceof Number) {
                result = operatorType.apply((int) result, (int) generatedValue);
            } else {  // should be added here a parse methods if added a provider generating data different than numbers
                throw new UnsupportedOperationException();
            }
        }
        return result;
    }
}
    