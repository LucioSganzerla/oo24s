package br.edu.utfpr.repository;

import br.edu.utfpr.database.ConnectDataBase;
import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.Disciplina;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.*;

public class AlunoRepository implements Repository<Aluno> {

    @Override
    public List<Aluno> buscarTodos() {
        Connection conn = ConnectDataBase.createConnections();
        List<Aluno> retorno = new ArrayList<>();
        try {
            PreparedStatement psBuscar = conn.prepareStatement(
                    "SELECT * FROM aluno"
            );
            findAndBuild(conn, retorno, psBuscar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    @Override
    public Aluno salvar(Aluno aluno) {
        Connection conn = ConnectDataBase.createConnections();
        try {
            PreparedStatement psSalvar = conn.prepareStatement(
                    "INSERT INTO aluno(nome, telefone, email, dataNascimento) " +
                            "VALUES(?, ?, ?, ?)", RETURN_GENERATED_KEYS
            );
            psSalvar.setString(1, aluno.getNome());
            psSalvar.setString(2, aluno.getTelefone());
            psSalvar.setString(3, aluno.getEmail());
            psSalvar.setDate(4, Date.valueOf(aluno.getDataNascimento()));

            int linhasAfetadas = psSalvar.executeUpdate();
            ResultSet generatedKeys = psSalvar.getGeneratedKeys();

            if(linhasAfetadas == 0)
                System.out.printf("ERRO AO ADICIONAR ALUNO %s%n", aluno.getNome().toUpperCase());
            else {
                if(generatedKeys.next())
                    aluno.setId(generatedKeys.getInt(1));
            }
            psSalvar.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERRO AO CADASTRAR ALUNO");
        }
        return aluno;
    }

    @Override
    public Aluno atualizar(Aluno aluno) {
        return null;
    }

    @Override
    public boolean remover(Aluno aluno) {
        remover(aluno.getId());
        return false;
    }

    @Override
    public boolean remover(int i) {
        Connection conn = ConnectDataBase.createConnections();
        try {
            PreparedStatement psExcluir = conn.prepareStatement("" +
                    "DELETE FROM aluno WHERE id=?"
            );
            psExcluir.setInt(1, i);
            int linhasAfetadas = psExcluir.executeUpdate();
            psExcluir.close();
            conn.close();

            return linhasAfetadas == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERRO AO CADASTRAR ALUNO");
        }
        return false;
    }

    public List<Aluno> buscarPorDisciplina(Disciplina disciplina) {
     return buscarPorDisciplina(disciplina.getId());
    }

    public List<Aluno> buscarPorDisciplina(int id) {
        Connection conn = ConnectDataBase.createConnections();
        List<Aluno> retorno = new ArrayList<>();
        try {
            PreparedStatement psBuscar = conn.prepareStatement(
                    "SELECT a.* FROM aluno a " +
                    "INNER JOIN aluno_disciplina d on d.id_disciplina = ?"
            );
            psBuscar.setInt(1, id);
            findAndBuild(conn, retorno, psBuscar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    private void findAndBuild(Connection conn, List<Aluno> retorno, PreparedStatement psBuscar) throws SQLException {
        psBuscar.executeQuery();
        ResultSet resultSet = psBuscar.getResultSet();
        while (resultSet.next()) {
            Aluno aluno = Aluno.builder()
                    .nome(resultSet.getString(2))
                    .telefone(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .dataNascimento(LocalDate.parse(resultSet.getString(5)))
                    .build();
            aluno.setId(resultSet.getInt(1));
            retorno.add(aluno);
        }
        psBuscar.close();
        conn.close();
    }

}
