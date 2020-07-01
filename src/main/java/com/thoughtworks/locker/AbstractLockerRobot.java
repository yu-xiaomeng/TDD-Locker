package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.LockerFullException;
import com.thoughtworks.locker.exception.TicketInvalidException;
import com.thoughtworks.locker.utils.PrintUtil;

import java.util.List;

public abstract class AbstractLockerRobot implements ReportPrint {
    private final List<Locker> lockers;

    public AbstractLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket checkIn(Bag bag) {
        Locker selectedLocker = this.selectValidLocker();
        if (selectedLocker == null) {
            throw new LockerFullException();
        }
        return selectedLocker.checkIn(bag);
    }

    public Bag checkOut(Ticket ticket) {
        for (Locker locker : lockers) {
            Bag bag = locker.checkOut(ticket);
            if (bag != null) {
                return bag;
            }
        }
        throw new TicketInvalidException();
    }

    public List<Locker> getLockers() {
        return lockers;
    }

    private int getCapacity() {
        return lockers.stream().mapToInt(Locker::getCapacity).sum();
    }

    private int getAvailableCapacity() {
        return lockers.stream().mapToInt(Locker::getAvailableCapacity).sum();
    }

    @Override
    public String print(String tabs) {
        StringBuilder builder = new StringBuilder();
        builder.append(tabs == null ? "" : tabs);
        builder.append(PrintUtil.print("R", getAvailableCapacity(), getCapacity()));
        builder.append("\n");
        builder.append(PrintUtil.printReports(lockers, tabs));
        return builder.toString();
    }

    protected abstract Locker selectValidLocker();
}
