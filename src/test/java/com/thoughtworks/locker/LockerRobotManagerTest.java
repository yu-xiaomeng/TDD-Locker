package com.thoughtworks.locker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LockerRobotManagerTest {

    @Test(expected = AssertionError.class)
    public void should_throw_exception_when_new_robotLockerManager_given_robot1_robot2_two_locker_and_robotLockManager_manage_robot1_or_robot2_locker() {
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(1));
        new LockerRobotManager(Arrays.asList(new PrimaryLockerRobot(lockers), new SmartLockerRobot(lockers)), lockers);
    }
}
