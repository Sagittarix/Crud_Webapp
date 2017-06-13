package crudwebapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crudwebapp.model.Human;

/**
 * Created by arnoldas on 17.6.12.
 */


@Repository
public class HumanRepositoryImplDB implements HumanRepository {
    private static final String FIND_ALL = "SELECT x FROM Human x";

    @Autowired
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Human> findAllHumans() {
        return em.createQuery(FIND_ALL).getResultList();
    }

    @Override
    public Human findHumanById(Long id) {
        Human human = em.find(Human.class, id);
        if (human != null) {
            return human;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Human createOrUpdateHuman(Human human) {
        if (human.getId() == null) {
            em.persist(human);
            return human;
        } else {
            Human merged = em.merge(human);
            em.persist(merged);
            return merged;
        }
    }

    @Override
    @Transactional
    public void deleteHuman(Long id) {
        Human human = em.find(Human.class, id);
        em.remove(human);
    }

}

