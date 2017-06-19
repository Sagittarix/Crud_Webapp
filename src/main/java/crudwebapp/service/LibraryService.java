package crudwebapp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crudwebapp.model.Book;
import crudwebapp.model.Library;
import crudwebapp.repository.BookRepository;
import crudwebapp.repository.LibraryRepository;

/**
 * Created by arnoldas on 17.6.19.
 */

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void addBookToLibrary(Long id, Book book) {

    Book savedBook = bookRepository.createOrUpdate(book); //in case book does not exist
    Library library = libraryRepository.find(id);
    library.getBooks().add(savedBook);
    }




}
