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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }

        Book book = (Book) o;

        if (getTitle() != null ? !getTitle().equals(book.getTitle()) : book.getTitle() != null) {
            return false;
        }
        return getAuthor() != null
               ? getAuthor().equals(book.getAuthor())
               : book.getAuthor() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        return result;
    }
}
