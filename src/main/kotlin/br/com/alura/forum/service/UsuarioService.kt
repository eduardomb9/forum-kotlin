package br.com.alura.forum.service

import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UsuarioService(private val repository: UsuarioRepository) : UserDetailsService {

    fun buscarPorId(id: Long) {
        repository.getOne(id)
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        val usuario = repository.findByEmail(email) ?: throw RuntimeException()
        return UserDetail(usuario)
    }

}