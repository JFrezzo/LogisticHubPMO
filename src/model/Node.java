package model;

public interface Node {
	void setGCost(final int g);
	void setHCost(final int h);
	void setFCost(final int f);
	void setParent(final Node parent);
	void setAsPath();
	void setAsStart();
	void setAsGoal();
	void setAsSolid();
	void setAsOpen();
	void setAsChecked();
	boolean isStart();
	boolean isGoal();
	boolean isSolid();
	int getGCost();
	int getHCost();
	int getFCost();
	Node getParent();
	int getRow();
	int getCol();
	boolean isOpen();
	boolean isChecked();
}
