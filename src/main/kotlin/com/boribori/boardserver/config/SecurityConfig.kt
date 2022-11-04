package com.boribori.boardserver.config

import com.boribori.boardserver.auth.JwtAuthenticationFilter
import com.boribori.boardserver.auth.JwtProperties
import com.boribori.boardserver.auth.JwtProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.anonymous()
                .and()
                .formLogin().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(JwtAuthenticationFilter(JwtProvider(JwtProperties())), UsernamePasswordAuthenticationFilter :: class.java)
    }
}