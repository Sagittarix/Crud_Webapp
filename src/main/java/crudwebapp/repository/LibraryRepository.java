package crudwebapp.repository;

import org.springframework.stereotype.Repository;

import crudwebapp.model.Library;
import crudwebapp.repository.generic.GenericRepository;

@Repository
public class LibraryRepository extends GenericRepository<Library> {

    public LibraryRepository() {
        super(Library.class);
    }
}
