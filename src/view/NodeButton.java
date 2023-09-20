package view;

import javax.swing.JButton;

public class NodeButton extends JButton{
	
	private int row;
	private int col;
	
	public NodeButton(final int row, final int col) {
		
		this.row = row;
		this.col = col;
		
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}

}
