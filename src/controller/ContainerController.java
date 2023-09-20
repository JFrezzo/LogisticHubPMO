 package controller;

import java.util.List;

import model.ConcreteContainer;
import model.ConcreteGarage;
import model.Container;
import model.Hub;
import model.Product;
import model.Vehicle;
import view.ContainerObserver;
import view.ContainerViewImpl;

public class ContainerController implements ContainerObserver {

	private final ContainerViewImpl view;
	private final Hub hub;
	private final ConcreteGarage garage;
	private Vehicle vehicleSelected;
	private Container c;
	
	public ContainerController() {
		
		this.hub = Hub.getInstance();
		this.garage = ConcreteGarage.getInstance();
		this.view = new ContainerViewImpl();
		this.view.setObserver(this);
		this.view.start();
		
		
	}
	
	
	@Override
	public void quit() {
		 view.dispose();
	}

	@Override
	public String[] getVehicles() {

		List<Vehicle> v = this.garage.getAllVehicles();
		String[] result = new String[v.size()];
		int i = 0;
		for(Vehicle veich : v) {
			result[i] = veich.getId()+ "-"+ veich.getDescription() + "-" + veich.getType();
			i++;
		}
		
		return result;
	}

	@Override
	public String[] getProducts() {
		List<Product> p = this.hub.getAllProducts();
		String[] result = new String[p.size()];
		int i = 0;
		for(Product prod : p) {
			result[i] = prod.getId() +"-" + prod.getDescription() +"-" + prod.getType();
			i++;
		}
		return result;
	}

	@Override
	public void add(final String id, final String qta) {
		try {
			Product prod = this.hub.getProductById(id);
			
			if(this.c == null) {
				this.c = new ConcreteContainer(prod.getType(), this.vehicleSelected.getType());
			}
			
			c.add(prod, Integer.parseInt(qta));
		
		} catch(NumberFormatException e){
			view.showMessage("CASTING ERROR");
		} catch(IllegalArgumentException e) {
			view.showMessage(e.getMessage());
		}
		
	}
	
	@Override
	public void setVehicle(final String id) {
		try {
		this.vehicleSelected = garage.getVehicleById(id);
		} catch(IllegalArgumentException e) {
			view.showMessage(e.getMessage());
		}
	}


	@Override
	public List<String> getContainer() {
		return c.getContainer();
	}


	@Override
	public void send() {
		if(this.vehicleSelected != null && this.c != null) {
			if(this.c.getQuantity() > 0) {
				this.vehicleSelected.setContainer(this.c);
				new SendController(this.vehicleSelected);
			} else throw new IllegalArgumentException("ADD PRODUCT");
		} else {

			throw new IllegalArgumentException("SELECT VEHICLE AND ADD PRODUCTS");
		}
		
	}

}
