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
}
