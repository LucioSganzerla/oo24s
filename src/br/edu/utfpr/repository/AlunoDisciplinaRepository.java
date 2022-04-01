package br.edu.utfpr.repository;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.AlunoDisciplina;
import br.edu.utfpr.statement.AlunoDisciplinaStatement;
import br.edu.utfpr.statement.AlunoStatement;

public class AlunoDisciplinaRepository extends RepositoryImpl<AlunoDisciplinaStatement, AlunoDisciplina>{

    @Override
    public AlunoDisciplinaStatement getInstanceOfT() {
        return new AlunoDisciplinaStatement();
    }

}
