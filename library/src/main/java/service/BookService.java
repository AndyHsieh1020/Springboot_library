package service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.BookModel;
import repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookrepository;
	public void addBook(BookModel bookmodel) {
		bookrepository.addBook(bookmodel);
	}
	public void editBook(BookModel bookmodel) {
		bookrepository.editBook(bookmodel);
	}
	public void deleteBook(String isbn) {
		bookrepository.deleteBook(isbn);
	}
	public List<Map<String,Object>> listAllbooks() {
		return bookrepository.loadAllbooks();
	}
}
