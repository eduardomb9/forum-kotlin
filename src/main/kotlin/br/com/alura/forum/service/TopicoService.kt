package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.StatusTopico
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService {

    private var topicos = mutableListOf<Topico>()

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
        return topicos.filter { it.id == id }.first()
    }


}