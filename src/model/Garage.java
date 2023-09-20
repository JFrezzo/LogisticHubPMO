package model;

import java.util.List;


/**
 * 
 * @author jacopo
 * this interface abstracts the functions that each garage must make available for the users
 */
public interface Garage {

	void setNew(final List<Vehicle> vehicle);
	List<Vehicle> getAllVehicles();
	Vehicle getVehicle(final int index);
	Vehicle getVehicleById(final String id);
	boolean find(final String id);
	void add(final Vehicle newVehicle) throws IllegalArgumentException;
	void remove(final String id);
	void edit(final Vehicle newVehicle) throws IllegalArgumentException;
	int getSize();
}
