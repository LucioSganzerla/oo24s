package br.edu.utfpr.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Aluno implements Model{
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
}
