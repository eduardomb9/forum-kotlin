package br.com.alura.forum.service.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class AtualizacaoTopicoView(
    @field:NotNull
    val id: Long,

    @field:NotEmpty @field:Size(min = 5, max = 30)
    val titulo: String,

    @field:NotEmpty
    val mensagem: String,
)