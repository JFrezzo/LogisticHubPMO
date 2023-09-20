package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import model.ConcreteGarage;
import model.ConcreteProduct;
import model.ConcreteVehicle;
import model.Hub;
import model.Product;
import model.ProductType;
import model.Vehicle;
import model.VehicleType;
import view.MenuButtonType;
import view.MenuObserver;
import view.MenuViewImpl;

public class MenuController implements MenuObserver {

	
	public final static  String PATHVEHICLE = "vehicle.txt";
	public final static String PATHPRODUCT = "product.txt";
	
	
	private final MenuViewImpl view;
	private final ConcreteGarage garage;	
	private final Hub hub;



	public MenuController() {
		this.garage = ConcreteGarage.getInstance();		
		this.hub = Hub.getInstance();

		
		
		this.view = new MenuViewImpl();
		this.view.setObserver(this);
		this.view.start();
		
		this.importAll();

	}
	
	private void importAll() {
		
		importProducts();
		importVehicles();
	}
	
	
	private void importProducts() {
		
		BufferedReader reader;
		List<Product> list = new ArrayList<>();
		try {
			
		    File file = new File(PATHPRODUCT);
		      
	        FileReader fr = new FileReader(file);
	          
	        reader = new BufferedReader(fr);
	          
	        String line = reader.readLine();
	          
	        while (line != null) {
	        	
	        
	        	String[] values = line.split(";");
	        	list.add(new ConcreteProduct(
	        			values[0],
	        			values[1],
	        			ProductType.valueOf(values[2]),
	        			Double.parseDouble(values[3]),
	        			Double.parseDouble(values[4])
	        			));
	        	
	        			
	        			
	            line = reader.readLine();
	        }
	        
	        hub.setNew(list);
	        
		}catch (Exception e) {
			view.showMessage(e.toString());
		}
	
	}
	
	private void importVehicles() {
		
		BufferedReader reader;
		List<Vehicle> list = new ArrayList<>();
		try {
			
		    File file = new File(PATHVEHICLE);
		      
	        FileReader fr = new FileReader(file);
	          
	        reader = new BufferedReader(fr);
	          
	        String line = reader.readLine();
	          
	        while (line != null) {
	        	
	        
	        	String[] values = line.split(";");
	        	list.add(new ConcreteVehicle(
	        			values[0],
	        			values[1],
	        			VehicleType.valueOf(values[2])
	        			));
	        	
	        			
	        			
	            line = reader.readLine();
	        }
	        
	        garage.setNew(list);
	        
		}catch (Exception e) {
			view.showMessage(e.toString());
		}
		
	}
	
	
	@Override
	public void quit() {
		view.dispose();
	}



	@Override
	public void openFrame(final MenuButtonType button) {
		if(button == MenuButtonType.PRODUCT) {
			
			new ProductController();
			
		}else if(button == MenuButtonType.VEHICLE) {
			
			new VehicleController();
			
		}else if(button == MenuButtonType.SEND) {
			
			new ContainerController();
			
		}
	}
	
	
	



	public static void main(String[] args) {
		
		new MenuController(); 
		
	}
}
