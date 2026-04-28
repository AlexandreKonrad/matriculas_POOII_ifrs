package com.instituicao.matricula.mock;

public class CursoMock {
    private Long id;
    private String nome;
    private int cargaHoraria;
    private String horario; // Ex: "Seg/Qua 19h"
    private int vagasMaximas;
    private int vagasOcupadas;

    public CursoMock(Long id, String nome, int cargaHoraria, String horario, int vagasMaximas) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.horario = horario;
        this.vagasMaximas = vagasMaximas;
        this.vagasOcupadas = 0;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public int getCargaHoraria() { return cargaHoraria; }
    public String getHorario() { return horario; }
    public int getVagasMaximas() { return vagasMaximas; }
    public int getVagasOcupadas() { return vagasOcupadas; }

    public boolean isLotado() {
        return vagasOcupadas >= vagasMaximas;
    }

    public void adicionarAluno() {
        this.vagasOcupadas++;
    }

    public void removerAluno() {
        if (this.vagasOcupadas > 0) {
            this.vagasOcupadas--;
        }
    }
}
