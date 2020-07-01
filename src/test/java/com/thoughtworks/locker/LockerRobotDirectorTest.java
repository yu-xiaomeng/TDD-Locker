package com.thoughtworks.locker;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LockerRobotDirectorTest {

    @Test
    public void should_successful_when_director_print_report_given_director_manage_one_manager_and_manager_manage_one_locker() {
        LockerRobotManager robotManager = new LockerRobotManager(null, Arrays.asList(new Locker(10)));
        robotManager.checkIn(new Bag());
        robotManager.checkIn(new Bag());
        LockerRobotDirector director = new LockerRobotDirector(Arrays.asList(robotManager));

        String report = director.print();

        Assert.assertEquals("M 8 10\n\tL 8 10", report);
    }

    @Test
    public void should_successful_when_director_print_report_given_director_manage_one_manager_and_manager_manage_one_robot() {
        AbstractLockerRobot robot = new PrimaryLockerRobot(Arrays.asList(new Locker(5)));
        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(robot), null);
        manager.checkIn(new Bag());
        manager.checkIn(new Bag());
        LockerRobotDirector director = new LockerRobotDirector(Arrays.asList(manager));

        String report = director.print();

        Assert.assertEquals("M 3 5\n\tR 3 5\n\t\tL 3 5", report);
    }

    @Test
    public void should_successful_when_director_print_report_given_director_manage_one_manager_and_manager_manage_one_robot_and_one_locker() {
        AbstractLockerRobot robot = new PrimaryLockerRobot(Arrays.asList(new Locker(5)));
        Locker locker = new Locker(8);
        LockerRobotManager manager = new LockerRobotManager(Arrays.asList(robot), Arrays.asList(locker));
        manager.checkIn(new Bag());
        manager.checkIn(new Bag());
        locker.checkIn(new Bag());
        locker.checkIn(new Bag());
        locker.checkIn(new Bag());
        LockerRobotDirector director = new LockerRobotDirector(Arrays.asList(manager));

        String report = director.print();

        Assert.assertEquals("M 8 13\n\tL 5 8\n\tR 3 5\n\t\tL 3 5", report);
    }
}
