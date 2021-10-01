package br.com.alura.forum.service

import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.StatusTopico
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.service.request.AtualizacaoTopicoView
import br.com.alura.forum.service.request.TopicoView
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TopicoService(
    private val topicoViewMapper: TopicoViewMapper,
) {

    private var topicos = mutableListOf<Topico>()
    private val LOG = LoggerFactory.getLogger(this::class.java)

    init {
        topicos.add(
            Topico(
                id = 1,
                titulo = "Duvida Kotlin",
                mensagem = "Mensagem no Kotlin",
                curso = Curso(
                    id = 1,
                    nome = "Kotlin",
                    categoria = "Programação"
                ),
                autor = Usuario(
                    id = 1,
                    nome = "Pedro",
                    email = "pedro@gmail.com"
                ),
                status = StatusTopico.NAO_RESPONDIDO
            )
        )
        topicos.add(
            Topico(
                id = 2,
                titulo = "Duvida Kotlin Parametros",
                mensagem = "Mensagem parametros no Kotlin",
                curso = Curso(
                    id = 1,
                    nome = "Kotlin",
                    categoria = "Programação"
                ),
                autor = Usuario(
                    id = 1,
                    nome = "Pedro",
                    email = "pedro@gmail.com"
                ),
                status = StatusTopico.NAO_RESPONDIDO
            )
        )
        topicos.add(
            Topico(
                id = 3,
                titulo = "Duvida Kotlin Controller Não Funciona",
                mensagem = "Mensagem dúvida controller no Kotlin",
                curso = Curso(
                    id = 1,
                    nome = "Kotlin",
                    categoria = "Programação"
                ),
                autor = Usuario(
                    id = 1,
                    nome = "Pedro",
                    email = "pedro@gmail.com"
                ),
                status = StatusTopico.NAO_RESPONDIDO
            )
        )
    }

    fun listar(): List<Topico> {
        return topicos.toList()
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.first { it.id == id }
    }

    fun cadastrar(topicoView: TopicoView): Long? {
        return topicoViewMapper.map(topicoView)
            .apply { id = topicos.last().id?.plus(1) }
            .also { topicos.add(it) }.id
    }

    fun atualizar(atualizarTopicoView: AtualizacaoTopicoView) {
        topicos
            .first { it.id == atualizarTopicoView.id }
            .apply {
                titulo = atualizarTopicoView.titulo
                mensagem = atualizarTopicoView.mensagem
                dataCriacao = LocalDateTime.now()
            }
    }

    fun remover(id: Long) {
        topicos
            .first {
                it.id == id
            }.let { topicos.remove(it) }
        LOG.info("Topico $id removido")
    }


}