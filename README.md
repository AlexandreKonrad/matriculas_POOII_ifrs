# Sistema de Matrículas - Instituição de Ensino

Este projeto é um sistema em Java desenvolvido para gerenciar o processo de matrículas de alunos em cursos de uma instituição de ensino. O sistema simula um ambiente corporativo com arquitetura em camadas e integração com sistemas externos.

## 🏛️ Arquitetura do Sistema

O projeto foi construído seguindo os princípios de **Programação Orientada a Objetos (POO)** e a estrita separação de responsabilidades. O padrão adotado foi a divisão em camadas:

1. **Model (`com.instituicao.matricula.model`)**: Contém as entidades principais do domínio do sistema (ex: `CursoAluno`).
2. **Repository (`com.instituicao.matricula.repository`)**: Responsável pela comunicação com o banco de dados. Implementa o padrão de abstração e isolamento (DAO).
3. **Service (`com.instituicao.matricula.service`)**: O "coração" do sistema. Esta camada contém todas as regras de negócio e integrações. Nenhuma regra de negócio deve vazar para a View.
4. **View (`com.instituicao.matricula.view`)**: Interface de interação com o usuário (no momento, em Terminal). Não contém lógica de negócio, apenas injeta as dependências e repassa comandos à camada Service.
5. **Mock (`com.instituicao.matricula.mock`)**: Camada de simulação de integrações externas. Como o sistema possui o conceito de **Banco de Dados Separado**, nós recebemos dados do "Sistema de Alunos" e do "Sistema de Cursos" através desta camada para validar nossas matrículas.

## 🛡️ Regras de Negócio Implementadas

A camada de Serviço (`MatriculaService`) valida obrigatoriamente as seguintes regras antes de persistir uma matrícula:
- **Duplicidade**: Um aluno não pode se matricular duas vezes no mesmo curso.
- **Status do Aluno**: Apenas alunos **Ativos** no sistema externo podem realizar matrículas.
- **Conflito de Horários**: Um aluno não pode estar matriculado em dois cursos que ocorram no mesmo dia e horário.
- **Lotação Máxima**: O sistema impede matrículas em cursos que já atingiram sua capacidade máxima de vagas.
- **Histórico de Cancelamento**: Ao cancelar uma matrícula, a linha no banco de dados não é apagada (DELETE), apenas o `status` é alterado para inativo, e a vaga é devolvida ao curso no sistema externo.

## 📂 Estrutura de Pastas e Arquivos

- `/src/com/instituicao/matricula/model/CursoAluno.java`: Entidade representando a tabela principal de matrículas.
- `/src/com/instituicao/matricula/repository/`: Contém a interface `CursoAlunoRepository` e a implementação concreta `CursoAlunoRepositoryImpl` simulando SQL via JDBC.
- `/src/com/instituicao/matricula/service/MatriculaService.java`: Classe contendo todos os `Ifs` e regras explicadas acima.
- `/src/com/instituicao/matricula/view/MatriculaView.java`: O ponto de entrada (`main`) que inicializa as dependências e roda o menu.
- `/script.sql`: Script DDL contendo o código para a criação real da tabela `CursoAluno` no banco de dados.

## 🚀 Como Executar o Projeto

Como o projeto é em Java puro, você pode rodá-lo utilizando uma IDE ou via Terminal.

### Executando via IDE (Recomendado)
1. Abra a pasta principal do projeto na sua IDE favorita (Eclipse, IntelliJ IDEA ou VS Code).
2. Navegue até o arquivo: `src/com/instituicao/matricula/view/MatriculaView.java`.
3. Execute a classe (Play / Run 'MatriculaView.main()').
4. O terminal interativo será aberto na aba de "Console" ou "Run" da sua IDE.

### Executando via Terminal (PowerShell / CMD)
Para rodar diretamente pela linha de comando, é necessário que você possua o **JDK (Java Development Kit)** instalado e configurado nas variáveis de ambiente (para ter acesso ao comando `javac`).

Siga o passo a passo a partir da pasta raiz do projeto (`matricula`):

**Passo 1: Compilar o código**
Crie uma pasta para armazenar os arquivos compilados e utilize o `javac` para compilar todas as classes do código fonte:
```powershell
# Cria a pasta bin (se não existir)
mkdir bin

# Salva a lista de todos os arquivos .java em um arquivo de texto
dir /s /B src\*.java > sources.txt

# Compila o código lendo o arquivo com os caminhos
javac -d bin @sources.txt
```

**Passo 2: Executar o programa**
Após compilar (os arquivos `.class` estarão dentro da pasta `bin`), execute a classe principal utilizando o comando `java`:
```powershell
java -cp bin com.instituicao.matricula.view.MatriculaView
```

### Dados Mocados para Teste
Ao executar o sistema, os seguintes dados estarão disponíveis para você forçar testes e ver as validações agindo:
- **Alunos**: 
  - `1`: João Silva (Ativo)
  - `2`: Maria Souza (Ativo)
  - `3`: Carlos (Inativo)
- **Cursos**: 
  - `101`: Programação Java (Seg/Qua 19h - 30 vagas)
  - `102`: Banco de Dados (Ter/Qui 19h - Apenas 1 vaga)
  - `103`: Engenharia de Software (Seg/Qua 19h - 20 vagas) *[Conflita com 101]*

*Dica de teste: Tente matricular o aluno 2 no curso 102. Em seguida, tente matricular o aluno 1 no mesmo curso e veja a regra de lotação agir!*
