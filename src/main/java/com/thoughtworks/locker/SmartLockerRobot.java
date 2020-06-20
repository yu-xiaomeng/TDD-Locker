package com.thoughtworks.locker;

import java.util.ArrayList;
import java.util.List;

public class SmartLockerRobot {

    private final List<Locker> lockers;

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket checkIn(Bag bag) {
        assert (null != lockers);
        return lockers.get(0).checkIn(bag);
    }
}
