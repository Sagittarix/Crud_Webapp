package crudwebapp.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import crudwebapp.model.Book;

/**
 * Created by arnoldas on 17.6.14.
 */
public interface BookRepository {
    @Transactional
    Book createOrUpdate(Book book);

    Book findById(Long bookId);

    List<Book> findAll();

    void delete(Long id);
}
