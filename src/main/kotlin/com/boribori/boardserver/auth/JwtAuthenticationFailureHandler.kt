package com.boribori.boardserver.auth

import com.boribori.boardserver.common.Response
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtAuthenticationFailureHandler : OncePerRequestFilter() {


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try{
            filterChain.doFilter(request, response)
        }catch (e : SignatureException){
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e);
        }
    }


    fun setErrorResponse(status: HttpStatus, response: HttpServletResponse, exception: Throwable) {
        SecurityContextHolder.clearContext()
        val objectMapper = ObjectMapper()
        val writer = response.writer

        if (exception is ExpiredJwtException) {
            writer.write(objectMapper.writeValueAsString(
                    Response<Any>(
                            status = Response.Status("만료된 토큰입니다."),
                            content = null)
            ))
        }
        if (exception is SignatureException) {
            writer.write(objectMapper.writeValueAsString(
                    Response<Any>(
                            status = Response.Status("잘못된 토큰입니다."),
                            content = null)
            ))
        }
        if (exception is NullPointerException) {
            writer.write(objectMapper.writeValueAsString(
                    Response<Any>(
                            status = Response.Status("잘못된 토큰입니다."),
                            content = null)
            ))
        }
        if (exception is MalformedJwtException) {
            writer.write(objectMapper.writeValueAsString(
                    Response<Any>(
                           status = Response.Status("잘못된 토큰입니다."),
                    content = null)
            ))
        }
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.setHeader(HttpHeaders.CONTENT_ENCODING, "UTF-8")
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    }
}