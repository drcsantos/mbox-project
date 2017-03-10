package br.com.mbox.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Quote extends AbstractModel<Long> {

    @Column(nullable = false)
    private String text;
    private String author;    
    private Long likes = 1L;
    private Long unlikes = 0L;
    private Long views = 1L;

    public Quote() {}

    public Quote(String text, String author, Long likes, Long unlikes, Long views) {
        this.text = text;
        this.author = author;
        this.likes = likes;
        this.unlikes = unlikes;
        this.views = views;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return (author != null && !author.trim().isEmpty()) ? author : "Unknow" ;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getLikes() {
        return likes;
    }

    public void setANewLike() {
        this.likes += 1;
    }

    public Long getUnlikes() {
        return unlikes;
    }

    public void setANewUnlike() {
        this.unlikes += 1;
    }

    public Long getViews() {
        return views;
    }

    public void setANewView() {
        this.views += 1;
    }

    @Override
    public String toString() {
        return "\"" + this.text + "\" by " + this.author;
    }
}