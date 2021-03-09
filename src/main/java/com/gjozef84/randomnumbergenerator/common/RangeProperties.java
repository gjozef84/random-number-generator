package com.gjozef84.randomnumbergenerator.common;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RangeProperties {

    @Min(value = -1000000000)
    @Max(value = 1000000000)
    @NotNull
    private Integer min;

    @Min(value = -1000000000)
    @Max(value = 1000000000)
    @NotNull
    private Integer max;
}
