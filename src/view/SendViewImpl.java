package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SendViewImpl extends JFrame implements SendView, ActionListener {

	private SendObserver obs;
	private JPanel streetPanel;
	private NodeButton[][] nodes;
	private final int nodeSize = 80;

	
	public SendViewImpl() {
		super("STREET MAP");
	}

	@Override
	public void setObserver(final SendObserver obs) {
		this.obs = obs;
	}

	@Override
	public void start() {
		
		
		setUp();
		
		this.add(streetPanel);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(SendViewImpl.this,
				msg ,"", JOptionPane.INFORMATION_MESSAGE );
	}
	
	private void setUp() {
		
		nodes = new NodeButton[obs.getMaxRow()][obs.getMaxCol()];
		streetPanel = new JPanel();
		
		final int maxRow = obs.getMaxRow();
		final int maxCol = obs.getMaxCol();
		
		streetPanel.setPreferredSize(new Dimension(nodeSize*maxRow, nodeSize*maxCol));
		streetPanel.setBackground(Color.black);
		streetPanel.setLayout(new GridLayout(maxRow, maxCol));
		streetPanel.setFocusable(true);

		
		//PLACE NODE
		int col = 0;
		int row = 0;
		
		while(col < maxCol && row < maxRow) {
			nodes[row][col] = new NodeButton(row, col);
			nodes[row][col].setBackground(Color.white);
			nodes[row][col].setForeground(Color.black);
			nodes[row][col].addActionListener(this);
			streetPanel.add(nodes[row][col]);
			
			col++;
			
			if(col == maxCol) {
				col = 0;
				row++;
			}
		}
		
		String[] start = obs.getStartNode().split("-");
		JButton startButton = nodes[Integer.parseInt(start[0])][Integer.parseInt(start[1])];
		String[] goal = obs.getGoalNode().split("-");
		JButton goalButton = nodes[Integer.parseInt(goal[0])][Integer.parseInt(goal[1])];

		startButton.setBackground(Color.green);
		startButton.setText("START");
		goalButton.setBackground(Color.blue);
		goalButton.setText("GOAL");
		goalButton.addActionListener((ActionEvent e) -> {
			
			List<String> path = obs.searchSolution();
			
			for(String s : path) {
				String[] rowColpath = s.split("-");
				nodes[Integer.parseInt(rowColpath[0])][Integer.parseInt(rowColpath[1])].setBackground(Color.red);
			}
			
			this.showMessage("The vehicle " +obs.getVId()+" has arrived at its destination");	
		});
	
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
		NodeButton block = (NodeButton) e.getSource();
		
		block.setBackground(Color.black);
		
		obs.setSolidNode(block.getRow(), block.getCol());
			
	}
	
}
