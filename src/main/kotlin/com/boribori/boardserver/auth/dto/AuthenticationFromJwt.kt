package com.boribori.boardserver.auth.dto

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.List


class AuthenticationFromJwt(private val authUserContext: AuthUser) : AbstractAuthenticationToken(List.of(SimpleGrantedAuthority(authUserContext.userPayloads.nickname))) {
    init {
        this.isAuthenticated = true
    }

    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): Any {
        return authUserContext
    }
}