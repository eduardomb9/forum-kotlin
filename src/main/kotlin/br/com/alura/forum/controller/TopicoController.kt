package br.com.alura.forum.controller

import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topico")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar (): List<Topico> {
        return this.service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId (@PathVariable id: Long) : Topico {
        return this.service.buscarPorId(id);
    }
}