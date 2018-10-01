package org.roof.account.exceptions;

public class AccountException extends RuntimeException {

    public AccountException(String msg, Throwable t) {
        super(msg, t);
    }

    public AccountException(String msg) {
        super(msg);
    }

}
