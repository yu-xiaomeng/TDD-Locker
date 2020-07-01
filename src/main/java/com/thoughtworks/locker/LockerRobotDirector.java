package com.thoughtworks.locker;

import com.thoughtworks.locker.utils.CollectionUtil;
import com.thoughtworks.locker.utils.PrintUtil;

import java.util.List;

public class LockerRobotDirector {
    private final List<LockerRobotManager> lockerRobotManagers;

    public LockerRobotDirector(List<LockerRobotManager> lockerRobotManagers) {
        assert (!CollectionUtil.isNullOrEmpty(lockerRobotManagers));
        this.lockerRobotManagers = lockerRobotManagers;
    }

    public String print() {
        return PrintUtil.printReports(lockerRobotManagers, null);
    }
}
