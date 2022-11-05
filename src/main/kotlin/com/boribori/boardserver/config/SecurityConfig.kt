package com.boribori.boardserver.config

import com.boribori.boardserver.auth.JwtAuthenticationFilter
import com.boribori.boardserver.auth.JwtProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
class SecurityConfig(
        private val jwtProvider: JwtProvider
): WebSecurityConfigurerAdapter() {


    override fun configure(web: WebSecurity) {
        super.configure(web)
        web.ignoring().antMatchers("/api/board/**")
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        //http.httpBasic().disable(); // 일반적인 루트가 아닌 다른 방식으로 요청시 거절, header에 id, pw가 아닌 token(jwt)을 달고 간다. 그래서 basic이 아닌 bearer를 사용한다.
        //http.httpBasic().disable(); // 일반적인 루트가 아닌 다른 방식으로 요청시 거절, header에 id, pw가 아닌 token(jwt)을 달고 간다. 그래서 basic이 아닌 bearer를 사용한다.
        http.httpBasic().disable()
                .authorizeRequests() // 요청에 대한 사용권한 체크
                .antMatchers("/test").permitAll()
                .antMatchers("/api/board").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .addFilterBefore(JwtAuthenticationFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter::class.java) // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다

        // + 토큰에 저장된 유저정보를 활용하여야 하기 때문에 CustomUserDetailService 클래스를 생성합니다.
        // + 토큰에 저장된 유저정보를 활용하여야 하기 때문에 CustomUserDetailService 클래스를 생성합니다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)



//        http.anonymous()
//                .and()
//                .formLogin().disable()
//                .authorizeRequests()
//                .anyRequest().permitAll()
//                .and()
//                .addFilterBefore(JwtAuthenticationFilter(JwtProvider()), UsernamePasswordAuthenticationFilter :: class.java)
    }
}