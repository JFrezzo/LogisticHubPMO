package model;

/**
 * 
 * @author jacopo
 * Concrete class of Vehicle.
 * this class allows to represent vehicles.
 */
public class ConcreteVehicle implements Vehicle {

	
	private String id;
	private String description;
	private VehicleType type;
	private Container container;

	
	
	public ConcreteVehicle(
			final String id,
			final String description,
			final VehicleType type
			) {
		
		
		this.id = id;
		this.description = description;
		this.type = type;

		
		
		
		
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public VehicleType getType() {
		return type;
	}


	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setType(VehicleType type) {
		this.type = type;
	}

	
	public String[] toArray() {
		return new String[] {id, description, type+""};
	}

	@Override
	public void setContainer(Container c) {
		if(c.getTypeVehicle() == this.type) this.container = c;
		else throw new IllegalArgumentException("THIS VEHICLE CAN NOT TRANSPORT THIS CONTAINER");
	
	}

	@Override
	public Container releaseContainer() {
		Container c = this.container;
		this.container = null;
		return c;
	}

	
	
}
