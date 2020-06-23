package com.thoughtworks.locker;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LockerRobotManagerTest {

    @Test(expected = AssertionError.class)
    public void should_throw_exception_when_new_robotLockerManager_given_robot1_robot2_two_locker_and_robotLockManager_manage_robot1_or_robot2_locker() {
        List<Locker> lockers = Arrays.asList(new Locker(1), new Locker(1));
        new LockerRobotManager(Arrays.asList(new PrimaryLockerRobot(lockers), new SmartLockerRobot(lockers)), lockers);
    }

    @Test
    public void should_check_in_locker1_when_lockerRobotManager_check_in_given_locker_robot_manager_have_no_robots_and_have_two_locker() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(null, Arrays.asList(locker1, locker2));
        Bag bag = new Bag();

        LockerRobotManagerTicket ticket = manager.checkIn(bag);

        Assert.assertEquals(bag, locker1.checkOut(ticket.getTicket()));
    }

    @Test
    public void should_check_in_locker1_when_lockerRobotManager_check_in_given_locker_robot_manager_have_no_robots_and_have_two_locker_and_locker2_is_full() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(null, Arrays.asList(locker1, locker2));
        locker2.checkIn(new Bag());
        Bag bag = new Bag();

        LockerRobotManagerTicket ticket = manager.checkIn(bag);

        Assert.assertEquals(bag, locker1.checkOut(ticket.getTicket()));
    }

    @Test
    public void should_check_in_locker1_when_lockerRobotManager_check_in_given_locker_robot_manager_have_no_robots_and_have_two_locker_and_locker1_is_full() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(null, Arrays.asList(locker1, locker2));
        locker1.checkIn(new Bag());
        Bag bag = new Bag();

        LockerRobotManagerTicket ticket = manager.checkIn(bag);

        Assert.assertEquals(bag, locker2.checkOut(ticket.getTicket()));
    }
}
