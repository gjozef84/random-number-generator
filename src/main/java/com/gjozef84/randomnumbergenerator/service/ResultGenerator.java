package com.gjozef84.randomnumbergenerator.service;

import com.gjozef84.randomnumbergenerator.common.OperatorType;

public interface ResultGenerator {
    Integer calculateGeneratedResult(Integer numberGeneratedByInternalProvider, Integer numberGeneratedByExternalProvider, OperatorType operatorType);
}
