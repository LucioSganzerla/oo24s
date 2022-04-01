package br.edu.utfpr.service;

import br.edu.utfpr.model.Avaliacao;

import java.util.List;

public class MathRepository {

    public Double calcularMedia(List<Avaliacao> avaliacaoList) {
        Double media = 0.0;
        for (Avaliacao avaliacao : avaliacaoList) {
            media += avaliacao.getNota();
        }
        return media / avaliacaoList.size();
    }

}
