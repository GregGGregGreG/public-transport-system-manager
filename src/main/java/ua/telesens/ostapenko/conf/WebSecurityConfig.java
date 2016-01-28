package ua.telesens.ostapenko.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.telesens.ostapenko.auth.security.CustomUserDetailsServiceFromFindByEmail;
import ua.telesens.ostapenko.auth.security.CustomUserDetailsServiceFromFindByName;

import javax.sql.DataSource;

/**
 * @author root
 * @since 27.01.16
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    CustomUserDetailsServiceFromFindByEmail findByEmail;

    @Autowired
    CustomUserDetailsServiceFromFindByName findByName;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = passwordEncoder();
        auth
                .userDetailsService(findByEmail)
                .passwordEncoder(encoder)
                .and()
                .userDetailsService(findByName)
                .passwordEncoder(encoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .headers()
                .cacheControl()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/join/**",
                        "/signup_check/**",
                        "/static/**",
                        "/favicon.ico"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/account")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login?expired");

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
