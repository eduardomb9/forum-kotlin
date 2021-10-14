package br.com.alura.forum.config

import br.com.alura.forum.model.Role
import br.com.alura.forum.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil(
    private val usuarioService: UsuarioService
) {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.expiration}")
    private lateinit var expiration: String

    fun generateToken(username: String, authorities: List<Role>): String? {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", authorities)
            .setExpiration(Date(System.currentTimeMillis() + expiration.toLong()))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

    fun isValid(detail: String?): Boolean {
        return try {
            println(detail)
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(detail)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun getAuthentication(detail: String?): Authentication {
        val username = Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(detail).body.subject
        val user = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(user.username, null, user.authorities)
    }

}