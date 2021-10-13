package br.com.alura.forum.config

import br.com.alura.forum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val userDetailService: UserDetailsService,
    private val jwtUtil: JwtUtil
        ) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.
        authorizeRequests()?.
//            antMatchers("/topico")?.hasAuthority("LEITURA-ESCRITA")?.
//            antMatchers("/h2-console/**")?.permitAll()?.
            antMatchers("/login")?.permitAll()?.
            anyRequest()?.
            authenticated()?.
            and()
            http?.addFilterBefore(JWTLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
            http?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.
            and()?.
            formLogin()?.
            disable()?.
            httpBasic()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailService)?.passwordEncoder(bCryptPasswordEncoder())
    }

}