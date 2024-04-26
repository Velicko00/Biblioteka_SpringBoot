package com.springboot.biblioteka.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class MySecurityConfig {


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select korisnicko_ime, lozinka, aktivan from korisnik where korisnicko_ime=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select korisnicko_ime, rola from korisnik where korisnicko_ime=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET, "/korisnik").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.GET, "/korisnik/clanovi").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.POST, "/korisnik").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.PUT, "/korisnik").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.DELETE, "/korisnik/**").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.GET, "/knjiga").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.GET, "/knjiga/pozajmljene").hasAuthority("clan")
                        .requestMatchers(HttpMethod.GET, "/knjiga/kolicina").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.POST, "/knjiga").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.PUT, "/knjiga").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.DELETE, "/knjiga/**").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.GET, "/pretplata").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.GET, "/pretplata/stanje").hasAuthority("clan")
                        .requestMatchers(HttpMethod.POST, "/pretplata").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.PUT, "/pretplata").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.DELETE, "/pretplata/**").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.GET, "/evidencija").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.POST, "/evidencija").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.PUT, "/evidencija").hasAuthority("radnik")
                        .requestMatchers(HttpMethod.DELETE, "/evidencija").hasAuthority("radnik")
                        .anyRequest().permitAll());
                        //privilegije

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf-> csrf.disable());

        return http.build();
    }

}
