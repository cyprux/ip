import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    //Format of what Bobby Recieves
    private static final DateTimeFormatter INPUT_DATE =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //Output what Bobby shows
    private static final DateTimeFormatter DISPLAY_DATE =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    public static LocalDate parseDate(String raw) throws BobbyException {
        try {
            return LocalDate.parse(raw.trim(), INPUT_DATE);
        } catch (Exception e) {
            throw new BobbyException("Invalid date! Use yyyy-mm-dd format.");
        }
    }

    public static String formatDate(LocalDate date) {
        return date.format(DISPLAY_DATE);
    }
}
