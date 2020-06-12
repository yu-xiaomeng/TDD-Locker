package com.thoughtworks.locker;

import org.assertj.core.api.Assertions;
import org.junit.Test;

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
}
