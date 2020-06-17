package com.thoughtworks.locker;

import com.thoughtworks.locker.constant.Messages;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Locker {
    private AtomicInteger availableCapacity;
    private HashMap<Ticket, Bag> tickets = new HashMap<>();

    public Locker(int capacity) {
        this.availableCapacity = new AtomicInteger(capacity);
    }

    public Ticket checkIn(Bag bag) {
        int available = availableCapacity.get();
        if (available == 0) {
            throw new RuntimeException(Messages.LOCKER_IS_FULL);
        }
        availableCapacity.compareAndSet(available, available - 1);
        Ticket ticket = new Ticket();
        tickets.put(ticket, bag);

        return ticket;
    }

    public Bag checkOut(Ticket ticket) {
        Bag bag = tickets.get(ticket);
        if (bag != null) {
            tickets.remove(ticket);
            availableCapacity.getAndAdd(1);
        }
        return bag;
    }

    public int getAvailableCapacity() {
        return availableCapacity.get();
    }
}
