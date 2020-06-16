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
            try {
                return locker.checkIn(bag);
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }
}
