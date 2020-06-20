package com.thoughtworks.locker;

import java.util.List;

public class SmartLockerRobot {

    private final List<Locker> lockers;

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket checkIn(Bag bag) {
        assert (null != lockers);
        Locker selectedLocker = null;
        for (Locker locker : lockers) {
            if (selectedLocker == null) {
                selectedLocker = locker;
            } else {
                selectedLocker = selectedLocker.getAvailableCapacity() < locker.getAvailableCapacity()
                        ? locker : selectedLocker;
            }
        }
        return selectedLocker.checkIn(bag);
    }
}
