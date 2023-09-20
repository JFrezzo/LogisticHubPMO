package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConcreteStreetMap implements StreetMap {
	
	private Node[][] nodes;
	private Node startNode, goalNode, currentNode;
	private boolean goalReached = false;
	private int step = 0;
	private int maxRow;
	private int maxCol;
	
	
	
	
	
	ArrayList<Node> openList = new ArrayList<>();
	ArrayList<Node> checkedList = new ArrayList<>();
	
	private List<Node> path = new ArrayList<>();
	
	
	
	public ConcreteStreetMap(final int maxRow, final int maxCol) {
		
		
		
		nodes = new ConcreteNode[maxRow][maxCol];
		
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		
		
		placeNode();
		setStartNode(0,0);
		setGoalNode(maxRow-1, maxCol-1);
		setUpNode();

		
	}
	
	public int getmaxRow() {
		return this.maxRow;
	}
	
	public int getmaxCol() {
		return this.maxCol;
	}
	
	private void setUpNode() {
		
		int row = 0;
		int col = 0;
		
		
		while(col < maxCol && row < maxRow) {
			
			getCost(nodes[row][col]);

			row++;
			
			if(row == maxRow) {
				row = 0;
				col++;
			}
			
		}
		
	}
	
	private void placeNode() {
		
		int row = 0;
		int col = 0;
		
		
		while(col < maxCol && row < maxRow) {
			
			this.nodes[row][col] = new ConcreteNode(row, col);

			row++;
			
			if(row == maxRow) {
				row = 0;
				col++;
			}
			
		}
		
	}
	
	private void setStartNode(final int row, final int col) {
		this.nodes[row][col].setAsStart();
		this.startNode = nodes[row][col];
		this.currentNode = startNode;
	}
	
	private void setGoalNode(final int row, final int col) {
		nodes[row][col].setAsGoal();
		goalNode = nodes[row][col];
	}
	
	
	
	
	private void getCost(final Node node) {
		
		// GET G COST (The distance from the start node)
		int xDistance = Math.abs(node.getCol() - startNode.getCol());
		int yDistance = Math.abs(node.getRow() - startNode.getRow());
		node.setGCost(xDistance + yDistance); 
		
		// GET H COST (The distance from the goal node)
		xDistance = Math.abs(node.getCol() - goalNode.getCol());
		yDistance = Math.abs(node.getRow() - goalNode.getRow());
		node.setHCost(xDistance + yDistance);
		
		// GET F COST (The total cost)
		node.setFCost(node.getGCost() + node.getHCost());
		
	}
	
	private void openNode(final Node node) {
		if(node.isOpen() == false && node.isChecked() == false && node.isSolid() == false) {
			
			// if the node is not opened yet, add it to the open list
			node.setAsOpen();
			node.setParent(currentNode);
			openList.add(node);
		}
	}
	
	private void trackThePath() {
		
		// Backtrack and draw the best path
		Node current = goalNode;
		
		while(current != startNode) {
			current = current.getParent();
			if(current != startNode) {
				current.setAsPath();
				path.add(current);
			}
		}
	}
	
	public List<Node> searchSolution() throws Exception{
			
		while(goalReached == false && step < 300) {
			
			
			int col = currentNode.getCol();
			int row = currentNode.getRow();
			
			currentNode.setAsChecked();
			checkedList.add(currentNode);
			openList.remove(currentNode);
			
			// OPEN THE UP NODE
			if(row -1 >= 0) {
				openNode(nodes[row-1][col]);
			}
			
			// OPEN THE LEFT NODE
			if(col -1 >= 0 ) {
				openNode(nodes[row][col-1]);
			}
			
			// OPEN THE DOWN NODE
			if(row+1 < maxRow) {
				openNode(nodes[row+1][col]);
			}
			
			// OPEN THE RIGHT NODE
			if(col+1 < maxCol) {
				openNode(nodes[row][col+1]);
			}
			
			
			// FIND THE BEST NODE
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			
			for(int i = 0; i< openList.size(); i++) {
				
				//Check if this node's F cost is better
				if(openList.get(i).getFCost() < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).getFCost();
				}
				// if F cost is equal, check the G cost
				else if(openList.get(i).getFCost() == bestNodefCost) {
					if(openList.get(i).getGCost() < openList.get(bestNodeIndex).getGCost()) {
						bestNodeIndex = i;
					}
				}
			}
			
			// After the loop, we get the best node which is our next step
			currentNode = openList.get(bestNodeIndex);
			
			if(currentNode == goalNode) {
				goalReached = true;
				trackThePath();
			}
			
			step++;
		}
		
		return Collections.unmodifiableList(new ArrayList<>(this.path));
	}
	
	public void setSolidNode(final int row, final int col) {
		if(row > -1 && row < maxRow && col > -1 && col < maxCol) {
			nodes[row][col].setAsSolid();

		} else throw new IllegalArgumentException("Error position not valid: row:"+row+" col:"+ col);
	}

	@Override
	public Node getStartNode() {
		return this.startNode;
	}

	@Override
	public Node getGoalNode() {
		return this.goalNode;
	}
	
}
