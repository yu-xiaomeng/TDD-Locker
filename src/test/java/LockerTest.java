import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LockerTest {
    @Test
    public void should_check_in_successful_and_get_a_ticket_when_check_in_given_locker_is_not_full() {
        // Given
        int capacity = 10;
        Locker locker = new Locker();

        // When
        String ticket = locker.checkIn();

        // Then
        assertThat(ticket).isNotNull();

    }
}
