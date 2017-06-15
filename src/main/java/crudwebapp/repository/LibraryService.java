//package crudwebapp.repository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import crudwebapp.model.Book;
//import crudwebapp.model.Human;
//import crudwebapp.model.Library;
//
//@Service
//public class LibraryService {
//
//	@Autowired
//	private LibraryRepository libraryRepository;
//
//	@Autowired
//	private HumanRepository humanRepository = new HumanRepositoryImpl();
//
//	@Autowired
//	private BookRepository bookRepository;
//
//	public Library getLibrary(Long id) {
//		return libraryRepository.find(id);
//	}
//
//	public Library createLibrary(Library l) {
//		return libraryRepository.saveOrUpdate(l);
//	}
//
//	@Transactional
//	public Human joinNewReader(Long libraryId, Human r) {
//		Human savedReader = humanRepository.createOrUpdate(r);
//		Library library = libraryRepository.findById(libraryId);
//		library.getReaders().add(savedReader);
//
//		return savedReader;
//	}
//
//	@Transactional
//	public Book enterNewBook(Long libraryId, Book b) {
//		Book savedBook = bookRepository.saveOrUpdate(b);
//		Library library = libraryRepository.find(libraryId);
//		library.getBooks().add(savedBook);
//		return savedBook;
//	}
//
//	@Transactional
//	public Human borrowBook(Long bookId, Long readerId) {
//		Human reader = humanRepository.find(readerId);
//		Book book = bookRepository.find(bookId);
//		reader.addBorrowedBook(book);
//		return reader;
//	}
//}
