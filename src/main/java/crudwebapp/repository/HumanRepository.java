package crudwebapp.repository;

import org.springframework.stereotype.Repository;

import crudwebapp.model.Human;
import crudwebapp.repository.generic.GenericRepository;

/**
 * Created by arnoldas on 17.6.12.
 */

@Repository
public class HumanRepository extends GenericRepository<Human> {

    public HumanRepository() {
        super(Human.class);
    }
}

