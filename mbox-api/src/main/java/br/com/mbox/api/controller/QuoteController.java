package br.com.mbox.api.controller;

import br.com.mbox.api.service.AbstractDataService;
import br.com.mbox.api.service.QuoteService;
import br.com.mbox.api.domain.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("quote")
public class QuoteController extends AbstractRestController<Quote, Long> {

	@Autowired
	private QuoteService service;

	@Override
	protected AbstractDataService<Quote, Long> getService() {
		return service;
	}

	@RequestMapping(value = "/random", method = RequestMethod.GET)
	public Quote getRandomizedQuote() {
		return service.getRandomizedQuote();
	}

    @RequestMapping(value = "/like/{id}", method = RequestMethod.POST)
    public HttpStatus setLikeToQuote(@PathVariable Long id) {
        try {
            service.likeQuote(id);

            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @RequestMapping(value = "/unlike/{id}", method = RequestMethod.POST)
    public HttpStatus setUnlikeToQuote(@PathVariable Long id) {
        try {
            service.unlikeQuote(id);

            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
