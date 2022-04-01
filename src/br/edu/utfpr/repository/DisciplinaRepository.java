package br.edu.utfpr.repository;

import br.edu.utfpr.model.Disciplina;
import br.edu.utfpr.statement.DisciplinaStatement;

public class DisciplinaRepository extends RepositoryImpl<DisciplinaStatement, Disciplina>{
    @Override
    public DisciplinaStatement getInstanceOfT() {
        return new DisciplinaStatement();
    }
}
