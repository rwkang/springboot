package shop.onekorea.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public StackTraceElement getStackTraceElement() {
        return Thread.currentThread().getStackTrace()[1];
    }

}
