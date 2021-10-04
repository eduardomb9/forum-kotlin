package br.com.alura.forum.service.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicoForm(
    @field:NotEmpty @field:Size(min = 5, max = 30)
    val titulo: String,

    @field:NotEmpty
    val mensagem: String,

    @field:NotNull
    val idCurso: Long,

    @field:NotNull
    val idAutor: Long
)