package com.instituicao.matricula.model;

import java.time.LocalDateTime;

public class CursoAluno {
    
    private Long id; // Chave Primária
    private Long idAluno;
    private Long idCurso;
    private LocalDateTime dataMatricula;
    private boolean status; // true = Ativa, false = Cancelada
    private String numeroMatricula;

    public CursoAluno() {
    }

    public CursoAluno(Long id, Long idAluno, Long idCurso, LocalDateTime dataMatricula, boolean status, String numeroMatricula) {
        this.id = id;
        this.idAluno = idAluno;
        this.idCurso = idCurso;
        this.dataMatricula = dataMatricula;
        this.status = status;
        this.numeroMatricula = numeroMatricula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public LocalDateTime getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDateTime dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }
}
