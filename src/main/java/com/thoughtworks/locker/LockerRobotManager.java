package com.thoughtworks.locker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LockerRobotManager {
    private final List<AbstractLockerRobot> robots;
    private final List<Locker> lockers;

    public LockerRobotManager(List<AbstractLockerRobot> robots, List<Locker> lockers) {
        checkParamsValid(robots, lockers);
        this.robots = robots;
        this.lockers = lockers;
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
