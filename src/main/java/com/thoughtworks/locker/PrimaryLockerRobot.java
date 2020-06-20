package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.LockerFullException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.List;

public class PrimaryLockerRobot {
    private final List<Locker> lockers;

    public PrimaryLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket checkIn(Bag bag) {
        for (Locker locker : lockers) {
            if (locker.getAvailableCapacity() > 0) {
                return locker.checkIn(bag);
            }
        }
        throw new LockerFullException();
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

}
