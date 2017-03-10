package br.com.mbox.api.controller;

import br.com.mbox.api.domain.controller.IRestController;
import br.com.mbox.api.domain.model.AbstractModel;
import br.com.mbox.api.service.AbstractDataService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractRestController<T extends AbstractModel<Long>, Long extends Serializable> implements IRestController<T, Long> {

    protected abstract AbstractDataService<T, Long> getService();

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<T> list() {
        return getService().getAll();
    }

    @Override
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public T post(T entity) {
        return getService().save(entity);
    }

    @Override
    @RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
    public HttpStatus put(@PathVariable Long id, T entity) {
        try {
            entity.setId(id);
            getService().save(entity);

            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public HttpStatus delete(@PathVariable Long id) {
        try {
            getService().delete(id);

            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
