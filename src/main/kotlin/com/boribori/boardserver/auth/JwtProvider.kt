package com.boribori.boardserver.auth

import com.boribori.boardserver.auth.dto.UserDataOfJwt
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.SignatureException

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets


@Component
class JwtProvider
        {
    @Value("\${jwt.access-token-key}")
    val tokenKey: String = ""

    fun getUserData(accessToken: String): UserDataOfJwt? {

            var claims: Claims = getClaims(accessToken, tokenKey)

            return UserDataOfJwt(
                    id = claims.subject,
                    nickname = claims["nickname"] as String,
                    userProfile = claims["profile_image"] as String
            )

    }

    private fun getClaims(token: String, tokenKey: String): Claims {
        //tokenKey.getBytes(StandardCharsets.UTF_8)



            return Jwts.parser()
                    .setSigningKey(tokenKey.toByteArray(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .body


    }
}