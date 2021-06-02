package com.example.kustomerloginanddescribe

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.Date

/**
 * WARNING: This class is included to generate JWT tokens locally to keep this example app self-contained.
 * All JWT tokens should be generated server-side, not in the client app.
 *
 * @see <a href="https://developer.kustomer.com/chat-sdk/v2-Android/docs/authentication">Kustomer Android Authentication</a>
 * */
object JwtGenerator {

    private fun getSigningKey(secret: String): Key? {
        val keyBytes = secret.toByteArray(StandardCharsets.UTF_8)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun getJwt(secret: String, key: String, value: String): String? {
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .claim(key, value)
            .setIssuedAt(Date())
            .signWith(getSigningKey(secret), SignatureAlgorithm.HS256)
            .compact()
    }
}