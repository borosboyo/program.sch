// Role.java
package hu.bme.aut.programsch.config.security;

import hu.bme.aut.programsch.config.authsch.AuthSchAPI;
import hu.bme.aut.programsch.config.authsch.Role;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


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
                .antMatchers("/profile", "/profile/**", "/stats").hasRole(Role.USER.name())
                .antMatchers("/configure/**").hasAnyRole(Role.LEADER.name(), Role.ADMIN.name())
                .antMatchers("/admin", "/admin/**").hasRole(Role.ADMIN.name())
                .and()
                .formLogin()
                .loginPage("/login");

        http.cors();
    }

    protected void configure(AuthenticationManagerBuilder auth) {
        if (auth != null) {
            auth.eraseCredentials(true);
        }

    }

    @Bean
    @ConfigurationProperties(prefix = "authsch")
    public AuthSchAPI authSchApi() {
        return new AuthSchAPI();
    }
}
