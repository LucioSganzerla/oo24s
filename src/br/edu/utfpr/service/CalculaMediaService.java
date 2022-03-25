package br.edu.utfpr.service;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.Disciplina;
import br.edu.utfpr.model.to.AlunoMediaTO;
import br.edu.utfpr.service.CRUD.AlunoCRUDService;
import br.edu.utfpr.service.CRUD.impl.AlunoCRUDServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class CalculaMediaService {

    private AlunoCRUDService alunoService = new AlunoCRUDServiceImpl();

    public List<AlunoMediaTO> porDisciplina(Disciplina disciplina) {
        List<Aluno> alunos = alunoService.buscaPorDisciplina(disciplina);
        return new ArrayList<>();
    }
}
