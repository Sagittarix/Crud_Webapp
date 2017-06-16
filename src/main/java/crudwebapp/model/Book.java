package crudwebapp.model;

import javax.persistence.Entity;

import crudwebapp.model.generic.GenericEntity;

@Entity
public class Book extends GenericEntity {

    private String title;
    private String author;



    /*===============================================*/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
