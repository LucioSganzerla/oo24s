package br.edu.utfpr.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Avaliacao implements Model {

    private Long id;
    private Long id_disciplina;
    private Long id_aluno;
    private Double nota;

}
