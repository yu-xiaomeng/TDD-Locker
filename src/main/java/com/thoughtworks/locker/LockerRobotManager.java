package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.LockerFullException;
import com.thoughtworks.locker.exception.TicketInvalidException;
import com.thoughtworks.locker.utils.CollectionUtil;
import com.thoughtworks.locker.utils.PrintUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LockerRobotManager implements ReportPrint {
    private final List<AbstractLockerRobot> robots;
    private final List<Locker> lockers;
    private final List<Locker> allLockers;

    public LockerRobotManager(List<AbstractLockerRobot> robots, List<Locker> lockers) {
        checkParamsValid(robots, lockers);
        this.robots = robots;
        this.lockers = lockers;
        this.allLockers = new ArrayList<>();
        if (!CollectionUtil.isNullOrEmpty(robots)) {
            for (AbstractLockerRobot robot : robots) {
                this.allLockers.addAll(robot.getLockers());
            }
        }
        if (!CollectionUtil.isNullOrEmpty(lockers)) {
            this.allLockers.addAll(lockers);
        }
    }

    public LockerRobotManagerTicket checkIn(Bag bag) {
        Locker locker = getRobotLocker();
        if (locker == null && !CollectionUtil.isNullOrEmpty(lockers)) {
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

    private int getCapacity() {
        return allLockers.stream().mapToInt(Locker::getCapacity).sum();
    }

    private int getAvailableCapacity() {
        return allLockers.stream().mapToInt(Locker::getAvailableCapacity).sum();
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append(PrintUtil.print("M", getAvailableCapacity(), getCapacity()));
        builder.append("\n\t");
        builder.append(PrintUtil.printReports(lockers));
        return builder.toString();
    }

    private Locker getRobotLocker() {
        if (CollectionUtil.isNullOrEmpty(this.robots)) {
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
        if (CollectionUtil.isNullOrEmpty(robots) || CollectionUtil.isNullOrEmpty(lockers)) {
            return;
        }
        List<Locker> allRobotLockers = new ArrayList<>();
        robots.forEach(r -> {allRobotLockers.addAll(r.getLockers());});
        assert (!allRobotLockers.stream().filter(l -> lockers.contains(l)).findFirst().isPresent());
    }
}
