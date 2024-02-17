package com.udemy.project.demo.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        httpSecurity.csrf().disable().authorizeHttpRequests(authorize -> {
//            authorize.anyRequest().authenticated();
//        }).httpBasic(Customizer.withDefaults());

        httpSecurity.csrf(httpSecurityCsrfConfigurer -> {
            try {
                httpSecurityCsrfConfigurer.disable().authorizeHttpRequests(authorize->{
//                    //who has role ADMIN only can access this url with POST method
//                    authorize.requestMatchers(HttpMethod.POST, "/api/todo/**").hasRole("ADMIN");
//
//                    //authorize.requestMatchers(HttpMethod.GET, "/api/todo/**").hasAnyRole("ADMIN", "USER");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/todo/**").permitAll();
//
//                    authorize.requestMatchers(HttpMethod.PATCH, "/api/todo/**").hasAnyRole("ADMIN", "USER");
//
//                    authorize.requestMatchers(HttpMethod.DELETE, "/api/todo/**").hasRole("ADMIN");
//
//                    authorize.requestMatchers(HttpMethod.PUT, "/api/todo/**").hasRole("ADMIN");

                    authorize.anyRequest().authenticated();
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    public AuthenticationManager authenticationManager(AuthenticationConfiguration authentic) throws Exception {
        return authentic.getAuthenticationManager();
    }


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user = User.builder().username("user1").password(passwordEncoder().encode("user1")).roles("USER").build();
//
//        UserDetails admin = User.builder().username("admin1").password(passwordEncoder().encode("admin1")).roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(user,admin);
//
//    }

}
