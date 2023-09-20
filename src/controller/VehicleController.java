package controller;

import java.io.FileWriter;
import java.io.IOException;

import model.ConcreteGarage;
import model.ConcreteVehicle;
import model.Vehicle;
import model.VehicleType;
import view.VehicleObserver;
import view.VehicleViewImpl;

public class VehicleController implements VehicleObserver {

	
	private final VehicleViewImpl view;
	private final ConcreteGarage model;
	
	public VehicleController() {
		
		this.model = ConcreteGarage.getInstance();
		this.view = new VehicleViewImpl();
		this.view.setObserver(this);
		this.view.start();
		
		
		
	}
	
	private void createVehicleObject(final int op, final String id, final String description, final String type) {
		
		/*
		 * op = 0 for adding vehicle in the garage
		 * op = 1 for edit vehicle in the garage
		 */
		
		Vehicle v = null;
		
		try {
			
		
		v = new ConcreteVehicle(id, description, VehicleType.values()[Integer.parseInt(type)]);
		
		if(op == 0)model.add(v);
		if(op == 1)model.edit(v);
		
		} catch(NumberFormatException e){
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
	public void addVehicle(final String id, final String description, final String type) {
		createVehicleObject(0, id, description, type);
	}

	@Override
	public void removeVehicle(final String id) {
		try {
		model.remove(id);	
		} catch(IllegalArgumentException e) {
			view.showMessage(e.getMessage());
		}
	}

	@Override
	public void editVehicle(final String id, final String description, final String type) {
		createVehicleObject(1, id, description, type);
	}

	
	

	@Override
	public void exportVehicle() throws IOException {
		

		FileWriter f = new FileWriter(MenuController.PATHVEHICLE);
		
		for(Vehicle v : model.getAllVehicles()) {
			
			f.write(v.getId()+";"+v.getDescription()+";"+v.getType()+ "\n");
		}
		
		
		f.close();
	

	}

	@Override
	public int getSize() {
		return this.model.getSize();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		Vehicle v = this.model.getVehicle(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return v.getId();
		case 1:
			return v.getDescription();
		case 2:
			return v.getType();
		
		default:
			return null;
	}
	}

	@Override
	public int getType(final int rowIndex) {
		return this.model.getVehicle(rowIndex).getType().ordinal();
	}

	@Override
	public void backToMenu() {
		new MenuController();
		this.quit();
	}

}
