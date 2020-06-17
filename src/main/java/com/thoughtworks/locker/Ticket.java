package com.thoughtworks.locker;

import java.util.UUID;

public class Ticket {
    private String ticketNum;

    public Ticket() {
        this.ticketNum = UUID.randomUUID().toString();
    }

    public String getTicketNum() {
        return ticketNum;
    }

    @Override
    public int hashCode() {
        return this.ticketNum.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return this.getTicketNum().equals(((Ticket) obj).getTicketNum());
    }
}
