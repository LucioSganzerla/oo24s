package br.edu.utfpr.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Disciplina implements Model{
    private int id;
    private String nome;
}
