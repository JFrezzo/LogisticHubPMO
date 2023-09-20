package controller;

import java.io.FileWriter;
import java.io.IOException;

import model.ConcreteProduct;
import model.Hub;
import model.Product;
import model.ProductType;
import view.ProductObserver;
import view.ProductViewImpl;

public class ProductController implements ProductObserver {

	private final ProductViewImpl view;
	private final Hub model;
	
	public ProductController() {
		
		this.model = Hub.getInstance();
		this.view = new ProductViewImpl();
		this.view.setObserver(this);
		this.view.start();
		
	}
	
	private void createProductObject(final int op, final String id,final String description, final String type, final String weight, final String price) {
		
		// op = 0 for adding product in warehouse
		// op = 1 for edit product in warehouse
		
		Product p = null;
		
		try {
			
			p = new ConcreteProduct(id, description, ProductType.values()[Integer.parseInt(type)] , Double.parseDouble(weight), Double.parseDouble(price));
			
			if(op == 0) model.add(p);
			if(op ==1)  model.edit(p);
		
		
		}catch(NumberFormatException e){
			view.showMessage("CASTING ERROR");
		}
		catch(IllegalArgumentException e) {
			view.showMessage(e.getMessage());
		} catch(Exception e) {
			view.showMessage("TYPE ERROR");
		} 
		
	}
	
	@Override
	public void quit() {
		view.dispose();
	}

	@Override
	public void addProduct(final String id,final String description, final String type, final String weight, final String price) {
		createProductObject(0, id, description, type, weight, price);
	}

	@Override
	public void removeProduct(final String id) {
		try {
			model.remove(id);	
		} catch(IllegalArgumentException e) {
			view.showMessage(e.getMessage());
		}
	}

	@Override
	public void editProduct(final String id,final String description, final String type, final String weight, final String price) {
		createProductObject(1, id, description, type, weight, price);
	}


	
	@Override
	public void exportProduct() throws IOException {
		
		FileWriter f = new FileWriter(MenuController.PATHPRODUCT);
		
		for(Product p : model.getAllProducts()) {
			
			f.write(p.getId()+";"+p.getDescription()+";"+p.getType()+ ";"+ p.getWeight() +";"+ p.getPrice()+ "\n");
		}
		
		
		f.close();
	}

	@Override
	public int getSize() {
		return this.model.getSize();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		Product p = this.model.getProduct(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return p.getId();
		case 1:
			return p.getDescription();
		case 2:
			return p.getType();
		case 3:
			return p.getWeight();
		case 4:
			return p.getPrice();
		default:
			return null;
	}
		
		
	
	}

	
	public int getType(final int rowIndex) {
		return this.model.getProduct(rowIndex).getType().ordinal(); /*position in enum declaration */
	}
	
	
	public void backToMenu() {
		
		new MenuController();
		this.quit();
	}



}
