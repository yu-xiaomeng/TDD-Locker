package com.thoughtworks.locker.exception;

import com.thoughtworks.locker.constant.Messages;

public class LockerFullException extends RuntimeException {

    public LockerFullException() {
        super(Messages.LOCKER_IS_FULL);
    }
}
