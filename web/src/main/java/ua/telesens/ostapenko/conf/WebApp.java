package ua.telesens.ostapenko.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author root
 * @since 29.11.15
 */
@Configuration
@EnableWebMvc
@ComponentScan("ua.telesens.ostapenko.systemimitation")
public class WebApp extends WebMvcConfigurerAdapter {


}
