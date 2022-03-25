package br.edu.utfpr;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.service.CRUD.AlunoCRUDService;
import br.edu.utfpr.service.CRUD.impl.AlunoCRUDServiceImpl;
import br.edu.utfpr.sql.TableControl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        AlunoCRUDService service = new AlunoCRUDServiceImpl();

        TableControl.createTablesV1();
        Aluno a1 = new Aluno("Valcir", "46 99998 9999", "balbinottijr@outlook.com", LocalDate.now());
        Aluno a2 = new Aluno("Tiago", "46 99998 9999", "balbinottijr@outlook.com", LocalDate.now());
        Aluno a3 = new Aluno("Pedro", "46 99998 9999", "balbinottijr@outlook.com", LocalDate.now());
        service.salvarAluno(a1);
        service.salvarAluno(a2);
        service.salvarAluno(a3);
        System.out.println("ANTES de remover");
        service.buscarTodos().forEach(System.out::println);
        service.removerAluno(a2);
        service.removerAluno(a1.getId());
        System.out.println("DEPOIS de remover");
        service.buscarTodos().forEach(System.out::println);

    }
}
