package br.edu.utfpr;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.Model;
import br.edu.utfpr.service.AlunoCRUDService;
import br.edu.utfpr.service.impl.AlunoCRUDServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Model> models = List.of(new Aluno());
        models.forEach(Model::createTable);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Aluno aluno = Aluno.builder()
                .nome("João")
                .telefone("123456789")
                .email("joão@email.com")
                .dataNascimento(LocalDate.parse("01/01/2000", df))
                .build();

        AlunoCRUDService service = new AlunoCRUDServiceImpl();
        service.salvarAluno(aluno);
    }
}
