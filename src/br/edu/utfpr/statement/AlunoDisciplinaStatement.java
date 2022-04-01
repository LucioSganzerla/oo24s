package br.edu.utfpr.statement;

import br.edu.utfpr.database.ConnectDataBase;
import br.edu.utfpr.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AlunoDisciplinaStatement implements Statement<AlunoDisciplina> {

    @Override
    public String sqlCreateTable() {
        return "" +
                "CREATE TABLE IF NOT EXISTS aluno_disciplina (" +
                "id SERIAL PRIMARY KEY, " +
                "id_aluno int8 NOT NULL, " +
                "id_disciplina int8 NOT NULL, " +
                "semestre_participacao VARCHAR(50) NOT NULL);";
    }

    @Override
    public String sqlDropTable() {
        return "DROP TABLE IF EXISTS aluno_disciplina";
    }

    @Override
    public PreparedStatement findAll(Connection conn) throws SQLException {
        return conn.prepareStatement(
                "SELECT * FROM aluno_disciplina"
        );
    }

    @Override
    public PreparedStatement salvar(Connection conn, AlunoDisciplina aluno) throws SQLException {
        PreparedStatement psSalvar = conn.prepareStatement(
                "INSERT INTO aluno_disciplina(id_aluno, id_disciplina, semestre_participacao) " +
                        "VALUES(?, ?, ?)", RETURN_GENERATED_KEYS
        );
        psSalvar.setLong(1, aluno.getId_aluno());
        psSalvar.setLong(2, aluno.getId_disciplina());
        psSalvar.setString(3, aluno.getSemestre_participacao());
        return psSalvar;
    }

    @Override
    public AlunoDisciplina convertResultToObject(ResultSet resultSet) throws SQLException {
        return AlunoDisciplina.builder()
                .id(resultSet.getLong(1))
                .id_aluno(resultSet.getLong(2))
                .id_disciplina(resultSet.getLong(3))
                .semestre_participacao(resultSet.getString(4))
                .build();
    }

    @Override
    public List<AlunoDisciplina> convertResultToObjectList(ResultSet resultSet) throws SQLException {
        List<AlunoDisciplina> result = new ArrayList<>();
        while (resultSet.next()) {
            AlunoDisciplina alunoDisciplina = convertResultToObject(resultSet);
            result.add(alunoDisciplina);
        }
        return result;
    }

}
