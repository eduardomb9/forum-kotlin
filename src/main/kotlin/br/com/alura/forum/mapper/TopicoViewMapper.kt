package br.com.alura.forum.mapper

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.service.request.TopicoView
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper : Mapper<TopicoView, Topico> {
    override fun map(t: TopicoView): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = Curso(id = t.idCurso, nome = "", categoria = ""),
            autor = Usuario(id = t.idAutor, nome = "", email = "")
        )
    }

}