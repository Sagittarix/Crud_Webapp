package crudwebapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import crudwebapp.model.Human;

/**
 * Created by arnoldas on 17.6.12.
 */
public interface HumanRepository {

    List<Human> findAll();

    Human findById(Long id);

    @Transactional
    void createOrUpdate(Human human);

    @Transactional
    void delete(Long id);
}
