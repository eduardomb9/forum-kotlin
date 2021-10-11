package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.StatusTopico
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.TopicoRepository
import br.com.alura.forum.service.request.AtualizacaoTopicoForm
import br.com.alura.forum.service.request.TopicoForm
import br.com.alura.forum.service.response.TopicoView
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TopicoService(
    private val topicoFormMapper: TopicoFormMapper,
    private val topicoViewMapper: TopicoViewMapper,
    private val repository: TopicoRepository,
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
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

    fun listar(nomeCurso: String?, pagina: Pageable): Page<TopicoView> {
        val topicos = if (nomeCurso == null) {
            repository.findAll(pagina)
        } else {
            repository.findByCursoNome(nomeCurso, pagina)
        }
        return topicos.map { topicoViewMapper.map(it) }
    }

    fun buscarPorId(id: Long): Topico {
        return repository.findById(id).orElseThrow { NotFoundException("Nao encontrado!") }
    }

    fun cadastrar(topicoForm: TopicoForm): TopicoView {
        val topico = topicoFormMapper.map(topicoForm)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    @Transactional
    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id).orElseThrow { NotFoundException("Nao encontrado!") }
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun remover(id: Long) {
        repository.deleteById(id)
        LOG.info("Topico $id removido")
    }

}