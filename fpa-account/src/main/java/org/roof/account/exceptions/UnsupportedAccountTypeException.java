package org.roof.account.exceptions;

public class UnsupportedAccountTypeException extends AccountException {

    public UnsupportedAccountTypeException(String msg, Throwable t) {
        super(msg, t);
    }

    public UnsupportedAccountTypeException(String msg) {
        super(msg);
    }

}
