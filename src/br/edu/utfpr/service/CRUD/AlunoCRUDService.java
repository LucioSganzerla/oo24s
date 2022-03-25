package br.edu.utfpr.service.CRUD;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.Disciplina;

import java.util.List;

public interface AlunoCRUDService {

    List<Aluno> buscarTodos();

    Aluno salvarAluno(Aluno aluno);

    boolean removerAluno(Aluno aluno);

    boolean removerAluno(int idAluno);

    List<Aluno> buscaPorDisciplina(Disciplina disciplina);
}
