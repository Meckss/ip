import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;


public class MaybeDate {
    private String description;
    private Temporal date;


    private static final List<DateTimeFormatter> supportedFormats = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("M/dd/yyyy"),
            DateTimeFormatter.ofPattern("MM/d/yyyy"),
            DateTimeFormatter.ofPattern("M/d/yyyy")
    );


    private static final DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter outputDateTimeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");


    private MaybeDate(LocalDateTime date, String description) {
        this.date = date;
        this.description = description;
    }


    public static MaybeDate parse(String input) {
        LocalDateTime temp = null;
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        for(DateTimeFormatter format : supportedFormats) {
            dateTimeFormatterBuilder.appendOptional(format);
        }
        dateTimeFormatterBuilder.optionalStart().appendOptional(DateTimeFormatter.ofPattern(" HH:mm")).appendOptional(DateTimeFormatter.ofPattern(" HHmm")).optionalEnd();
        try {
            temp = LocalDateTime.parse(input, dateTimeFormatterBuilder.toFormatter());
            return new MaybeDate(temp, null);
        } catch (DateTimeParseException e) {

        }

        return new MaybeDate(null, input);
    }


    @Override
    public String toString() {
        if(date == null) {
            return description;
        } else {
            if(date instanceof LocalDate){
                return outputDateFormat.format(date);
            }
            if( date instanceof LocalDateTime) {
                return outputDateTimeFormat.format(date);
            }
        }
        return description;
    }
}
