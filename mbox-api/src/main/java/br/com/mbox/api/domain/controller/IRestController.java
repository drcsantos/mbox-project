package br.com.mbox.api.domain.controller;

import br.com.mbox.api.domain.model.AbstractModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

/**
 * Created by diego.santos on 07/03/2017.
 */
public interface IRestController<T extends AbstractModel<Long>, Long extends Serializable> {
    @RequestMapping(method = RequestMethod.GET)
    List<T> list();

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    T post(T entity);

    @RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
    HttpStatus put(@PathVariable Long id, T entity);

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    HttpStatus delete(@PathVariable Long id);
}
