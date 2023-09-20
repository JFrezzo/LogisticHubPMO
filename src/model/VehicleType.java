package model;

/**
 * 
 * @author jacopo
 * This enum represents the only two types of vehicle models
 */
public enum VehicleType {
	AIR(1000),
	LAND(10000);
	
	private final int  maxQ; /* max quantity in grams */
	private VehicleType(final int maxQ) {
		this.maxQ = maxQ;
	}
	
	public int getMaxQ() {
		return this.maxQ;
	}
}
