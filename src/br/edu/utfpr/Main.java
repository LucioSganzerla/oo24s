package br.edu.utfpr;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.AlunoDisciplina;
import br.edu.utfpr.model.Disciplina;
import br.edu.utfpr.service.CRUD.AlunoCRUDService;
import br.edu.utfpr.service.CRUD.DisciplinaCRUDService;
import br.edu.utfpr.service.CRUD.impl.AlunoCRUDServiceImpl;
import br.edu.utfpr.service.CRUD.impl.DisciplinaCrudServiceImpl;
import br.edu.utfpr.sql.TableControl;

import java.time.LocalDate;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        AlunoCRUDService alunoService = new AlunoCRUDServiceImpl();
        DisciplinaCRUDService disciplinaService = new DisciplinaCrudServiceImpl();

        TableControl.createTablesV1();
        Aluno a1 = new Aluno("Valcir", "46 99998 9999", "balbinottijr@outlook.com", LocalDate.now());
        Aluno a2 = new Aluno("Tiago", "46 99998 9999", "balbinottijr@outlook.com", LocalDate.now());
        Aluno a3 = new Aluno("Pedro", "46 99998 9999", "balbinottijr@outlook.com", LocalDate.now());
        alunoService.salvarAluno(a1);
        alunoService.salvarAluno(a2);
        alunoService.salvarAluno(a3);
        System.out.println("ANTES de remover");
        alunoService.buscarTodos().forEach(System.out::println);
        alunoService.removerAluno(a2);
        alunoService.removerAluno(a1.getId());
        System.out.println("DEPOIS de remover");
        alunoService.buscarTodos().forEach(System.out::println);
        Disciplina d1 = Disciplina.builder().nome("AD23S").build();
        Disciplina d2 = Disciplina.builder().nome("OO24S").build();
        disciplinaService.salvarDisciplina(d1);
        disciplinaService.salvarDisciplina(d2);
        disciplinaService.addAlunoNaDisciplina(d1, a1, "2022/1");
        disciplinaService.addAlunoNaDisciplina(d1, a2, "2022/1");
        disciplinaService.addAlunoNaDisciplina(d2, a1, "2022/2");
        disciplinaService.addAlunoNaDisciplina(d2, a3, "2022/2");

        alunoService.buscaPorDisciplina(d1)
                .forEach(System.out::println);

    }
}
