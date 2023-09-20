package model;

/**
 * 
 * @author jacopo
 * this interface abstracts the functions that each vehicle must make available for the users
 */
public interface Vehicle {
	String getId();
	String getDescription();
	VehicleType getType();
	void setId(String id);
	void setDescription(String description);
	void setType(VehicleType type);
	void setContainer(Container c);
	Container releaseContainer();

}
