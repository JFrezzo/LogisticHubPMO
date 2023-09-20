package model;

/**
 * 
 * @author jacopo
 * This class allows users to represent name-value pair without specify in advance the types of the 2 object name and value
 * @param <X>
 * @param <Y>
 */
public final class Pair<X, Y> {

	private X x;
	private Y y;
	
	
	public Pair(final X x, final Y y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	
	public X getFirst() {
		return x;
	}
	
	public Y getSecond() {
		return y;
	}
	
	public void setSecondValue(Y y) {
		this.y = y;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
