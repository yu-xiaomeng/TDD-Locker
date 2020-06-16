package com.thoughtworks.locker;

import java.util.List;

public class PrimaryLockerRobot {
    private final List<Locker> lockers;

    public PrimaryLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket checkIn(Bag bag) {
        return lockers.get(0).checkIn(bag);
    }
}
