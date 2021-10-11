package br.com.alura.forum.repository

import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.response.RelatorioDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TopicoRepository: JpaRepository<Topico, Long> {

    fun findByCursoNome(nomeCurso: String, pagina: Pageable): Page<Topico>

    @Query("select new br.com.alura.forum.service.response.RelatorioDto(c.categoria, count(t)) from Topico t JOIN t.curso c group by c.categoria")
    fun obterRelatorio(): List<RelatorioDto>
}