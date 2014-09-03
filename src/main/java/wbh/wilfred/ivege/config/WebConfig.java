package wbh.wilfred.ivege.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import wbh.wilfred.ivege.web.typehandler.DateTimeObjectMapper;
import wbh.wilfred.ivege.web.typehandler.StringToDateTimeConverter;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("wbh.wilfred.ivege.web")
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(100000);
        return resolver;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateTimeConverter());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(messageConverter());
    }
    @Bean
    public MappingJackson2HttpMessageConverter messageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(dateTimeObjectMapper());
        return converter;
    }
    @Bean
    public DateTimeObjectMapper dateTimeObjectMapper() {
        return new DateTimeObjectMapper();
    }
}
