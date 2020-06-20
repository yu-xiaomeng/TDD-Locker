package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.LockerFullException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SmartLockerRobotTest {

    @Test
    public void should_check_in_success_in_1st_locker_and_get_ticket_when_smart_robot_check_in_given_locker1_capacity_is_2_and_locker2_capacity_is_1() {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));

        Bag bag = new Bag();
        Ticket ticket = smartLockerRobot.checkIn(bag);

        Assert.assertNotNull(ticket);
        Assert.assertEquals(bag, locker1.checkOut(ticket));
    }

    @Test
    public void should_check_in_success_in_second_locker_and_get_ticket_when_smart_robot_check_in_given_locker1_capacity_is_1_and_locker2_capacity_is_2() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));

        Bag bag = new Bag();
        Ticket ticket = smartLockerRobot.checkIn(bag);

        Assert.assertNotNull(ticket);
        Assert.assertEquals(bag, locker2.checkOut(ticket));
    }

    @Test
    public void should_check_in_success_in_1st_locker_and_get_ticket_when_smart_robot_check_in_given_locker1_capacity_is_2_and_locker2_capacity_is_2() {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));

        Bag bag = new Bag();
        Ticket ticket = smartLockerRobot.checkIn(bag);

        Assert.assertNotNull(ticket);
        Assert.assertEquals(bag, locker1.checkOut(ticket));
    }

    @Test(expected = LockerFullException.class)
    public void should_check_in_fail_when_smart_robot_check_in_given_locker1_and_locker2_is_full() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        smartLockerRobot.checkIn(new Bag());
        smartLockerRobot.checkIn(new Bag());

        smartLockerRobot.checkIn(new Bag());
    }

    @Test
    public void should_checkout_success_when_smart_robot_checkout_given_valid_ticket() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = smartLockerRobot.checkIn(bag);

        Bag checkoutBag = smartLockerRobot.checkout(ticket);

        Assert.assertNotNull(checkoutBag);
        Assert.assertEquals(bag, checkoutBag);
    }
}
