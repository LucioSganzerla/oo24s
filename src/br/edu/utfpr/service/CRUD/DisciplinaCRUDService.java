package br.edu.utfpr.service.CRUD;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.AlunoDisciplina;
import br.edu.utfpr.model.Disciplina;

public interface DisciplinaCRUDService {
    Disciplina salvarDisciplina(Disciplina disciplina);
    AlunoDisciplina addAlunoNaDisciplina(Disciplina disciplina, Aluno aluno, String semestre);
    AlunoDisciplina addAlunoNaDisciplina(int idDisciplina, int idAluno, String semestre);
}
