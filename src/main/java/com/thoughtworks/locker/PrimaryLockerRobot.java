package com.thoughtworks.locker;

import java.util.List;

public class PrimaryLockerRobot {
    private final List<Locker> lockers;
    private String lockerIsFull = "储物柜已满";
    private String ticketIsInvalid = "非法票据";

    public PrimaryLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket checkIn(Bag bag) {
        for (Locker locker:lockers) {
            if (locker.getAvailableCapacity() > 0) {
                return locker.checkIn(bag);
            }
        }
        throw new RuntimeException(lockerIsFull);
    }

    public Bag checkOut(Ticket ticket) {
        for (Locker locker:lockers) {
            try {
               return locker.checkOut(ticket);
            } catch (Exception e) {
                continue;
            }
        }
        throw new RuntimeException(ticketIsInvalid);
    }

}
