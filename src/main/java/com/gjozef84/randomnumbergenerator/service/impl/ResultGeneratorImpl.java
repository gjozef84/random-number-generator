package com.gjozef84.randomnumbergenerator.service.impl;

import com.gjozef84.randomnumbergenerator.common.OperatorType;
import com.gjozef84.randomnumbergenerator.service.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResultGeneratorImpl implements ResultGenerator {

    @Override
    public Integer calculateGeneratedResult(Integer numberGeneratedByInternalProvider, Integer numberGeneratedByExternalProvider, OperatorType operatorType) {
        if (ObjectUtils.allNotNull(numberGeneratedByInternalProvider, numberGeneratedByExternalProvider)) {
            return operatorType.apply(numberGeneratedByInternalProvider, numberGeneratedByExternalProvider);
        } else {
            log.debug("numberGeneratedByExternalProvider returns null value. calculateGeneratedResult return only numberGeneratedByInternalProvider value {}",
                numberGeneratedByInternalProvider);
            return numberGeneratedByInternalProvider;
        }
    }
}
