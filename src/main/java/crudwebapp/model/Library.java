package crudwebapp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
//    @JoinColumn(name = "library_id") by adding this, library id will be created in table books
    private List<Book> books;



    /*==============================================*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
