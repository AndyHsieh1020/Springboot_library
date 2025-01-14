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

import model.InventoryModel;
import service.InventoryService;

@RestController
public class InventoryController {
	
	@Autowired
	InventoryModel inventorymodel;
	@Autowired
	InventoryService inventoryservice;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
	@PostMapping("/inventory")
	public String addBook(@RequestBody InventoryModel bookmodel) {
		inventoryservice.addBook(bookmodel);
		return "Inventory added";
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.PUT})
	@PutMapping("/inventory/{id}")
	public String editBook(@PathVariable int id, @RequestBody InventoryModel bookmodel) {
		bookmodel.setInventoryId(id);
		inventoryservice.editBook(bookmodel);
		return "Inventory edited";
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.DELETE})
	@DeleteMapping("/inventory/{id}")
	public String deleteBook(@PathVariable int id) {
		inventoryservice.deleteBook(id);
		return "Inventory deleted";
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
	@GetMapping("/inventory")
	public List<Map<String,Object>> listBook() {
		return inventoryservice.listAllbooks();
	}
	//@GetMapping("/inventory/{id}")
    //public InventoryModel getInventory(@PathVariable Long id) {
        //return inventoryService.getInventory(id);
    //}
}
