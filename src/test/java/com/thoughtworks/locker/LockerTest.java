package com.thoughtworks.locker;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class LockerTest {
    @Test
    public void should_check_in_successful_and_get_a_ticket_when_check_in_given_locker_is_not_full() {
        // Given
        Locker locker = new Locker();

        // When
        String ticket = locker.checkIn();

        // Then
        Assertions.assertThat(ticket).isNotNull();

    }

    @Test(expected = RuntimeException.class)
    public void should_check_in_failed_when_check_in_given_locker_is_full() {
        // Given
        Locker locker = new Locker();
        locker.setFull(true);

        // When
        try {
            String ticket = locker.checkIn();
        } catch (Exception e) {
            // Then
            Assert.assertEquals("储物柜已满", e.getMessage());
            throw e;
        }

    }

    @Test
    public void should_check_out_successful_when_check_out_given_ticket_is_valid() {
        // Given
        Locker locker = new Locker();
        String ticket = UUID.randomUUID().toString();

        // When
        boolean checkOutSuccessful = locker.checkOut(ticket);

        // Then
        Assert.assertTrue(checkOutSuccessful);

    }

}
