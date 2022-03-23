package br.edu.utfpr.repository;

import br.edu.utfpr.database.ConnectDataBase;
import br.edu.utfpr.model.Aluno;

import java.sql.*;

public class AlunoRepository {

    public Aluno salvar(Aluno aluno) {
        Connection con = ConnectDataBase.createConnections();
        int linhasAfetadas = 0;
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO aluno (nome, telefone, email, dataNascimento) VALUES (?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getTelefone());
            statement.setString(3, aluno.getEmail());
            statement.setDate(4, Date.valueOf(aluno.getDataNascimento()));
            linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas == 0) {
                System.out.println("Não foi possível salvar o aluno " + aluno.getNome().toUpperCase());
            } else {
                ResultSet getGeneratedKeys = statement.getGeneratedKeys();
                if (getGeneratedKeys.next()) {
                    aluno.setId(getGeneratedKeys.getInt(1));
                    System.out.println("Aluno " + aluno.getNome().toUpperCase() + " salvo com sucesso!");
                }
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar aluno");
        }

        return aluno;
    }

}
