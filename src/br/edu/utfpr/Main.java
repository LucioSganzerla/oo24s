package br.edu.utfpr;

import br.edu.utfpr.database.TableControl;
import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.AlunoDisciplina;
import br.edu.utfpr.model.Avaliacao;
import br.edu.utfpr.model.Disciplina;
import br.edu.utfpr.repository.AlunoDisciplinaRepository;
import br.edu.utfpr.repository.AlunoRepository;
import br.edu.utfpr.repository.AvaliacaoRepository;
import br.edu.utfpr.repository.DisciplinaRepository;
import br.edu.utfpr.service.MathRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        TableControl.createTablesV1();
        AlunoRepository alunoRepository = new AlunoRepository();
        DisciplinaRepository disciplinaRepository = new DisciplinaRepository();
        AlunoDisciplinaRepository alunoDisciplinaRepository = new AlunoDisciplinaRepository();
        AvaliacaoRepository avaliacaoRepository = new AvaliacaoRepository();

        MathRepository mathRepository = new MathRepository();

        System.out.println("Cadastrando alunos...");

        Aluno a1 = Aluno.builder()
                .id(1L).nome("João").dataNascimento(LocalDate.parse("1940-11-09"))
                .email("joao@zinho.com").telefone("9999999").build();

        Aluno a2 = Aluno.builder()
                .id(2L).nome("Pedro").dataNascimento(LocalDate.parse("1940-11-09"))
                .email("pedro@zinho.com").telefone("9999999").build();

        alunoRepository.salvar(a1);
        System.out.println("Alunos cadastrados: ");
        alunoRepository.findAll().forEach(System.out::println);

        System.out.println("Cadastrando disciplinas...");
        Disciplina d1 = Disciplina.builder().id(1L).nome("AD23S").build();
        Disciplina d2 = Disciplina.builder().id(2L).nome("OO24S").build();
        disciplinaRepository.salvar(d1);
        disciplinaRepository.salvar(d2);
        System.out.println("Disciplinas cadastradas: ");
        disciplinaRepository.findAll().forEach(System.out::println);

        System.out.println("Cadastrando aluno-disciplina...");
        AlunoDisciplina ad1 = AlunoDisciplina.builder().id_aluno(a1.getId()).id_disciplina(d1.getId()).semestre_participacao("Primeiro").build();
        AlunoDisciplina ad2 = AlunoDisciplina.builder().id_aluno(a1.getId()).id_disciplina(d2.getId()).semestre_participacao("Primeiro").build();

        AlunoDisciplina ad3 = AlunoDisciplina.builder().id_aluno(a2.getId()).id_disciplina(d1.getId()).semestre_participacao("Primeiro").build();

        alunoDisciplinaRepository.salvar(ad1);
        alunoDisciplinaRepository.salvar(ad2);
        alunoDisciplinaRepository.salvar(ad3);
        System.out.println("Aluno-disciplina cadastradas: ");
        alunoDisciplinaRepository.findAll().forEach(System.out::println);

        System.out.println("Cadastrando avaliações...");
        avaliacaoRepository.salvar(Avaliacao.builder().id_aluno(a1.getId()).id_disciplina(d1.getId()).nota(9.0).build());
        avaliacaoRepository.salvar(Avaliacao.builder().id_aluno(a1.getId()).id_disciplina(d1.getId()).nota(9.0).build());
        avaliacaoRepository.salvar(Avaliacao.builder().id_aluno(a1.getId()).id_disciplina(d1.getId()).nota(9.0).build());
        avaliacaoRepository.salvar(Avaliacao.builder().id_aluno(a1.getId()).id_disciplina(d2.getId()).nota(2.0).build());
        avaliacaoRepository.salvar(Avaliacao.builder().id_aluno(a1.getId()).id_disciplina(d2.getId()).nota(1.0).build());
        avaliacaoRepository.salvar(Avaliacao.builder().id_aluno(a1.getId()).id_disciplina(d2.getId()).nota(5.5).build());

        avaliacaoRepository.salvar(Avaliacao.builder().id_aluno(a2.getId()).id_disciplina(d1.getId()).nota(2.0).build());
        avaliacaoRepository.salvar(Avaliacao.builder().id_aluno(a2.getId()).id_disciplina(d1.getId()).nota(2.0).build());
        avaliacaoRepository.salvar(Avaliacao.builder().id_aluno(a2.getId()).id_disciplina(d1.getId()).nota(2.0).build());
        System.out.println("Avaliações cadastradas: ");
        avaliacaoRepository.findAll().forEach(System.out::println);


        System.out.println("\nResultados utilizando Streams: ");
        System.out.printf("Méida Geral do aluno %s:\n", a1.getNome());
        System.out.println(mathRepository.calcularMedia(avaliacaoRepository.findAll().stream().filter(a ->
                a.getId_aluno().equals(a1.getId())).collect(Collectors.toList())));

        System.out.printf("Media do aluno %s na disciplina %s:\n", a1.getNome(), d1.getNome());
        System.out.println(mathRepository.calcularMedia(avaliacaoRepository.findAll().stream().filter(a ->
                a.getId_aluno().equals(a1.getId()) && a.getId_disciplina().equals(d1.getId())).collect(Collectors.toList())));

        System.out.printf("Media do aluno %s na disciplina %s:\n", a2.getNome(), d1.getNome());
        System.out.println(mathRepository.calcularMedia(avaliacaoRepository.findAll().stream().filter(a ->
                a.getId_aluno().equals(a2.getId()) && a.getId_disciplina().equals(d1.getId())).collect(Collectors.toList())));

        System.out.printf("Media do aluno %s na disciplina %s:\n", a1.getNome(), d2.getNome());
        System.out.println(mathRepository.calcularMedia(avaliacaoRepository.findAll().stream().filter(a ->
                a.getId_aluno().equals(a1.getId()) && a.getId_disciplina().equals(d2.getId())).collect(Collectors.toList())));

        System.out.printf("Méida Geral da disciplina %s:\n", d1.getNome());
        System.out.println(mathRepository.calcularMedia(avaliacaoRepository.findAll().stream().filter(a ->
                a.getId_disciplina().equals(d1.getId())).collect(Collectors.toList())));

        System.out.printf("Méida Geral da disciplina %s:\n", d2.getNome());
        System.out.println(mathRepository.calcularMedia(avaliacaoRepository.findAll().stream().filter(a ->
                a.getId_disciplina().equals(d2.getId())).collect(Collectors.toList())));

        System.out.println("\nResultados utilizando Selects: ");
        System.out.printf("Méida Geral do aluno %s:\n", a1.getNome());
        try {
            System.out.println(mathRepository.calcularMedia(avaliacaoRepository.getInstanceOfT().findAllByAluno(a1)));
        } catch (SQLException e) {
            System.out.println("Erro ao calcular média geral do aluno " + a1.getNome());
            e.printStackTrace();
        }

        System.out.printf("Media do aluno %s na disciplina %s:\n", a1.getNome(), d1.getNome());
        try {
            System.out.println(mathRepository.calcularMedia(avaliacaoRepository.getInstanceOfT().findAllByAlunoDisciplina(a1, d1)));
        } catch (SQLException e) {
            System.out.println("Erro ao calcular média do aluno " + a1.getNome() + " na disciplina " + d1.getNome());
            e.printStackTrace();
        }

        System.out.printf("Media do aluno %s na disciplina %s:\n", a2.getNome(), d1.getNome());
        try {
            System.out.println(mathRepository.calcularMedia(avaliacaoRepository.getInstanceOfT().findAllByAlunoDisciplina(a2, d1)));
        } catch (SQLException e) {
            System.out.println("Erro ao calcular média do aluno " + a2.getNome() + " na disciplina " + d1.getNome());
        }

        System.out.printf("Media do aluno %s na disciplina %s:\n", a1.getNome(), d2.getNome());
        try {
            System.out.println(mathRepository.calcularMedia(avaliacaoRepository.getInstanceOfT().findAllByAlunoDisciplina(a1, d2)));
        } catch (SQLException e) {
            System.out.println("Erro ao calcular média do aluno " + a1.getNome() + " na disciplina " + d2.getNome());
        }

        System.out.printf("Méida Geral da disciplina %s:\n", d1.getNome());
        try {
            System.out.println(mathRepository.calcularMedia(avaliacaoRepository.getInstanceOfT().findAllByDisciplina(d1)));
        } catch (SQLException e) {
            System.out.println("Erro ao calcular média geral da disciplina " + d1.getNome());
            e.printStackTrace();
        }

        System.out.printf("Méida Geral da disciplina %s:\n", d2.getNome());
        try {
            System.out.println(mathRepository.calcularMedia(avaliacaoRepository.getInstanceOfT().findAllByDisciplina(d2)));
        } catch (SQLException e) {
            System.out.println("Erro ao calcular média geral da disciplina " + d2.getNome());
            e.printStackTrace();
        }

        System.out.println("Fim");
    }
}
