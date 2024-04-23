package controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.BookModel;
import service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService bookservice;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
	@PostMapping("/book")
	public String addBook(@RequestBody BookModel bookmodel) {
		bookservice.addBook(bookmodel);
		return "Book added";
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.PUT})
	@PutMapping("/book/{isbn}")
	public String editBook(@PathVariable String isbn, @RequestBody BookModel bookmodel) {
		bookmodel.setIsbn(isbn);
		bookservice.editBook(bookmodel);
		return "Book edited";
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE})
	@DeleteMapping("/book/{isbn}")
	public String deleteBook(@PathVariable String isbn) {
		bookservice.deleteBook(isbn);
		return "Book deleted";
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
	@GetMapping("/book")
	public List<Map<String,Object>> listBook() {
		return bookservice.listAllbooks();
	}
}
