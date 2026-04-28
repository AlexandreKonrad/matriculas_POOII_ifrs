package com.instituicao.matricula.repository;

import com.instituicao.matricula.model.CursoAluno;

import java.util.List;
import java.util.Optional;

/**
 * Interface do Repository responsável pela comunicação com o banco de dados de Matrículas.
 * As implementações reais desta interface conterão as consultas SQL (ex: via JDBC ou JPA).
 */
public interface CursoAlunoRepository {
    
    // Salva o registro de uma nova matrícula no banco
    CursoAluno salvar(CursoAluno matricula);

    // Atualiza um registro existente (útil para alterar o status sem deletar)
    CursoAluno atualizar(CursoAluno matricula);

    // Busca uma matrícula pelo seu ID
    Optional<CursoAluno> buscarPorId(Long id);

    // RF4: Busca a lista de matrículas ativas para um curso específico
    List<CursoAluno> buscarMatriculasAtivasPorCurso(Long idCurso);

    // Regra de Negócio: Verifica se o aluno já possui matrícula ativa no curso
    boolean existeMatriculaAtiva(Long idAluno, Long idCurso);
}
