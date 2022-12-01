package com.boribori.boardserver.config

import com.boribori.boardserver.auth.JwtAuthenticationFailureHandler
import com.boribori.boardserver.auth.JwtAuthenticationFilter
import com.boribori.boardserver.auth.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@EnableWebSecurity
class SecurityConfig(
        private val jwtProvider: JwtProvider
): WebSecurityConfigurerAdapter() {


//    override fun configure(web: WebSecurity) {
//        super.configure(web)
//        web.ignoring().antMatchers("/api/board/**")
//        web.ignoring().antMatchers(HttpMethod.GET,"/**")
//    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.formLogin().disable()
        http.httpBasic().disable()
                .authorizeRequests() // 요청에 대한 사용권한 체크
                .antMatchers("/test").authenticated()
                .antMatchers("/api/board/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/board/**/comment").permitAll()
                .antMatchers(HttpMethod.GET, "/api/boards").permitAll()
                .antMatchers(HttpMethod.POST,"/api/board/**/comment").authenticated()
                .and()
                .addFilterBefore(JwtAuthenticationFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(JwtAuthenticationFailureHandler(), JwtAuthenticationFilter::class.java)

                // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다

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

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.addAllowedHeader("*")
        configuration.addAllowedOriginPattern("*")
        configuration.addAllowedMethod("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}