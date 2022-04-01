package br.edu.utfpr.statement;

import br.edu.utfpr.model.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaStatement implements Statement<Disciplina> {
    @Override
    public String sqlCreateTable() {
        return "" +
                "CREATE TABLE IF NOT EXISTS disciplina (" +
                "id SERIAL PRIMARY KEY, " +
                "nome VARCHAR(50) NOT NULL " +
                ");";
    }

    @Override
    public String sqlDropTable() {
        return "" +
                "DROP TABLE IF EXISTS disciplina";
    }

    @Override
    public PreparedStatement findAll(Connection conn) throws SQLException {
        return conn.prepareStatement(
                "SELECT * FROM DISCIPLINA"
        );
    }

    @Override
    public PreparedStatement salvar(Connection conn, Disciplina disciplina) throws SQLException {
        PreparedStatement result = conn.prepareStatement(
                "INSERT INTO disciplina(nome) "  +
                        "VALUES(?)"
        );
        result.setString(1,disciplina.getNome());
        return result;
    }

    @Override
    public Disciplina convertResultToObject(ResultSet resultSet) throws SQLException {
        return Disciplina.builder()
                .id(resultSet.getInt(1))
                .nome(resultSet.getString(2))
                .build();
    }

    @Override
    public List<Disciplina> convertResultToObjectList(ResultSet resultSet) throws SQLException {
        List<Disciplina> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(convertResultToObject(resultSet));
        }
        return result;
    }
}
