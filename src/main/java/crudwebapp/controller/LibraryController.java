package crudwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import crudwebapp.model.Library;
import crudwebapp.repository.LibraryRepository;
import io.swagger.annotations.Api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by arnoldas on 17.6.12.
 */

@RestController
@Api //swagger
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryRepository libraryRepository;

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public void createOrUpdateLibrary(@RequestBody Library library) {
        libraryRepository.createOrUpdate(library);
    }

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseStatus(OK)
    public Library getLibraryById(@PathVariable("id") Long id) {
        return libraryRepository.findById(id);
    }
}

