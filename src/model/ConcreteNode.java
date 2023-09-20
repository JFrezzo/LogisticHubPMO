package model;

import java.awt.Color;

public class ConcreteNode implements Node {

	
	private Node parent;
	private int row;
	private int col;
	private int gCost; // the distance between the current node and the start node
	private int hCost; // the distance from the current node to the goal node
	private int fCost; // the total cost (G + H ) of the node
	
	private boolean start = false;
	private boolean goal = false;
	private boolean solid = false;
	private boolean open = false;
	private boolean checked = false;
	private boolean path = false;
	
	public ConcreteNode(final int row, final int col) {
		
		this.row = row;
		this.col = col;
		
		
	}
	
	public void setGCost(final int g) {
		this.gCost = g;
	}
	
	public void setHCost(final int h) {
		this.hCost = h;
	}
	
	public void setFCost(final int f) {
		this.fCost = f;
	}
	
	public void setParent(final Node parent) {
		this.parent = parent;
	}
	
	public void setAsPath() {
		this.path = true;
	}
	
	public void setAsStart() {
		this.start = true;
	}
	
	public void setAsGoal() {
		this.goal = true;
	}
	
	public void setAsSolid() {
		this.solid = true;
	}
	
	public void setAsOpen() {
		this.open = true;
	}
	
	public void setAsChecked() {
		this.checked = true;
	}
	public boolean isStart() {
		return start;
	}

	public boolean isGoal() {
		return goal;
	}

	public boolean isSolid() {
		return solid;
	}
	
	public int getGCost() {
		return this.gCost;
	}
	
	public int getHCost() {
		return this.hCost;
	}
	
	public int getFCost() {
		return this.fCost;
	}
	
	public Node getParent() {
		return parent;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}


	public boolean isOpen() {
		return open;
	}

	public boolean isChecked() {
		return checked;
	}
	
	
	
}
