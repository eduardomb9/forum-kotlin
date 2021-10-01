package br.com.alura.forum.controller

import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.request.TopicoView
import br.com.alura.forum.service.TopicoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/topico")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): ResponseEntity<List<Topico>> {
        return ResponseEntity.ok(this.service.listar())
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Topico> {
        return ResponseEntity.ok(this.service.buscarPorId(id))
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid topicoView: TopicoView): ResponseEntity<Unit> {
        val id = this.service.cadastrar(topicoView)
        val uriResultante = URI.create("localhost:8080/topico/$id")
        return ResponseEntity.created(uriResultante).build()
    }
}