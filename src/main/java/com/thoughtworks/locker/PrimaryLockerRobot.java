package com.thoughtworks.locker;

import java.util.List;

public class PrimaryLockerRobot {
    private final List<Locker> lockers;
    private String lockerIsFull = "储物柜已满";

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
}
