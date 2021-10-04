package br.com.alura.forum.mapper

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.service.request.TopicoForm
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper : Mapper<TopicoForm, Topico> {
    override fun map(t: TopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = Curso(id = t.idCurso, nome = "", categoria = ""),
            autor = Usuario(id = t.idAutor, nome = "", email = "")
        )
    }

}