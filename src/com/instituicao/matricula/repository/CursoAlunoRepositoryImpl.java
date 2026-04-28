package com.instituicao.matricula.repository;

import com.instituicao.matricula.model.CursoAluno;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementação simulada em memória com os SQLs que seriam executados em um banco relacional.
 */
public class CursoAlunoRepositoryImpl implements CursoAlunoRepository {

    // Simula a tabela do banco de dados
    private List<CursoAluno> tabelaCursoAluno = new ArrayList<>();
    private Long idSequence = 1L;

    @Override
    public CursoAluno salvar(CursoAluno matricula) {
        /*
         * SQL equivalente:
         * INSERT INTO CursoAluno (id_aluno, id_curso, data_matricula, status, numero_matricula) 
         * VALUES (?, ?, ?, ?, ?);
         */
        matricula.setId(idSequence++);
        tabelaCursoAluno.add(matricula);
        
        System.out.println("[JDBC-LOG] Executando INSERT na tabela CursoAluno...");
        return matricula;
    }

    @Override
    public CursoAluno atualizar(CursoAluno matricula) {
        /*
         * SQL equivalente:
         * UPDATE CursoAluno SET status = ? WHERE id = ?;
         */
        
        System.out.println("[JDBC-LOG] Executando UPDATE na tabela CursoAluno (alterando status para inativo)...");
        // Como o objeto já teve o status alterado na memória pela camada de Service,
        // no nosso mock em lista não precisamos fazer um set manual novamente.
        return matricula;
    }

    @Override
    public Optional<CursoAluno> buscarPorId(Long id) {
        /*
         * SQL equivalente:
         * SELECT * FROM CursoAluno WHERE id = ?;
         */
        System.out.println("[JDBC-LOG] Executando SELECT por ID na tabela CursoAluno...");
        return tabelaCursoAluno.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<CursoAluno> buscarMatriculasAtivasPorCurso(Long idCurso) {
        /*
         * SQL equivalente:
         * SELECT * FROM CursoAluno WHERE id_curso = ? AND status = true;
         */
        System.out.println("[JDBC-LOG] Executando SELECT para buscar matrículas ativas por curso...");
        List<CursoAluno> resultado = new ArrayList<>();
        for (CursoAluno m : tabelaCursoAluno) {
            if (m.getIdCurso().equals(idCurso) && m.isStatus()) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    @Override
    public boolean existeMatriculaAtiva(Long idAluno, Long idCurso) {
        /*
         * SQL equivalente:
         * SELECT count(*) FROM CursoAluno WHERE id_aluno = ? AND id_curso = ? AND status = true;
         */
        System.out.println("[JDBC-LOG] Executando SELECT para verificar se a matrícula existe...");
        return tabelaCursoAluno.stream()
                .anyMatch(m -> m.getIdAluno().equals(idAluno) 
                            && m.getIdCurso().equals(idCurso) 
                            && m.isStatus());
    }
    
    // Método extra para a camada de serviço poder buscar os cursos de um aluno (para verificar conflitos)
    public List<CursoAluno> buscarMatriculasAtivasPorAluno(Long idAluno) {
        /*
         * SQL equivalente:
         * SELECT * FROM CursoAluno WHERE id_aluno = ? AND status = true;
         */
        List<CursoAluno> resultado = new ArrayList<>();
        for (CursoAluno m : tabelaCursoAluno) {
            if (m.getIdAluno().equals(idAluno) && m.isStatus()) {
                resultado.add(m);
            }
        }
        return resultado;
    }
}
