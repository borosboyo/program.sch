// Role.java
package hu.bme.aut.programsch.config;

import hu.bme.aut.programsch.config.authsch.AuthSchAPI;
import hu.bme.aut.programsch.config.authsch.Role;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index", "/circle", "/items", "/search/**",
                        "/item/**", "/items/**", "/provider/**", "/p/**", "/enableFilters").permitAll()
                .antMatchers("/loggedin", "/logout").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/disableFilters").permitAll()
                .antMatchers("/profile", "/profile/**", "/stats").hasRole(Role.USER.name())
                .antMatchers("/configure/**").hasAnyRole(Role.LEADER.name(), Role.ADMIN.name())
                .antMatchers("/admin", "/admin/**").hasRole(Role.ADMIN.name())
                .and()
                .formLogin()
                .loginPage("/login");

        http.cors().and().antMatcher("/enableFilters").cors().and().antMatcher("/disableFilters").cors();

    }

    protected void configure(AuthenticationManagerBuilder auth) {
        if (auth != null) {
            auth.eraseCredentials(true);
        }
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    @ConfigurationProperties(prefix = "authsch")
    public AuthSchAPI authSchApi() {
        return new AuthSchAPI();
    }
}
