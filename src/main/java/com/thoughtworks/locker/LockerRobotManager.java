package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.LockerFullException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LockerRobotManager {
    private final List<AbstractLockerRobot> robots;
    private final List<Locker> lockers;
    private final List<Locker> allLockers;

    public LockerRobotManager(List<AbstractLockerRobot> robots, List<Locker> lockers) {
        checkParamsValid(robots, lockers);
        this.robots = robots;
        this.lockers = lockers;
        this.allLockers = new ArrayList<>();
        if (!isNullOrEmpty(robots)) {
            for (AbstractLockerRobot robot : robots) {
                this.allLockers.addAll(robot.getLockers());
            }
        }
        if (!isNullOrEmpty(lockers)) {
            this.allLockers.addAll(lockers);
        }
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

    public Bag checkOut(LockerRobotManagerTicket ticket) {
        return this.allLockers.stream().map(l -> l.checkOut(ticket.getTicket()))
                .filter(Objects::nonNull).findFirst().orElseThrow(TicketInvalidException::new);
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
