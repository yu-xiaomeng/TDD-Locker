package com.thoughtworks.locker;

public class LockerRobotManagerTicket {
    private final Ticket ticket;

    public LockerRobotManagerTicket(final Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
