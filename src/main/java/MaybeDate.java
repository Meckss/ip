import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;


public class MaybeDate {
    private String description;
    private LocalDate date;


    private static final List<DateTimeFormatter> supportedFormats = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("M/dd/yyyy"),
            DateTimeFormatter.ofPattern("MM/d/yyyy"),
            DateTimeFormatter.ofPattern("M/d/yyyy")
    );


    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");


    private MaybeDate(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }


    public static MaybeDate parse(String input) {
        LocalDate temp = null;
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        for(DateTimeFormatter format : supportedFormats) {
            dateTimeFormatterBuilder.appendOptional(format);
        }
        try {
            temp = LocalDate.parse(input, dateTimeFormatterBuilder.toFormatter());
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
            return outputFormat.format(date);
        }
    }
}
