package wbh.wilfred.ivege.web.typehandler;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

public class StringToDateTimeConverter implements Converter<String, DateTime> {
    @Override
    public DateTime convert(String source) {
        DateTimeZone dateTimeZone = DateTimeZone.forOffsetHours(8);
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern
                ("yyyy-MM-dd HH:mm:ss").withZone(dateTimeZone);
        DateTime dateTime = dateTimeFormatter.parseDateTime(source);
        return dateTime;
    }
}
