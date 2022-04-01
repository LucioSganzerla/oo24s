package br.edu.utfpr.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlunoDisciplina implements Model {

    private Long id;
    private Long id_aluno;
    private Long id_disciplina;
    private String semestre_participacao;

}
