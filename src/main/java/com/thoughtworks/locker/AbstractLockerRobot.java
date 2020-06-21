package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.LockerFullException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.List;

public abstract class AbstractLockerRobot {
    private final List<Locker> lockers;

    public AbstractLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket checkIn(Bag bag) {
        Locker selectedLocker = this.selectValidLocker();
        if (selectedLocker == null) {
            throw new LockerFullException();
        }
        return selectedLocker.checkIn(bag);
    }

    public Bag checkOut(Ticket ticket) {
        for (Locker locker : lockers) {
            Bag bag = locker.checkOut(ticket);
            if (bag != null) {
                return bag;
            }
        }
        throw new TicketInvalidException();
    }

    public List<Locker> getLockers() {
        return lockers;
    }

    protected abstract Locker selectValidLocker();
}
