package com.thoughtworks.locker;

import java.util.List;

public class SmartLockerRobot extends AbstractLockerRobot {

    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    protected Locker selectValidLocker() {
        Locker selectedLocker = null;
        for (Locker locker : getLockers()) {
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
