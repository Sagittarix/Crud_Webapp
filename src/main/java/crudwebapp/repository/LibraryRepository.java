package crudwebapp.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import crudwebapp.model.Library;

@Repository
public class LibraryRepository {

	@Autowired
	private EntityManager manager;

	@Transactional
	public void createOrUpdate(Library library) {
		if (library.getId() == null) {
			manager.persist(library);
		} else {
			Library libraryUpdate = manager.merge(library);
			manager.persist(libraryUpdate);
		}
	}

	public Library findById(Long id) {
		Library found = manager.find(Library.class, id);
		return found;
	}
}
