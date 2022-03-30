package br.edu.utfpr.service.CRUD.impl;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.Disciplina;
import br.edu.utfpr.repository.AlunoRepository;
import br.edu.utfpr.service.CRUD.AlunoCRUDService;

import java.util.List;

import static br.edu.utfpr.logger.Logger.*;
import static br.edu.utfpr.logger.Tipo.*;

public class AlunoCRUDServiceImpl implements AlunoCRUDService {

    AlunoRepository repository = new AlunoRepository();

    @Override
    public Aluno salvarAluno(Aluno aluno) {
        log(INFO, "Iniciou salvarAluno() " + aluno.toString());
        repository.salvar(aluno);
        log(INFO, "Finalizou" + aluno.toString());
        return aluno;
    }

    @Override
    public List<Aluno> buscarTodos() {
        log(INFO, "Buscando alunos");
        var retorno= repository.buscarTodos();
        log(INFO,"Finalizou busca alunos");
        return retorno;
    }

    @Override
    public boolean removerAluno(Aluno aluno) {
        log(INFO, "Iniciar remover aluno "+ aluno.getNome());
        var retorno = repository.remover(aluno);
        log(INFO, "Terminou de excluir");
        return retorno;
    }

    @Override
    public boolean removerAluno(int idAluno) {
        log(INFO, "Iniciar remover aluno id = "+ idAluno);
        var retorno = repository.remover(idAluno);
        log(INFO, "Terminou de excluir");
        return retorno;
    }

    @Override
    public List<Aluno> buscaPorDisciplina(Disciplina disciplina) {
        log(INFO, "Iniciar busca de alunos da disciplina " + disciplina.getNome());
        var retorno = repository.buscarPorDisciplina(disciplina.getId());
        log(INFO, "finalizou busca alunos");
        return retorno;
    }
}
