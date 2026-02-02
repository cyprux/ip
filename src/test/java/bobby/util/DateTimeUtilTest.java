package bobby.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.time.LocalDate;
import bobby.exception.BobbyException;

public class DateTimeUtilTest {

    @Test
    public void parseDate_validIsoDate_parsesCorrectly() throws Exception {
        LocalDate date = DateTimeUtil.parseDate("2026-02-02");
        assertEquals(LocalDate.of(2026, 2, 2), date);
    }

    @Test
    public void parseDate_trimsWhitespace_parsesCorrectly() throws Exception {
        LocalDate date = DateTimeUtil.parseDate("   2026-12-07   ");
        assertEquals(LocalDate.of(2026, 12, 7), date);
    }

    @Test
    public void parseDate_invalidFormat_throwsBobbyExceptionWithHelpfulMessage() {
        BobbyException ex = assertThrows(BobbyException.class, () -> DateTimeUtil.parseDate("02/02/2026"));
        assertTrue(ex.getMessage().toLowerCase().contains("yyyy"),
                "Expected message to mention yyyy-mm-dd format, but was: " + ex.getMessage());
    }
}
