package br.edu.utfpr.model;

import br.edu.utfpr.database.ConnectDataBase;
import br.edu.utfpr.sql.CreateTableHelper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@Setter
public abstract class Model {

    private int id;

    public abstract CreateTableHelper generateCreateTableSQL();

    public void createTable() {
        try {
            Connection conn = ConnectDataBase.createConnections();

            PreparedStatement psCreate = conn.prepareStatement(
                    generateCreateTableSQL().getCreateTable());
            PreparedStatement psDrop = conn.prepareStatement(
                    generateCreateTableSQL().getDropTable());

            psDrop.executeUpdate();
            psCreate.executeUpdate();

            psCreate.close();
            psDrop.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("ERRO AO CRIAR TABELA");
            e.printStackTrace();
        }
    }

}
