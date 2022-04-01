package br.edu.utfpr.repository;

import br.edu.utfpr.model.Model;
import br.edu.utfpr.statement.Statement;

import java.util.List;

public interface Repository<T extends Statement
                            , R extends Model> {
    void createTable();
    void salvar(R r);
    List<R> findAll();
}
