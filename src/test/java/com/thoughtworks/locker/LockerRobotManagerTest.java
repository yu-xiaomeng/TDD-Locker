package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.LockerFullException;
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

    @Test(expected = LockerFullException.class)
    public void should_check_in_fail_when_lockerRobotManager_check_in_given_locker_robot_manager_have_no_robots_and_have_two_locker_and_locker1_locker2_is_full() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        LockerRobotManager manager = new LockerRobotManager(null, Arrays.asList(locker1, locker2));
        locker1.checkIn(new Bag());
        locker2.checkIn(new Bag());
        Bag bag = new Bag();

        manager.checkIn(bag);
    }

    @Test
    public void should_check_in_robot1_when_lockerManager_check_in_given_locker_robot_manager_have_to_robots_and_two_robots_is_not_full() {
        AbstractLockerRobot robot1 = new PrimaryLockerRobot(Arrays.asList(new Locker(1)));
        AbstractLockerRobot robot2 = new SmartLockerRobot(Arrays.asList(new Locker(1)));
        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(robot1, robot2), null);
        Bag bag = new Bag();

        LockerRobotManagerTicket ticket = manager.checkIn(bag);

        Assert.assertEquals(bag, robot1.checkOut(ticket.getTicket()));
    }

    @Test
    public void should_check_in_robot1_when_lockerManager_check_in_given_locker_robot_manager_have_to_robots_and_robot2_is_full() {
        AbstractLockerRobot robot1 = new PrimaryLockerRobot(Arrays.asList(new Locker(1)));
        AbstractLockerRobot robot2 = new SmartLockerRobot(Arrays.asList(new Locker(1)));
        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(robot1, robot2), null);
        robot2.checkIn(new Bag());
        Bag bag = new Bag();

        LockerRobotManagerTicket ticket = manager.checkIn(bag);

        Assert.assertEquals(bag, robot1.checkOut(ticket.getTicket()));
    }

    @Test
    public void should_check_in_robot1_when_lockerManager_check_in_given_locker_robot_manager_have_to_robots_and_robot1_is_full() {
        AbstractLockerRobot robot1 = new PrimaryLockerRobot(Arrays.asList(new Locker(1)));
        AbstractLockerRobot robot2 = new SmartLockerRobot(Arrays.asList(new Locker(1)));
        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(robot1, robot2), null);
        robot1.checkIn(new Bag());
        Bag bag = new Bag();

        LockerRobotManagerTicket ticket = manager.checkIn(bag);

        Assert.assertEquals(bag, robot2.checkOut(ticket.getTicket()));
    }
}
