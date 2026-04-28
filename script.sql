-- Script DDL para criação da tabela do Sistema de Matrículas

CREATE TABLE CursoAluno (
    id SERIAL PRIMARY KEY, -- Uso de SERIAL (PostgreSQL) ou AUTO_INCREMENT (MySQL)
    id_aluno INT NOT NULL,
    id_curso INT NOT NULL,
    data_matricula TIMESTAMP NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE, -- TRUE para Ativa, FALSE para Cancelada
    numero_matricula VARCHAR(50) UNIQUE NOT NULL
);

-- Índices para melhorar a performance das consultas exigidas
CREATE INDEX idx_cursoaluno_aluno ON CursoAluno(id_aluno);
CREATE INDEX idx_cursoaluno_curso ON CursoAluno(id_curso);
CREATE INDEX idx_cursoaluno_status ON CursoAluno(status);
