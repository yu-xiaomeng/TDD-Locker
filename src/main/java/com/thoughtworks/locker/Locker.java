package com.thoughtworks.locker;

import com.thoughtworks.locker.constant.Messages;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Locker {
    private final AtomicInteger availableCapacity;
    private final HashMap<Ticket, Bag> tickets;

    public Locker(int capacity) {
        tickets = new HashMap<>(capacity);
        this.availableCapacity = new AtomicInteger(capacity);
    }

    public Ticket checkIn(Bag bag) {
        int available = availableCapacity.get();
        if (available == 0) {
            throw new RuntimeException(Messages.LOCKER_IS_FULL);
        }
        availableCapacity.decrementAndGet();
        Ticket ticket = new Ticket();
        tickets.put(ticket, bag);

        return ticket;
    }

    public Bag checkOut(Ticket ticket) {
        Bag bag = tickets.get(ticket);
        if (bag != null) {
            tickets.remove(ticket);
            availableCapacity.incrementAndGet();
        }
        return bag;
    }

    public int getAvailableCapacity() {
        return availableCapacity.get();
    }
}
