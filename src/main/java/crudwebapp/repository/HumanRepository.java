package crudwebapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import crudwebapp.model.Human;

/**
 * Created by arnoldas on 17.6.12.
 */
public interface HumanRepository {

    @SuppressWarnings("unchecked")
    List<Human> findAllHumans();

    Human findHumanById(Long id);

    @Transactional
    void createOrUpdateHuman(Human human);

    @Transactional
    void deleteHuman(Long id);
}
