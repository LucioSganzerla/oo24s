package br.edu.utfpr.repository;

import br.edu.utfpr.database.ConnectDataBase;
import br.edu.utfpr.model.Disciplina;

import java.sql.*;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class DisciplinaRepository implements Repository<Disciplina>{

    @Override
    public List<Disciplina> buscarTodos() {
        return null;
    }

    @Override
    public Disciplina salvar(Disciplina disciplina) {
        Connection conn = ConnectDataBase.createConnections();
        try {
            PreparedStatement psSalvar = conn.prepareStatement(
                    "INSERT INTO disciplina(nome) " +
                            "VALUES(?)", RETURN_GENERATED_KEYS
            );
            psSalvar.setString(1, disciplina.getNome());

            int linhasAfetadas = psSalvar.executeUpdate();
            ResultSet generatedKeys = psSalvar.getGeneratedKeys();

            if(linhasAfetadas == 0)
                System.out.printf("ERRO AO ADICIONAR DISCIPLINA %s%n", disciplina.getNome().toUpperCase());
            else {
                if(generatedKeys.next())
                    disciplina.setId(generatedKeys.getInt(1));
            }
            psSalvar.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERRO AO CADASTRAR DISCIPLINA");
        }
        return disciplina;
    }

    @Override
    public Disciplina atualizar(Disciplina disciplina) {
        return null;
    }

    @Override
    public boolean remover(Disciplina disciplina) {
        return false;
    }

    @Override
    public boolean remover(int i) {
        return false;
    }
}
