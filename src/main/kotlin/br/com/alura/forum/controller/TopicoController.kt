package br.com.alura.forum.controller

import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.TopicoService
import br.com.alura.forum.service.request.AtualizacaoTopicoForm
import br.com.alura.forum.service.request.TopicoForm
import br.com.alura.forum.service.response.TopicoView
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topico")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    @Cacheable("topicos")
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(
            size = 5,
            sort = ["dataCriacao"],
            direction = Sort.Direction.DESC
        ) pagina: Pageable
    ): ResponseEntity<Page<TopicoView>> {
        return ResponseEntity.ok(this.service.listar(nomeCurso, pagina))
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Topico> {
        return ResponseEntity.ok(this.service.buscarPorId(id))
    }

    @PostMapping
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun cadastrar(
        @RequestBody @Valid topicoView: TopicoForm,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<Long> {
        val topico = this.service.cadastrar(topicoView)
        val uriResultante = uriComponentsBuilder.path("/topico/${topico.id}").build().toUri()
        return ResponseEntity.created(uriResultante).body(topico.id)
    }

    @PutMapping
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizar(@RequestBody @Valid atualizarTopicoView: AtualizacaoTopicoForm) {
        this.service.atualizar(atualizarTopicoView)
        ResponseEntity.ok()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun remover(@PathVariable id: Long) {
        this.service.remover(id)
    }
}