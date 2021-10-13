package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val usuario: Usuario) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? = null

    override fun getPassword(): String = usuario.senha

    override fun getUsername(): String = usuario.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}
