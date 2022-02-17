package com.tvsolutions.configs;

import com.tvsolutions.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(securedEnabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/authenticated/**").authenticated()
//                .antMatchers("/profile/**").hasAuthority("READ_PROFILE")
                .and()
                .formLogin()
//                .loginPage("/login")
//                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    //In-memory User Details
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user = User
//                .builder()
//                .username("user")
//                .password("{bcrypt$2a$12$EE/ZAAWZA3k71/n0DNoDXOzyAcOpSB4lobZaUR3CjPI3mkQi77OkS}")
//                .roles("USER")
//                .build();
//        UserDetails admin = User
//                .builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$EE/ZAAWZA3k71/n0DNoDXOzyAcOpSB4lobZaUR3CjPI3mkQi77OkS")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//
//    }


    // JDBC UserDetails
//    @Bean
//    public JdbcUserDetailsManager userDetailsService(DataSource dataSource) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        boolean userExists = jdbcUserDetailsManager.userExists("user");
//        System.out.println(userExists);
//        return jdbcUserDetailsManager;
//    }

    //DAO Authentication Provider
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}