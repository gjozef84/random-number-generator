package com.gjozef84.randomnumbergenerator.config;

import com.gjozef84.randomnumbergenerator.common.RangeProperties;
import com.gjozef84.randomnumbergenerator.exceptions.ApplicationInitializationException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;

@Validated
@ConfigurationProperties(prefix = "random-number-generator")
@Slf4j
@Getter
@Setter
class GeneratorProperties implements Validator {

    @Valid
    private RangeProperties range;

    @NotBlank
    private String operator;

    @Override
    public boolean supports(Class clazz) {
        return RangeProperties.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RangeProperties rangeProperties = (RangeProperties) target;
        Integer min = rangeProperties.getMin();
        Integer max = rangeProperties.getMax();

        if (min > max) {
            log.error("The minimum value {} of the generated number is greater than the maximum value {}.", min, max);
            errors.rejectValue("emailAddress", "field.domain.required");
            throw new ApplicationInitializationException(Arrays.asList(String.valueOf(min), String.valueOf(max)),
                "The minimum value of the generated number is greater than the maximum value.");
        }
    }
}