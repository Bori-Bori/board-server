package com.boribori.boardserver.auth

import com.boribori.boardserver.auth.dto.AuthUser
import com.boribori.boardserver.auth.dto.AuthenticationFromJwt
import com.boribori.boardserver.auth.dto.UserDataOfJwt
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.net.http.HttpRequest
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtAuthenticationFilter (
        private val jwtProvider: JwtProvider,
        private val AUTHORIZATION_HEADER : String = "Authorization",
        private val BEARER_PREFIX : String = "Bearer "
        ): GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        var authorizationHeader : String? = (request as HttpServletRequest).getHeader(AUTHORIZATION_HEADER)

        val token : String = getToken(authorizationHeader)

        var userData : UserDataOfJwt? = jwtProvider.getUserData(token)

        var context = userData?.id?.let { AuthUser(id = it, userPayloads = userData) }

        SecurityContextHolder.getContext().authentication = context?.let { AuthenticationFromJwt(authUserContext = it) }

        chain.doFilter(request, response)

    }

    fun getToken(authorizationHeader : String?): String{
        if (authorizationHeader != null) {
            return authorizationHeader.substring(BEARER_PREFIX.length)
        }
        return ""
    }


}