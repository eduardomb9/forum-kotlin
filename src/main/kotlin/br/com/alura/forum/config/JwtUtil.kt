package br.com.alura.forum.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import java.util.*

class JwtUtil {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.expiration}")
    private lateinit var expiration: String

    fun generateToken(username: String) : String? {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + expiration.toLong()))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

}