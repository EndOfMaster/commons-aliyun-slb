package com.endofmaster.commons.slb;

/**
 * @author ZM.Wang
 */
public class SlbException extends RuntimeException {

    public SlbException(Throwable cause) {
        super(cause);
    }

    public SlbException(String message) {
        super(message);
    }

    public SlbException(String message, Throwable cause) {
        super(message, cause);
    }
}
