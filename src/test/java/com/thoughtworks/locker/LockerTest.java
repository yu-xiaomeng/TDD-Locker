package com.thoughtworks.locker;

import org.junit.Assert;
import org.junit.Test;

public class LockerTest {
    @Test
    public void should_check_in_successful_and_get_a_ticket_when_check_in_given_locker_is_not_full() {
        // Given
        Locker locker = new Locker(10);

        // When
        Ticket ticket = locker.checkIn(new Bag());

        // Then
        Assert.assertNotNull(ticket);

    }

    @Test(expected = RuntimeException.class)
    public void should_check_in_failed_when_check_in_given_locker_is_full() {
        // Given
        Locker locker = new Locker(1);
        locker.checkIn(new Bag());

        // When
        try {
            locker.checkIn(new Bag());
        } catch (Exception e) {
            // Then
            Assert.assertEquals("储物柜已满", e.getMessage());
            throw e;
        }

    }

    @Test
    public void should_check_out_successful_when_check_out_given_ticket_is_valid() {
        // Given
        Locker locker = new Locker(10);
        Bag checkInBag = new Bag();
        Ticket ticket = locker.checkIn(checkInBag);

        // When
        Bag checkOutBag = locker.checkOut(ticket);

        // Then
        Assert.assertSame(checkOutBag, checkInBag);

    }

    @Test(expected = RuntimeException.class)
    public void should_check_out_failed_when_check_out_given_ticket_is_invalid() {
        // Given
        Locker locker = new Locker(10);
        Ticket ticket = new Ticket();

        // When
        try {
            locker.checkOut(ticket);
        } catch (Exception e) {
            // Then
            Assert.assertEquals("非法票据", e.getMessage());
            throw e;
        }

    }

    @Test(expected = RuntimeException.class)
    public void should_check_out_failed_when_check_out_given_ticket_is_reused() {
        Locker locker = new Locker(10);
        Bag checkInBag = new Bag();
        Ticket ticket = locker.checkIn(checkInBag);
        locker.checkOut(ticket);

        try {
            locker.checkOut(ticket);
        } catch (Exception e) {
            Assert.assertEquals("非法票据", e.getMessage());
            throw e;
        }

    }

}
