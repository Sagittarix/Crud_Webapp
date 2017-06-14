package crudwebapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String author;

	@ManyToMany(mappedBy = "borrowedBooks")
	private List<Human> bookReaders;


	/*===============================================*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Human> getBookReaders() {
		if (bookReaders == null) {
			bookReaders = new ArrayList<>();
		}
		return bookReaders;
	}

	public void setBookReaders(List<Human> bookReaders) {
		this.bookReaders = bookReaders;
	}
}
