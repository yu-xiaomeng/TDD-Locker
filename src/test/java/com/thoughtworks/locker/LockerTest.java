package com.thoughtworks.locker;

import org.junit.Assert;
import org.junit.Test;

public class LockerTest {
    @Test
    public void should_check_in_successful_and_get_a_ticket_when_check_in_given_locker_is_not_full() {
        Locker locker = new Locker(10);

        Ticket ticket = locker.checkIn(new Bag());

        Assert.assertNotNull(ticket);

    }

    @Test(expected = RuntimeException.class)
    public void should_check_in_failed_when_check_in_given_locker_is_full() {
        Locker locker = new Locker(1);
        locker.checkIn(new Bag());

        try {
            locker.checkIn(new Bag());
        } catch (Exception e) {
            Assert.assertEquals("储物柜已满", e.getMessage());
            throw e;
        }

    }

    @Test
    public void should_check_out_successful_when_check_out_given_ticket_is_valid() {
        Locker locker = new Locker(10);
        Bag checkInBag = new Bag();
        Ticket ticket = locker.checkIn(checkInBag);

        Bag checkOutBag = locker.checkOut(ticket);

        Assert.assertSame(checkOutBag, checkInBag);

    }

    @Test
    public void should_check_out_failed_when_check_out_given_ticket_is_invalid() {
        Locker locker = new Locker(10);
        Ticket ticket = new Ticket();

        Bag bag = locker.checkOut(ticket);
        Assert.assertNull(bag);
    }

    @Test
    public void should_check_out_failed_when_check_out_given_ticket_is_reused() {
        Locker locker = new Locker(10);
        Bag checkInBag = new Bag();
        Ticket ticket = locker.checkIn(checkInBag);
        locker.checkOut(ticket);
        Bag bag = locker.checkOut(ticket);
        Assert.assertNull(bag);
    }

}
