package crudwebapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crudwebapp.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@Autowired
	private EntityManager em;


	@Override
	public List<Book> findAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
		Root<Book> bookRoot = criteriaQuery.from(Book.class);

		criteriaQuery.select(bookRoot);
		criteriaQuery.where(criteriaBuilder.isNotNull(bookRoot.get("id")));

		final TypedQuery<Book> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Book createOrUpdate(Book book) {
		if (book.getId() == null) {
			em.persist(book);
			return book;
		} else {
			Book updatedBook = em.merge(book);
			em.persist(updatedBook);
			return updatedBook;
		}
	}

	@Override
	public Book findById(Long bookId) {
		Book found = em.find(Book.class, bookId);
		return found;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Book book = em.find(Book.class, id);
		em.remove(book);
	}
}
