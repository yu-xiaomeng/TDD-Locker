package com.thoughtworks.locker;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class PrimaryLockerRobotTest {
    @Test
    public void should_check_in_success_in_first_locker_and_get_a_ticket_when_check_in_given_robot_manage_two_lockers_with_available_capacity() {
        Locker firstLocker = new Locker(10);
        PrimaryLockerRobot robot = new PrimaryLockerRobot(Arrays.asList(firstLocker, new Locker(5)));
        Bag myBag = new Bag();

        Ticket ticket = robot.checkIn(myBag);

        Bag checkOutBag = firstLocker.checkOut(ticket);
        Assert.assertSame(checkOutBag, myBag);
        Assert.assertNotNull(ticket);
    }
}
