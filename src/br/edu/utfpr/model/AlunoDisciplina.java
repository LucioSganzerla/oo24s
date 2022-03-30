package br.edu.utfpr.model;

import br.edu.utfpr.sql.CreateTableHelper;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AlunoDisciplina extends Model{

    private int idAluno;
    private int idDisciplina;
    private String semestreParticipacao;

    @Override
    public CreateTableHelper generateCreateTableSQL() {
        String createTable = "" +
                "CREATE TABLE IF NOT EXISTS aluno_disciplina (" +
                "id SERIAL PRIMARY KEY, " +
                "id_aluno INT NOT NULL, " +
                "id_disciplina INT NOT NULL, " +
                "semestre_participacao VARCHAR(50) NOT NULL);";


        String dropTable = "" +
                "DROP TABLE IF EXISTS aluno_disciplina";

        return new CreateTableHelper(createTable, dropTable);
    }
}



