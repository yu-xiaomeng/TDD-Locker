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
        if (obj == null || !(obj instanceof Ticket)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        return this.getTicketNum().equals(((Ticket) obj).getTicketNum());
    }
}
