package ua.telesens.ostapenko.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author root
 * @since 29.11.15
 */
@Configuration
@EnableWebMvc
@ComponentScan("ua.telesens.ostapenko")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    private static final String RESOURCES_LOCATION = "/static/";
    private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(RESOURCES_HANDLER)
                .addResourceLocations(RESOURCES_LOCATION)
                .setCachePeriod(345600);
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/static/images/favicon.ico");
    }
}
