package br.edu.utfpr.statement;

import br.edu.utfpr.database.ConnectDataBase;
import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.Avaliacao;
import br.edu.utfpr.model.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AvaliacaoStatement implements Statement<Avaliacao> {

    @Override
    public String sqlCreateTable() {
        return "CREATE TABLE IF NOT EXISTS avaliacao (" +
                "id SERIAL PRIMARY KEY, " +
                "id_disciplina int8 NOT NULL, " +
                "id_aluno int8 NOT NULL, " +
                "nota numeric(10,2) NOT NULL " +
                ");";
    }

    @Override
    public String sqlDropTable() {
        return "DROP TABLE IF EXISTS avaliacao";
    }

    @Override
    public PreparedStatement findAll(Connection conn) throws SQLException {
        return conn.prepareStatement(
                "SELECT * FROM avaliacao"
        );
    }

    @Override
    public PreparedStatement salvar(Connection conn, Avaliacao avaliacao) throws SQLException {
        PreparedStatement result = conn.prepareStatement(
                "INSERT INTO avaliacao(id_disciplina, id_aluno, nota) VALUES(?, ?, ?)", RETURN_GENERATED_KEYS
        );
        result.setLong(1, avaliacao.getId_disciplina());
        result.setLong(2, avaliacao.getId_aluno());
        result.setDouble(3, avaliacao.getNota());
        return result;
    }

    @Override
    public Avaliacao convertResultToObject(ResultSet resultSet) throws SQLException {
        return Avaliacao.builder()
                .id(resultSet.getLong(1))
                .id_disciplina(resultSet.getLong(2))
                .id_aluno(resultSet.getLong(3))
                .nota(resultSet.getDouble(4))
                .build();
    }

    @Override
    public List<Avaliacao> convertResultToObjectList(ResultSet resultSet) throws SQLException {
        List<Avaliacao> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(convertResultToObject(resultSet));
        }
        return result;
    }

    public List<Avaliacao> findAllByDisciplina(Disciplina disciplina) throws SQLException {
        Connection conn = ConnectDataBase.connect();
        PreparedStatement result = conn.prepareStatement(
                "SELECT * FROM avaliacao where id_disciplina = ?"
        );
        result.setLong(1, disciplina.getId());
        return convertResultToObjectList(result.executeQuery());
    }

    public List<Avaliacao> findAllByAluno(Aluno aluno) throws SQLException {
        Connection conn = ConnectDataBase.connect();
        PreparedStatement result = conn.prepareStatement(
                "SELECT * FROM avaliacao where id_aluno = ?"
        );
        result.setLong(1, aluno.getId());
        return convertResultToObjectList(result.executeQuery());
    }

    public List<Avaliacao> findAllByAlunoDisciplina(Aluno aluno, Disciplina disciplina) throws SQLException {
        Connection conn = ConnectDataBase.connect();
        PreparedStatement result = conn.prepareStatement(
                "SELECT * FROM avaliacao where id_aluno = ? and id_disciplina = ?"
        );
        result.setLong(1, aluno.getId());
        result.setLong(2, disciplina.getId());
        return convertResultToObjectList(result.executeQuery());
    }


}
