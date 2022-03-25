package br.edu.utfpr.model;

import br.edu.utfpr.sql.CreateTableHelper;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Aluno extends Model{

    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private int idDisciplina;

    public Aluno(int id,
                 String nome,
                 String telefone,
                 String email,
                 LocalDate dataNascimento) {
        super.setId(id);
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Aluno(String nome,
                 String telefone,
                 String email,
                 LocalDate dataNascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Aluno(int id,
                 String nome,
                 String telefone,
                 String email,
                 LocalDate dataNascimento,
                 int idDisciplina) {
        super.setId(id);
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.idDisciplina = idDisciplina;
    }

    @Override
    public CreateTableHelper generateCreateTableSQL() {
        String createTable = "" +
                "CREATE TABLE IF NOT EXISTS aluno (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(50) NOT NULL, " +
                    "telefone VARCHAR(20) NOT NULL, " +
                    "email VARCHAR(70) NOT NULL, " +
                    "dataNascimento DATE NOT NULL" +
                    "id_disciplina INT NULL );";


        String dropTable = "" +
                "DROP TABLE IF EXISTS aluno";

        return new CreateTableHelper(createTable, dropTable);
    }
}
