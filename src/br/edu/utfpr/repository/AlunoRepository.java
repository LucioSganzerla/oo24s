package br.edu.utfpr.repository;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.statement.AlunoStatement;

public class AlunoRepository extends RepositoryImpl<AlunoStatement, Aluno>{
    @Override
    public AlunoStatement getInstanceOfT() {
        return new AlunoStatement();
    }
}
