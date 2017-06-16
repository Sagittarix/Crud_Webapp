package crudwebapp.repository;

import org.springframework.stereotype.Repository;

import crudwebapp.model.Book;
import crudwebapp.repository.generic.GenericRepository;

@Repository
public class BookRepository extends GenericRepository<Book> {

    public BookRepository() {
        super(Book.class);
    }
}
