package br.edu.utfpr.statement;

import br.edu.utfpr.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Statement<T extends Model> {
    String sqlCreateTable();
    String sqlDropTable();
    PreparedStatement findAll(Connection conn) throws SQLException;
    PreparedStatement salvar(Connection conn, T t) throws SQLException;
    T convertResultToObject(ResultSet resultSet) throws SQLException;
    List<T> convertResultToObjectList(ResultSet resultSet) throws SQLException;
}
