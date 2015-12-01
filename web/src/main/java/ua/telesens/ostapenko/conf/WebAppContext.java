package ua.telesens.ostapenko.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author root
 * @since 29.11.15
 */
@Configuration
@Import({
        WebApp.class, ConfigFromTiles.class
})
public class WebAppContext {

    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        List<String> fileList = new ArrayList<>();
        fileList.add("classpath:" + MESSAGE_SOURCE_BASE_NAME);

        String[] files = fileList.toArray(new String[fileList.size()]);

        messageSource.setBasenames(files);
        messageSource.setCacheSeconds(1);

        return messageSource;
    }
}
