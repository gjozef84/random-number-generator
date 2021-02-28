package com.gjozef84.randomnumbergenerator.exceptions;

import java.util.List;

public class ApplicationInitializationException extends ApplicationException {

    public ApplicationInitializationException(final List<String> params, final String message) {
        super(params, message, null);
    }
}
