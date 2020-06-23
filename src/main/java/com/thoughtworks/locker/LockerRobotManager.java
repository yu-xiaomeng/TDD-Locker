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
        Locker locker = this.lockers.stream().filter(l -> !l.isFull()).findFirst().orElseThrow(LockerFullException::new);
        return new LockerRobotManagerTicket(locker.checkIn(bag));
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
