package br.com.alura.forum.controller

import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.TopicoService
import br.com.alura.forum.service.request.AtualizacaoTopicoForm
import br.com.alura.forum.service.request.TopicoForm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topico")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(@RequestParam(required = false) nomeCurso: String?): ResponseEntity<List<Topico>> {
        return ResponseEntity.ok(this.service.listar(nomeCurso))
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Topico> {
        return ResponseEntity.ok(this.service.buscarPorId(id))
    }

    @PostMapping
    fun cadastrar(
        @RequestBody @Valid topicoView: TopicoForm,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<Long> {
        val topico = this.service.cadastrar(topicoView)
        val uriResultante = uriComponentsBuilder.path("/topico/${topico.id}").build().toUri()
        return ResponseEntity.created(uriResultante).body(topico.id)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid atualizarTopicoView: AtualizacaoTopicoForm) {
        this.service.atualizar(atualizarTopicoView)
        ResponseEntity.ok()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remover(@PathVariable id: Long) {
        this.service.remover(id)
    }
}