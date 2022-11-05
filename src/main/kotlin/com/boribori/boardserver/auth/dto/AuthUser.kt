package com.boribori.boardserver.auth.dto

import org.springframework.security.core.GrantedAuthority

import org.springframework.security.core.userdetails.UserDetails



/**
 * <h1>AuthUser</h1>
 *
 *
 * Custom Authentication Object
 *
 *
 *
 * 스프링 시큐리티에 사용될 Authentication 객체
 *
 *
 *
 * @author younghoCha
 */
class AuthUser (
        val userPayloads: UserDataOfJwt,
        val id: String
): UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return null
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return userPayloads.nickname
    }

    override fun isAccountNonExpired(): Boolean {
        return false
    }

    override fun isAccountNonLocked(): Boolean {
        return false
    }

    override fun isCredentialsNonExpired(): Boolean {
        return false
    }

    override fun isEnabled(): Boolean {
        return false
    }
}