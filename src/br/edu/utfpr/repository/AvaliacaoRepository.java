package br.edu.utfpr.repository;

import br.edu.utfpr.model.Avaliacao;
import br.edu.utfpr.statement.AvaliacaoStatement;

public class AvaliacaoRepository extends RepositoryImpl<AvaliacaoStatement, Avaliacao> {

    @Override
    public AvaliacaoStatement getInstanceOfT() {
        return new AvaliacaoStatement();
    }

}
