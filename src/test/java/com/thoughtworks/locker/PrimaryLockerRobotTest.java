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

    @Test
    public void should_check_in_success_in_second_locker_and_get_a_ticket_when_check_in_given_robot_manage_two_lockers_with_first_locker_is_full_second_locker_have_available_capacity() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(5);
        PrimaryLockerRobot robot = new PrimaryLockerRobot(Arrays.asList(firstLocker, secondLocker));
        robot.checkIn(new Bag());
        Bag myBag = new Bag();

        Ticket ticket = robot.checkIn(myBag);

        Bag checkOutBag = secondLocker.checkOut(ticket);
        Assert.assertSame(checkOutBag, myBag);
        Assert.assertNotNull(ticket);
    }

    @Test(expected = RuntimeException.class)
    public void should_check_in_failed_when_check_in_given_robot_manage_two_lockers_with_no_available_capacity() {
        Locker firstLocker = new Locker(1);
        Locker secondLocker = new Locker(1);
        PrimaryLockerRobot robot = new PrimaryLockerRobot(Arrays.asList(firstLocker, secondLocker));
        robot.checkIn(new Bag());
        robot.checkIn(new Bag());

        try {
            robot.checkIn(new Bag());
        } catch (Exception e) {
            Assert.assertEquals("储物柜已满", e.getMessage());
            throw e;
        }
    }

    @Test
    public void should_check_out_success_when_check_out_given_robot_manage_two_lockers_and_a_valid_ticket() {
        PrimaryLockerRobot robot = new PrimaryLockerRobot(Arrays.asList(new Locker(10), new Locker(5)));
        Bag myBag = new Bag();
        Ticket ticket = robot.checkIn(myBag);

        Bag checkoutBag = robot.checkOut(ticket);

        Assert.assertSame(checkoutBag, myBag);
    }

    @Test(expected = RuntimeException.class)
    public void should_check_out_failed_when_check_out_given_robot_manage_two_lockers_and_an_invalid_ticket() {
        PrimaryLockerRobot robot = new PrimaryLockerRobot(Arrays.asList(new Locker(10), new Locker(5)));
        Ticket ticket = new Ticket();

        try {
            robot.checkOut(ticket);
        } catch (Exception e) {
            Assert.assertEquals("非法票据", e.getMessage());
            throw e;
        }

    }
}
