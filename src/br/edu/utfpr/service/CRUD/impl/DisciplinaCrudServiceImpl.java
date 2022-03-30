package br.edu.utfpr.service.CRUD.impl;

import br.edu.utfpr.database.ConnectDataBase;
import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.AlunoDisciplina;
import br.edu.utfpr.model.Disciplina;
import br.edu.utfpr.repository.DisciplinaRepository;
import br.edu.utfpr.service.CRUD.DisciplinaCRUDService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static br.edu.utfpr.logger.Logger.log;
import static br.edu.utfpr.logger.Tipo.ERRO;
import static br.edu.utfpr.logger.Tipo.INFO;
import static java.sql.Statement.*;

public class DisciplinaCrudServiceImpl implements DisciplinaCRUDService {

    DisciplinaRepository disciplinaRepository = new DisciplinaRepository();

    @Override
    public Disciplina salvarDisciplina(Disciplina disciplina) {
        log(INFO, "Iniciou salvarDisciplina() " + disciplina.toString());
        disciplinaRepository.salvar(disciplina);
        log(INFO, "Finalizou salvar: " + disciplina.toString());
        return disciplina;
    }

    @Override
    public AlunoDisciplina addAlunoNaDisciplina(Disciplina disciplina, Aluno aluno, String semestre) {
        return addAlunoNaDisciplina(disciplina.getId(), aluno.getId(), semestre);
    }

    @Override
    public AlunoDisciplina addAlunoNaDisciplina(int idDisciplina, int idAluno, String semestre) {
        Connection conn = ConnectDataBase.createConnections();
        var retorno = AlunoDisciplina.builder()
                .idDisciplina(idDisciplina)
                .idAluno(idAluno)
                .semestreParticipacao(semestre)
                .build();
        try {
            PreparedStatement psInsert = conn.prepareStatement(
                    "INSERT INTO aluno_disciplina(id_aluno, id_disciplina, semestre_participacao) " +
                            "VALUES (?, ?, ?)", RETURN_GENERATED_KEYS
            );
            psInsert.setInt(1, idAluno);
            psInsert.setInt(2, idDisciplina);
            psInsert.setString(3, semestre);
            var linhasAfetadas = psInsert.executeUpdate();
            var resultSet = psInsert.getGeneratedKeys();
            if(linhasAfetadas < 1)
                log(ERRO, "Falha na inserção");
            else {
                if(resultSet.next()){
                    retorno.setId(resultSet.getInt(1));
                }
            }
            psInsert.close();
            conn.close();

        } catch (SQLException e) {
            log(ERRO, "Falha na inserção");
            e.printStackTrace();
        }
        return retorno;
    }
}
