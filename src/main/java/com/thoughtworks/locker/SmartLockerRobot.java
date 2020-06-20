package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.LockerFullException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.List;

public class SmartLockerRobot {

    private final List<Locker> lockers;

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket checkIn(Bag bag) {
        assert (null != lockers);
        Locker selectedLocker = this.selectValidLocker();
        if (null == selectedLocker) {
            throw new LockerFullException();
        }
        return selectedLocker.checkIn(bag);
    }

    public Bag checkOut(Ticket ticket) {
        for (Locker locker : lockers) {
            Bag checkoutBag = locker.checkOut(ticket);
            if (checkoutBag != null) {
                return checkoutBag;
            }
        }
        throw new TicketInvalidException();
    }

    private Locker selectValidLocker() {
        Locker selectedLocker = null;
        for (Locker locker : lockers) {
            if (locker.isFull()) {
                continue;
            }
            if (selectedLocker == null) {
                selectedLocker = locker;
            } else {
                selectedLocker = selectedLocker.getAvailableCapacity() < locker.getAvailableCapacity()
                        ? locker : selectedLocker;
            }
        }
        return selectedLocker;
    }
}
