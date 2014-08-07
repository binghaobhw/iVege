package wbh.wilfred.ivege.web.typehandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class DateTimeObjectMapper extends ObjectMapper {
    public DateTimeObjectMapper() {
        SimpleModule module = new SimpleModule("DateTime");
        module.addSerializer(new DateTimeSerializer());
        this.registerModule(module);
    }
}
