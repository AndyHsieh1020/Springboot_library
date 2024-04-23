package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import model.InventoryModel;

@Repository
public class InventoryRepository {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public void addBook(InventoryModel bookmodel) {
		System.out.println("EXCUTE INSERT Inventory");
		jdbctemplate.update("INSERT INTO Inventory(ISBN, Store_time, Status)"+"VALUES(?,?,'在庫')"
		,bookmodel.getIsbn(),bookmodel.getStoreTime());
	}
	
	public List<Map<String, Object>> loadAllbooks() {
		System.out.println("EXCUTE LOAD ALL Inventory");
		return jdbctemplate.queryForList("SELECT Inventory.*, Book.Name AS Name FROM Inventory JOIN Book ON Inventory.ISBN = Book.ISBN");
	}
	
	public void editBook(InventoryModel bookmodel) {
		System.out.println("EDIT Inventory");
		jdbctemplate.update("UPDATE Inventory SET ISBN = ?, Store_time = ?, Status = ? WHERE Inventory_id = ?"
				,bookmodel.getIsbn(),bookmodel.getStoreTime(),bookmodel.getStatus(),bookmodel.getInventoryId());
	}
	
	public void deleteBook(int id) {
		System.out.println("DELETE Inventory");
		jdbctemplate.update("DELETE FROM Inventory WHERE Inventory_id = ?", id);
	}


}
