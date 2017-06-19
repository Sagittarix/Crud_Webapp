package crudwebapp.service;

import java.util.List;
import java.util.stream.Collectors;

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
    public void addBookToLibrary(Long id, List<Book> books) {

        for (Book book : books) {
            Book savedBook = bookRepository.createOrUpdate(book); //in case book does not exist
            Library library = libraryRepository.find(id);
            if(library.getBooks().stream().filter(b -> b.equals(book)).count() == 0) {
                library.getBooks().add(savedBook);
            }
            libraryRepository.createOrUpdate(library);
        }
    }

    @Transactional
    public void removeBookFromLibrary(Long id, Book book) {
            Library library = libraryRepository.find(id);

            library.setBooks(library.getBooks()
                                    .stream()
                                    .filter(b -> !b.equals(book))
                                    .collect(Collectors.toList()));
            libraryRepository.createOrUpdate(library);
    }
}
