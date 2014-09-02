package wbh.wilfred.ivege.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@ComponentScan("wbh.wilfred.ivege.service")
@Import(DataConfig.class)
public class BusinessConfig {

}
