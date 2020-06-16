package com.thoughtworks.locker;

import java.util.HashMap;

public class Locker {
    private int capacity;
    private HashMap<Ticket, Bag> tickets = new HashMap<>();
    private String lockerIsFull = "储物柜已满";
    private String ticketIsInvalid = "非法票据";

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket checkIn(Bag bag) {
        if (capacity == 0) {
            throw new RuntimeException(lockerIsFull);
        }

        Ticket ticket = new Ticket();
        tickets.put(ticket, bag);

        capacity = capacity - 1;

        return ticket;
    }

    public Bag checkOut(Ticket ticket) {
        Bag bag = tickets.get(ticket);
        if (bag == null) {
            throw new RuntimeException(ticketIsInvalid);
        }
        tickets.remove(ticket);
        return bag;
    }
}
