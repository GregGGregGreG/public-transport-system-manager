package ua.telesens.ostapenko.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * @author root
 * @since 29.11.15
 */
@Configuration
public class ConfigFromTiles {

    private static final String TILES_DEFINITION = "/WEB-INF/defs/general.xml";

    /**
     * Introduce a Tiles view resolver, this is a convenience implementation that extends URLBasedViewResolver.
     *
     * @return tiles view resolver
     */
    @Bean
    public ViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(TilesView.class);
        return viewResolver;
    }

    /**
     * Initialise Tiles on application startup and identify the location of the tiles configuration file, tiles.xml.
     *
     * @return tiles configurer
     */
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(TILES_DEFINITION);
        configurer.setCheckRefresh(true);
        return configurer;
    }
}
