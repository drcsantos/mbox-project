package br.com.mbox.api.domain.service;

import br.com.mbox.api.domain.model.AbstractModel;
import java.io.Serializable;
import java.util.List;

/**
 * Created by diego.santos on 07/03/2017.
 */
public interface IDataService<T extends AbstractModel<Long>, Long extends Serializable> {
    List<T> getAll();
    T save(T entity);
    T getById(Long id);
    void delete(Long id);
}
