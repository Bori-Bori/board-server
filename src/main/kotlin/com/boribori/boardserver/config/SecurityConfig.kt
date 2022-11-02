package com.boribori.boardserver.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http.anonymous()
                .and()
                .formLogin().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
    }
}