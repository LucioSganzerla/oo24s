package br.edu.utfpr.statement;

import br.edu.utfpr.model.Aluno;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AlunoStatement implements Statement<Aluno>{

    @Override
    public String sqlCreateTable() {
        return "" +
                "CREATE TABLE IF NOT EXISTS aluno (" +
                "id SERIAL PRIMARY KEY, " +
                "nome VARCHAR(50) NOT NULL, " +
                "telefone VARCHAR(20) NOT NULL, " +
                "email VARCHAR(70) NOT NULL, " +
                "dataNascimento DATE NOT NULL);";
    }

    @Override
    public String sqlDropTable() {
        return "" +
                "DROP TABLE IF EXISTS aluno";
    }

    @Override
    public PreparedStatement findAll(Connection conn) throws SQLException {
        return conn.prepareStatement(
                "SELECT * FROM aluno"
        );
    }

    @Override
    public PreparedStatement salvar(Connection conn, Aluno aluno) throws SQLException {
        PreparedStatement psSalvar = conn.prepareStatement(
                "INSERT INTO aluno(nome, telefone, email, dataNascimento) " +
                        "VALUES(?, ?, ?, ?)", RETURN_GENERATED_KEYS
        );
        psSalvar.setString(1, aluno.getNome());
        psSalvar.setString(2, aluno.getTelefone());
        psSalvar.setString(3, aluno.getEmail());
        psSalvar.setDate(4, Date.valueOf(aluno.getDataNascimento()));
        return psSalvar;
    }

    @Override
    public Aluno convertResultToObject(ResultSet resultSet) throws SQLException {
        return Aluno.builder()
                .id(resultSet.getInt(1))
                .nome(resultSet.getString(2))
                .telefone(resultSet.getString(3))
                .email(resultSet.getString(4))
                .dataNascimento(LocalDate.parse(resultSet.getString(5)))
                .build();
    }

    @Override
    public List<Aluno> convertResultToObjectList(ResultSet resultSet) throws SQLException {
        List<Aluno> result = new ArrayList<>();
        while (resultSet.next()) {
            Aluno aluno = convertResultToObject(resultSet);
            result.add(aluno);
        };
        return result;
    }
}
