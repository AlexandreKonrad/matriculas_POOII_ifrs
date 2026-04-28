package com.instituicao.matricula.view;

import com.instituicao.matricula.mock.SistemasExternosMock;
import com.instituicao.matricula.model.CursoAluno;
import com.instituicao.matricula.repository.CursoAlunoRepositoryImpl;
import com.instituicao.matricula.service.MatriculaService;

import java.util.List;
import java.util.Scanner;

public class MatriculaView {

    private final MatriculaService matriculaService;
    private final Scanner scanner;

    public MatriculaView(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        boolean rodando = true;
        while (rodando) {
            System.out.println("\n=== Sistema de Matrículas (Administrador) ===");
            System.out.println("1. Registrar Matrícula");
            System.out.println("2. Cancelar Matrícula");
            System.out.println("3. Consultar Alunos por Curso");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        telaRegistrarMatricula();
                        break;
                    case 2:
                        telaCancelarMatricula();
                        break;
                    case 3:
                        telaConsultarAlunos();
                        break;
                    case 0:
                        rodando = false;
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }

    private void telaRegistrarMatricula() throws Exception {
        System.out.println("\n--- Registrar Nova Matrícula ---");
        System.out.print("ID do Aluno: ");
        Long idAluno = scanner.nextLong();
        
        System.out.print("ID do Curso: ");
        Long idCurso = scanner.nextLong();

        CursoAluno matricula = matriculaService.registrarMatricula(idAluno, idCurso);
        System.out.println("-> Sucesso! Matrícula realizada. Número: " + matricula.getNumeroMatricula());
    }

    private void telaCancelarMatricula() throws Exception {
        System.out.println("\n--- Cancelar Matrícula ---");
        System.out.print("ID da Matrícula a ser cancelada: ");
        Long idMatricula = scanner.nextLong();

        CursoAluno matriculaCancelada = matriculaService.cancelarMatricula(idMatricula);
        System.out.println("-> Sucesso! Matrícula " + matriculaCancelada.getNumeroMatricula() + " cancelada. Vaga liberada.");
    }

    private void telaConsultarAlunos() {
        System.out.println("\n--- Consultar Alunos do Curso ---");
        System.out.print("ID do Curso: ");
        Long idCurso = scanner.nextLong();

        List<CursoAluno> matriculados = matriculaService.consultarAlunosPorCurso(idCurso);
        
        if (matriculados == null || matriculados.isEmpty()) {
            System.out.println("-> Nenhum aluno ativo encontrado para este curso.");
        } else {
            System.out.println("\nAlunos Ativos no Curso (Total: " + matriculados.size() + "):");
            for (CursoAluno ca : matriculados) {
                System.out.println("- ID Aluno: " + ca.getIdAluno() + 
                                   " | Nº Matrícula: " + ca.getNumeroMatricula() + 
                                   " | Data: " + ca.getDataMatricula());
            }
        }
    }

    // Ponto de entrada (Main) apenas para fins de demonstração
    public static void main(String[] args) {
        // 1. Instanciamos a "infraestrutura"
        SistemasExternosMock sistemasExternos = new SistemasExternosMock();
        CursoAlunoRepositoryImpl repository = new CursoAlunoRepositoryImpl();
        
        // 2. Injetamos no Service
        MatriculaService service = new MatriculaService(repository, sistemasExternos);
        
        // 3. Injetamos na View
        MatriculaView view = new MatriculaView(service);
        
        System.out.println("Sistemas inicializados.");
        System.out.println("Alunos no mock: 1 (Ativo), 2 (Ativo), 3 (Inativo)");
        System.out.println("Cursos no mock:");
        System.out.println("  101: Programação Java (Seg/Qua 19h - 30 vagas)");
        System.out.println("  102: Banco de Dados (Ter/Qui 19h - 1 vaga)");
        System.out.println("  103: Engenharia de Software (Seg/Qua 19h - 20 vagas)");
        
        // 4. Rodamos
        view.exibirMenu();
    }
}
