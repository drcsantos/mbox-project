package br.com.mbox.api.service;

import br.com.mbox.api.domain.model.Quote;
import br.com.mbox.api.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteService extends AbstractDataService<Quote, Long> {

    private static List<Quote> cachedQuotes;

    @Autowired
    private QuoteRepository repository;

    @Override
    protected JpaRepository<Quote, Long> getRepository() {
        return repository;
    }

    public Quote getRandomizedQuote() {
        List<Quote> quotes = getCachedQuotes();

        if (quotes.size() == 0)
            return null;

        Random randomIdGenerator = new Random();
        int quoteIndex = randomIdGenerator.nextInt(quotes.size());
        Quote randomizedQuote = quotes.get(quoteIndex);

        randomizedQuote.setANewView();
        this.save(randomizedQuote);

        return randomizedQuote;
    }

    public void likeQuote(Long id) {
        Quote quote = getQuoteFromCachedList(id);

        quote.setANewLike();
        this.save(quote);
    }

    public void unlikeQuote(Long id) {
        Quote quote = getQuoteFromCachedList(id);

        quote.setANewUnlike();
        this.save(quote);
    }

    private Quote getQuoteFromCachedList(Long id) {
        List<Quote> quotes = getCachedQuotes();
        int quoteIndex = indexOfQuoteById(quotes.toArray(new Quote[quotes.size()]), id);

        if (quoteIndex >= 0)
            return quotes.get(quoteIndex);

        return null;
    }

    public static int indexOfQuoteById(Quote[] array, Long id) {
        int lowIndex = 0;
        int highIndex = array.length - 1;
        while (lowIndex <= highIndex) {
            // Id is in array[lowIndex..highIndex] or not present.
            int middleIndex = lowIndex + (highIndex - lowIndex) / 2;
            Long quoteId = array[middleIndex].getId();
            if (id < quoteId)
                highIndex = middleIndex - 1;
            else if (id > quoteId)
                lowIndex = middleIndex + 1;
            else
                return middleIndex;
        }
        return -1;
    }

    private List<Quote> getCachedQuotes() {
        if (QuoteService.cachedQuotes == null)
            resetCachedQuotes();

        return QuoteService.cachedQuotes;
    }

    private void resetCachedQuotes() {
        QuoteService.cachedQuotes = this.getAll();
    }

}
