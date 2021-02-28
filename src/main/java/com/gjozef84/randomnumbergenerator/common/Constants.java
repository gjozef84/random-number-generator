package com.gjozef84.randomnumbergenerator.common;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class Constants {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final Duration TIMEOUT_DURATION = Duration.ofSeconds(10);

}
