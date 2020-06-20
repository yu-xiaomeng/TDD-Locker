package com.thoughtworks.locker.exception;

import com.thoughtworks.locker.constant.Messages;

public class TicketInvalidException extends RuntimeException {

    public TicketInvalidException() {
        super(Messages.TICKET_IS_INVALID);
    }
}
