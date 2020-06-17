package com.thoughtworks.locker;

import com.thoughtworks.locker.constant.Messages;

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
        throw new RuntimeException(Messages.LOCKER_IS_FULL);
    }

    public Bag checkOut(Ticket ticket) {
        for (Locker locker : lockers) {
            Bag bag = locker.checkOut(ticket);
            if (bag != null) {
                return bag;
            }
        }
        throw new RuntimeException(Messages.TICKET_IS_INVALID);
    }

}
