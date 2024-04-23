package service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.InventoryModel;
import repository.InventoryRepository;

@Service
public class InventoryService {
	@Autowired
	InventoryRepository inventoryrepository;
	public void addBook(InventoryModel bookmodel) {
		inventoryrepository.addBook(bookmodel);
	}
	public void editBook(InventoryModel bookmodel) {
		inventoryrepository.editBook(bookmodel);
	}
	public void deleteBook(int id) {
		inventoryrepository.deleteBook(id);
	}
	public List<Map<String,Object>> listAllbooks() {
		return inventoryrepository.loadAllbooks();
	}
}
