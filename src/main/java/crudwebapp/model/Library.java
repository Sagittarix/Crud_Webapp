package crudwebapp.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import crudwebapp.model.generic.GenericEntity;

@Entity
public class Library extends GenericEntity {

    private String name;

    // library knows all about the books it has. Books do not - unidirectional
    // Library - One to Many - Books
    // creates a separate table where Library id's and book id's are connected
    // books are added by adding book obejects to library object
    @OneToMany
    private Collection<Book> books = new ArrayList<>();



    /*==============================================*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

}
