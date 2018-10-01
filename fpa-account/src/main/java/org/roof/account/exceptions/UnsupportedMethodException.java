package org.roof.account.exceptions;

public class UnsupportedMethodException extends AccountException {

    public UnsupportedMethodException(String msg, Throwable t) {
        super(msg, t);
    }

    public UnsupportedMethodException(String msg) {
        super(msg);
    }

}
