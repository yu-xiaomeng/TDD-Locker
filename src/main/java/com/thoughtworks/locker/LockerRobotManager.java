package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.LockerFullException;

import java.util.ArrayList;
import java.util.List;

public class LockerRobotManager {
    private final List<AbstractLockerRobot> robots;
    private final List<Locker> lockers;

    public LockerRobotManager(List<AbstractLockerRobot> robots, List<Locker> lockers) {
        checkParamsValid(robots, lockers);
        this.robots = robots;
        this.lockers = lockers;
    }

    public LockerRobotManagerTicket checkIn(Bag bag) {
        Locker locker = getRobotLocker();
        if (locker == null && !isNullOrEmpty(lockers)) {
            locker = this.lockers.stream().filter(l -> !l.isFull()).findFirst().orElseThrow(LockerFullException::new);
        }
        if (locker == null) {
            throw new LockerFullException();
        }
        return new LockerRobotManagerTicket(locker.checkIn(bag));
    }

    private Locker getRobotLocker() {
        if (isNullOrEmpty(this.robots)) {
            return null;
        }
        for (AbstractLockerRobot robot : robots) {
            Locker locker = robot.selectValidLocker();
            if (locker != null) {
                return locker;
            }
        }
        return null;
    }

    private void checkParamsValid(List<AbstractLockerRobot> robots, List<Locker> lockers) {
        if (isNullOrEmpty(robots) || isNullOrEmpty(lockers)) {
            return;
        }
        List<Locker> allRobotLockers = new ArrayList<>();
        robots.forEach(r -> {allRobotLockers.addAll(r.getLockers());});
        assert (!allRobotLockers.stream().filter(l -> lockers.contains(l)).findFirst().isPresent());
    }

    private boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}
