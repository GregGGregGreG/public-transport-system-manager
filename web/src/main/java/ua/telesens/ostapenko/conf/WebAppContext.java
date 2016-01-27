package ua.telesens.ostapenko.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author root
 * @since 29.11.15
 */
@Configuration
@Import({
        WebAppConfig.class, ConfigFromTiles.class
})
public class WebAppContext {

    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";
    private static final String VALIDATION_MESSAGE_SOURCE_BASE_NAME = "i18n/ValidationMessages";

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        List<String> fileList = new ArrayList<>();
        fileList.add("classpath:" + MESSAGE_SOURCE_BASE_NAME);
        fileList.add("classpath:" + VALIDATION_MESSAGE_SOURCE_BASE_NAME);

        String[] files = fileList.toArray(new String[fileList.size()]);

        messageSource.setBasenames(files);
        messageSource.setCacheSeconds(1);

        return messageSource;
    }

    // JSR-303 Validation
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        validator.setParameterNameDiscoverer(new LocalVariableTableParameterNameDiscoverer());
        return validator;
    }

    @Bean
    @Autowired
    MethodValidationPostProcessor getValidationPostProcessor(LocalValidatorFactoryBean validator) {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator);
        return processor;
    }

}
