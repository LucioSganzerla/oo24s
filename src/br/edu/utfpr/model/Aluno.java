package br.edu.utfpr.model;

import br.edu.utfpr.sql.CreateTableHelper;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class Aluno extends Model {

    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;

    public Aluno(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = "Email não informado";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataNascimento = LocalDate.parse("01/01/2000", df);
    }

    @Override
    public CreateTableHelper generateCreateTableSQL() {
        String createTable = "" +
                "CREATE TABLE IF NOT EXISTS aluno (" +
                "id SERIAL PRIMARY KEY, " +
                "nome VARCHAR(50) NOT NULL, " +
                "telefone VARCHAR(20) NOT NULL, " +
                "email VARCHAR(70) NOT NULL, " +
                "dataNascimento DATE NOT NULL);";


        String dropTable = "" +
                "DROP TABLE IF EXISTS aluno";

        return new CreateTableHelper(createTable, dropTable);
    }

    @Override
    public String toString() {
        return "Aluno {" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
