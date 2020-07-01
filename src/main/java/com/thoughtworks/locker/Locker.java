package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.LockerFullException;
import com.thoughtworks.locker.utils.PrintUtil;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Locker implements ReportPrint {
    private final AtomicInteger availableCapacity;
    private final HashMap<Ticket, Bag> tickets;

    public Locker(int capacity) {
        tickets = new HashMap<>(capacity);
        this.availableCapacity = new AtomicInteger(capacity);
    }

    public Ticket checkIn(Bag bag) {
        int available = availableCapacity.get();
        if (available == 0) {
            throw new LockerFullException();
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

    public boolean isFull() {
        return availableCapacity.get() == 0;
    }

    public int getCapacity() {
        return availableCapacity.get() + tickets.size();
    }

    @Override
    public String print() {
        return PrintUtil.print("L", getAvailableCapacity(), getCapacity());
    }
}
