package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import model.BookModel;

@Repository
public class BookRepository {
	@Autowired
	private JdbcTemplate jdbctemplate;
	public void addBook(BookModel bookmodel) {
		System.out.println("EXCUTE INSERT BOOK");
		jdbctemplate.update("INSERT INTO Book(ISBN, Name, Author, Introduction, ImgUrl)"+"VALUES(?,?,?,?,?)"
		,bookmodel.getisbn(),bookmodel.getname(),bookmodel.getauthor(),bookmodel.getintroduction(),bookmodel.getimgurl());
	}
	
	public List<Map<String, Object>> loadAllbooks() {
		System.out.println("EXCUTE LOAD ALL BOOKS");
		return jdbctemplate.queryForList("SELECT * FROM Book");
	}
	
	public void editBook(BookModel bookmodel) {
		System.out.println("EDIT BOOK");
		jdbctemplate.update("UPDATE Book SET Name = ?, Author = ?, Introduction = ?, ImgUrl = ?  WHERE ISBN = ?"
				,bookmodel.getname(),bookmodel.getauthor(),bookmodel.getintroduction(),bookmodel.getimgurl(),bookmodel.getisbn());
	}
	
	public void deleteBook(BookModel bookmodel) {
		System.out.println("DELETE BOOK");
		jdbctemplate.update("DELETE FROM Book WHERE ISBN = ?",bookmodel.getisbn());
	}

}
