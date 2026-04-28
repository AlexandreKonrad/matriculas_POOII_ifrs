package com.instituicao.matricula.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SistemasExternosMock {
    private List<AlunoMock> alunos;
    private List<CursoMock> cursos;

    public SistemasExternosMock() {
        this.alunos = new ArrayList<>();
        this.cursos = new ArrayList<>();
        inicializarDados();
    }

    private void inicializarDados() {
        // Alunos (1 inativo)
        alunos.add(new AlunoMock(1L, "João Silva", true));
        alunos.add(new AlunoMock(2L, "Maria Souza", true));
        alunos.add(new AlunoMock(3L, "Carlos Inativo", false));

        // Cursos (Curso 2 com limite baixo para teste de lotação, Curso 3 com mesmo horário do 1)
        cursos.add(new CursoMock(101L, "Programação Java", 60, "Seg/Qua 19h", 30));
        cursos.add(new CursoMock(102L, "Banco de Dados", 40, "Ter/Qui 19h", 1)); // Limite de 1 vaga
        cursos.add(new CursoMock(103L, "Engenharia de Software", 60, "Seg/Qua 19h", 20)); // Conflita com o 101
    }

    public Optional<AlunoMock> buscarAluno(Long idAluno) {
        return alunos.stream().filter(a -> a.getId().equals(idAluno)).findFirst();
    }

    public Optional<CursoMock> buscarCurso(Long idCurso) {
        return cursos.stream().filter(c -> c.getId().equals(idCurso)).findFirst();
    }
}
