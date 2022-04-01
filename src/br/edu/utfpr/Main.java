package br.edu.utfpr;

import br.edu.utfpr.database.TableControl;
import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.Disciplina;
import br.edu.utfpr.repository.AlunoRepository;
import br.edu.utfpr.repository.DisciplinaRepository;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        TableControl.createTablesV1();
        AlunoRepository alunoRepository = new AlunoRepository();
        DisciplinaRepository disciplinaRepository = new DisciplinaRepository();

        Aluno a1 =  Aluno.builder()
                .nome("Valcir")
                .dataNascimento(LocalDate.parse("1994-11-09"))
                .email("balbinottijr@gmail.com")
                .telefone("9999999")
                .build();

        Disciplina d1 = Disciplina.builder()
                .nome("AD23S").build();
        alunoRepository.salvar(a1);
        alunoRepository.findAll()
                .forEach(System.out::println);
        disciplinaRepository.salvar(d1);
        disciplinaRepository.findAll()
                .forEach(System.out::println);


    }
}
