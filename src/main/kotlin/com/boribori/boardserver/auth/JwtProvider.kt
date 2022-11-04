package com.boribori.boardserver.auth

import com.boribori.boardserver.auth.dto.UserDataOfJwt
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets


@Component
class JwtProvider (
        private val jwtProperties: JwtProperties
        ){

    fun getUserData(accessToken: String): UserDataOfJwt? {

        var claims: Claims = getClaims(accessToken, "accessKey")

        return UserDataOfJwt(
                id = claims.subject,
                nickname = claims.get("nickname") as String?
        )
    }

    private fun getClaims(token: String, tokenKey: String): Claims {
        //tokenKey.getBytes(StandardCharsets.UTF_8)
        return Jwts.parser()
                .setSigningKey(tokenKey.toByteArray(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody()

    }
}