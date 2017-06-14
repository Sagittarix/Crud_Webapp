package crudwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import crudwebapp.model.Book;
import crudwebapp.repository.BookRepository;
import crudwebapp.repository.BookRepositoryImpl;
import io.swagger.annotations.Api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by arnoldas on 17.6.12.
 */

@RestController
@Api //swagger
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository = new BookRepositoryImpl();

    @RequestMapping(method = GET)
    @ResponseStatus(OK)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public void createOrUpdateBook(@RequestBody Book book) {
        bookRepository.createOrUpdate(book);
    }

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseStatus(OK)
    public Book getHumanById(@PathVariable("id") Long id) {
        return bookRepository.findById(id);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseStatus(NO_CONTENT)
    public void deleteHumanById(@PathVariable("id") Long id) {
        bookRepository.delete(id);
    }

}

