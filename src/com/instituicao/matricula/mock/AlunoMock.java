package com.instituicao.matricula.mock;

public class AlunoMock {
    private Long id;
    private String nome;
    private boolean ativo; // Alunos inativos não podem se matricular

    public AlunoMock(Long id, String nome, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public boolean isAtivo() { return ativo; }
}
