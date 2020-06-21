package com.thoughtworks.locker;

import java.util.List;


public class PrimaryLockerRobot extends AbstractLockerRobot {

    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    protected Locker selectValidLocker() {
        for (Locker locker : getLockers()) {
            if (locker.getAvailableCapacity() > 0) {
                return locker;
            }
        }
        return null;
    }
}
