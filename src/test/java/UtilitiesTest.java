import controllers.Utilities;
import entities.Clinic;
import org.junit.jupiter.api.Test;
import repositories.interfaces.ClinicRepository;

import javax.persistence.EntityManagerFactory;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

    @Test
    void dayReceiver() {
        int correctDay = 30;
        int incorrectDay = 31;
        assertDoesNotThrow(() -> LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), correctDay, 0, 0));
        assertThrows(DateTimeException.class, () -> LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), incorrectDay, 0, 0));
    }
}
