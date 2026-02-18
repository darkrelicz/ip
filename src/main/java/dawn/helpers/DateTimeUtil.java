package dawn.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
AI USAGE
AI identified that the datetime functions common to Deadline 
and Event tasks can be extracted into this DateTimeUtil class
*/
public class DateTimeUtil {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Converts a String value to LocalDateTime object
     * @param strDate The datetime value in String
     * @return The datetime in as a LocalDateTime object
     * @throws DateTimeParseException If datetime is not in the correct format
     */
    public static LocalDateTime stringToDateTime(String strDate) throws DateTimeParseException {
        assert !strDate.toString().isEmpty() : "date time should not be empty";
        LocalDateTime dateTime = LocalDateTime.parse(strDate, FORMATTER);
        return dateTime;
    }
}
