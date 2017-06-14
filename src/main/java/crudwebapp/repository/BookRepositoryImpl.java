package crudwebapp.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import crudwebapp.model.Book;

@Repository
public class BookRepositoryImpl {

	@Autowired
	private EntityManager manager;

	@Transactional
	public Book saveOrUpdate(Book book) {
		if (book.getId() == null) {
			manager.persist(book);
			return book;
		} else {
			Book updatedBook = manager.merge(book);
			manager.persist(updatedBook);
			return updatedBook;
		}
	}

	public Book find(Long bookId) {
		Book found = manager.find(Book.class, bookId);
		return found;
	}
}
