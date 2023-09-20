package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ConcreteGarage implements Garage {

	/**
	 * 
	 * @author jacopo
	 * Concrete class of Garage, use the singleton pattern.
	 * this class allows to represent the only one Garage of the project.
	 */
	private static ConcreteGarage instance;
	private List<Vehicle> garage = new ArrayList<>();
	
	private ConcreteGarage() {
		

		
	}
	
	public static ConcreteGarage getInstance() {
		if(instance == null) {
			instance = new ConcreteGarage();
		}
		return instance;
	}
	
	public void setNew(final List<Vehicle> vehicle) {
		this.garage = new ArrayList<>();
		for(Vehicle v : vehicle) {
			this.add(v);
		}
	}
	
	public List<Vehicle> getAllVehicles(){
		return  Collections.unmodifiableList(new ArrayList<>(this.garage));
	}
	

	
	public Vehicle getVehicle(final int index) {
		return garage.get(index);
	}
	
	public boolean find(final String id) {
		for(Vehicle v: this.garage) {
			if(v.getId().equals(id)) return true;
		}
		
		return false;
	}
	
	public void add(final Vehicle newVehicle) throws IllegalArgumentException {
		
		
		if(this.find(newVehicle.getId()) ) { throw new IllegalArgumentException("Id already use");}
		check(newVehicle);
		this.garage.add(newVehicle);

	}
	
	
	public void remove(final String id) {
		
		for(Vehicle v: this.garage) {
			if(v.getId().equals(id)) {
				this.garage.remove(this.garage.indexOf(v));
				
				return;
			}
		}
		
		throw new IllegalArgumentException("Id not found");
	}
	
	
	public void edit(final Vehicle newVehicle) throws IllegalArgumentException {
		
		if(!this.find(newVehicle.getId())) { 	throw new IllegalArgumentException("Id not found");} /* Id not found */
    	check(newVehicle);
        for(Vehicle v : this.garage){

            if(v.getId().equals(newVehicle.getId())){

                v.setDescription(newVehicle.getDescription());
                v.setType(newVehicle.getType());
                

                return;
            }
        }
	}
	
    public int getSize() {
    	return this.garage.size();
    }
	
    private void check(final Vehicle newVehicle) {
    	
		if(	(newVehicle.getType() != VehicleType.AIR) && (newVehicle.getType() != VehicleType.LAND)) {
			throw new IllegalArgumentException("TYPE NOT CORRECT"); /* Type incorrect */
		}

    	
    }

	@Override
	public Vehicle getVehicleById(final String id)  {
		for(Vehicle v: this.garage) {
			if(v.getId().equals(""+id)) {
				return v;
			}
		}
		
		throw new IllegalArgumentException("Vehicle not found"); 
	}
}
