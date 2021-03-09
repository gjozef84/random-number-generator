package com.gjozef84.randomnumbergenerator.service;

import com.gjozef84.randomnumbergenerator.common.OperatorType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class ResultGenerator {

    public Integer calculateGeneratedResult(Integer numberGeneratedByInternalProvider, Integer numberGeneratedByExternalProvider, OperatorType operatorType) {
        if (ObjectUtils.allNotNull(numberGeneratedByInternalProvider, numberGeneratedByExternalProvider)) {
            return operatorType.apply(numberGeneratedByInternalProvider, numberGeneratedByExternalProvider);
        } else {
            log.info("numberGeneratedByExternalProvider returns null value. calculateGeneratedResult return only numberGeneratedByInternalProvider value {}",
                numberGeneratedByInternalProvider);
            return numberGeneratedByInternalProvider;
        }
    }
}
    