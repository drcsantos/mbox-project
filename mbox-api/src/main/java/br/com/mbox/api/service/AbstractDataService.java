package br.com.mbox.api.service;

import br.com.mbox.api.domain.model.AbstractModel;
import br.com.mbox.api.domain.service.IDataService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;


public abstract class AbstractDataService<T extends AbstractModel<Long>, Long extends Serializable> implements IDataService<T, Long> {

    protected abstract JpaRepository<T, Long> getRepository();

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public T getById(Long id) {
        T entity = getRepository().findOne(id);
        return entity;
    }

    @Override
    public void delete(Long id) {
        try {
            getRepository().delete(id);
        } catch (EmptyResultDataAccessException e) {}
    }

}