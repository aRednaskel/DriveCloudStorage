package com.projects.storage.DriveCloudStorage.config;

import com.projects.storage.DriveCloudStorage.model.User;
import com.projects.storage.DriveCloudStorage.services.interfaces.UserService;
import com.projects.storage.DriveCloudStorage.services.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .permitAll()
          .and().logout()
                .permitAll();

        http.formLogin()
                .defaultSuccessUrl("/home", true);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void userExamples() {
        User user1 = new User(1, "John", "John", "John" , "John", "John");
        userService.create(user1);

    }
}
