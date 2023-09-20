package controller;

import java.util.ArrayList;
import java.util.List;

import model.ConcreteStreetMap;
import model.Node;
import model.Vehicle;
import model.VehicleType;
import view.SendObserver;
import view.SendViewImpl;

public class SendController implements SendObserver {

	private final ConcreteStreetMap map;
	private final Vehicle v;
	private final SendViewImpl view;
	
	public SendController(final Vehicle v) {
		
		this.map = new ConcreteStreetMap(10, 10);
		this.v = v;
		this.view = new SendViewImpl();
		this.view.setObserver(this);
		this.view.start();
	}
	
	@Override
	public void quit() {
		view.dispose();
	}
	
	@Override
	public void setSolidNode(final int row, final int col) {
		// The air vehicle don't have obstacle
		if(v.getType().equals(VehicleType.LAND)){
			try {
				map.setSolidNode(row, col);
			} catch(IllegalArgumentException e) {
				view.showMessage(e.getMessage());
			}
		}
	}
	
	@Override
	public List<String> searchSolution() {
		List<String> path = new ArrayList<>();
		try {
			for(Node n : this.map.searchSolution()) {	
				
				path.add(n.getRow()+"-"+n.getCol());
			}
		} catch (Exception e) {
			view.showMessage("Solution not found");
		}	
		return path;
	}
	
	@Override
	public int getMaxRow() {
		return this.map.getmaxRow();
	}
	
	@Override
	public int getMaxCol() {
		return this.map.getmaxCol();
	}
	
	@Override
	public String getStartNode() {
		return this.map.getStartNode().getRow() + "-" + this.map.getStartNode().getCol();
	}
	
	@Override
	public String getGoalNode() {
		return this.map.getGoalNode().getRow() + "-" + this.map.getGoalNode().getCol();
	}

	@Override
	public String getVId() {
		return v.getId();
	}
	

}
