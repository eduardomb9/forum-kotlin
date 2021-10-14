package br.com.alura.forum.security

import br.com.alura.forum.config.JwtUtil
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(private val jwtUtil: JwtUtil) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val detail = getTokenDetail(request.getHeader("Authorization"))
        if (jwtUtil.isValid(detail)) {
            val authentication = jwtUtil.getAuthentication(detail)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun getTokenDetail(token: String?): String? {
        return token?.let {
            it.startsWith("Bearer ")
            it.substring(7, it.length)
        }
    }

}
