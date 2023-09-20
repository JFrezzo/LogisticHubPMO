package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

/* Frame for create,modify and remove products in the hub */
public class ProductViewImpl extends JFrame implements ProductView {

	private JPanel mainPanel;
	private JButton buttonAdd;
	private JButton buttonEdit;	
	private JButton buttonRemove;
	private JButton buttonExport;
	private JButton buttonBack;
	private JTable tableProduct;
	private TableModelProduct tableModel;
	private ProductObserver obs;
	
	
	
	public ProductViewImpl() {
		super("CREATE PRODUCTS");

		
	}

	@Override
	public void setObserver(final ProductObserver obs) {
		this.obs = obs;		
	}

	@Override
	public void start() {
		setGUI();
		setListener();
		
		//FRAME SETUP
		this.setContentPane(this.mainPanel);
		this.setSize(800, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	private void setGUI() {

		
		buttonAdd = new JButton("ADD");
		buttonEdit = new JButton("EDIT");
		buttonRemove = new JButton("REMOVE");
		buttonExport = new JButton("SAVE");
		buttonBack = new JButton("BACK TO MENU");
		tableModel = new TableModelProduct(obs);
		tableProduct = new JTable(tableModel);
		
		
		mainPanel = new JPanel(new BorderLayout(0,0));
		
		final JPanel panel2 = new JPanel(new BorderLayout(0, 0));
		mainPanel.add(panel2, BorderLayout.NORTH);
		
		final JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel2.add(panel3, BorderLayout.WEST);
		
		final JLabel labelWareHouse = new JLabel();
		labelWareHouse.setText("PRODUCT WAREHOUSE");
		panel3.add(labelWareHouse);
		
		
		final JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel2.add(panel4, BorderLayout.CENTER);
		
		final JToolBar.Separator separator1 = new JToolBar.Separator();
		panel4.add(separator1);
		final JToolBar.Separator separator2 = new JToolBar.Separator();
		panel4.add(separator2);
		
		panel4.add(buttonAdd);
		panel4.add(buttonEdit);
		panel4.add(buttonRemove);
		
		final JToolBar.Separator separator3 = new JToolBar.Separator();
		panel4.add(separator3);
		
		panel4.add(buttonExport);
		
		final JToolBar.Separator separator4 = new JToolBar.Separator();
		panel4.add(separator4);
		panel4.add(buttonBack);
		
		
		final JScrollPane tabPane = new JScrollPane();
		tabPane.setEnabled(true);
		mainPanel.add(tabPane, BorderLayout.CENTER);
		tabPane.setBorder(BorderFactory.createTitledBorder(null, "Products", 
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		tabPane.setViewportView(tableProduct);
		
		final JPanel panel5 = new JPanel(new GridBagLayout());
		mainPanel.add(panel5, BorderLayout.EAST);
		
		
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JButton getButtonAdd() {
		return buttonAdd;
	}

	public JButton getButtonEdit() {
		return buttonEdit;
	}

	public JButton getButtonRemove() {
		return buttonRemove;
	}


	public JButton getButtonExport() {
		return buttonExport;
	}
	
	public JButton getButtonBack() {
		return buttonBack;
	}

	public JTable getTableProduct() {
		return tableProduct;
	}


	public ProductObserver getObs() {
		return obs;
	}
	
	

	private void setListener() {
		
		this.buttonAdd.addActionListener((ActionEvent e) -> {
				
				ProductDialog dialog = new ProductDialog(ProductViewImpl.this, "New Product");
				if(dialog.isButtonOKPressed()) {
					
					String id = dialog.getIdField().getText().trim();
					String description = dialog.getDescriptionField().getText().trim();
					String type = dialog.getTypeField().getText().trim();
					String weight = dialog.getWeightField().getText().trim();
					String price = dialog.getPriceField().getText().trim();
					
					
					
					obs.addProduct(id, description, type, weight, price);
					tableModel.fireTableDataChanged();

					
				}

			
		});
		
		this.buttonEdit.addActionListener((ActionEvent e) -> {
				int row = getTableProduct().getSelectedRow();
				if(row < 0) {
					showMessage("NO ROW SELECTED");
				}else {
					
					String id = tableModel.getValueAt(row, 0).toString();
					String description = tableModel.getValueAt(row, 1).toString();
					String type = ""+tableModel.getType(row);
					String weight = tableModel.getValueAt(row, 3).toString();
					String price = tableModel.getValueAt(row, 4).toString();
					
					ProductDialog dialog = new ProductDialog(ProductViewImpl.this, "Edit Product", id, description, type, weight, price);

					if(dialog.isButtonOKPressed()) {
						
						
						description = dialog.getDescriptionField().getText().trim();
						type = dialog.getTypeField().getText().trim();
						weight = dialog.getWeightField().getText().trim();
						price = dialog.getPriceField().getText().trim();
						
						
						
						obs.editProduct(id, description, type, weight, price);						
						tableModel.fireTableDataChanged();
							
						
						
					}
						
					
				
			}
			
		});
		
		
		this.buttonRemove.addActionListener((ActionEvent e) ->{
				int row = getTableProduct().getSelectedRow();

				if(row < 0) {
					showMessage("NO ROW SELECTED");
				}else {
					
					String id = tableModel.getValueAt(row, 0).toString();

					
					int val = JOptionPane.showConfirmDialog(ProductViewImpl.this, "Remove "+ id + " from tabel ? ", "Remove product", JOptionPane.YES_OPTION);
					
					if(val == JOptionPane.YES_OPTION) {
					
						obs.removeProduct(id);
						tableModel.fireTableDataChanged();

				
					}
				}
			
		});
		
		
		
		this.buttonExport.addActionListener((ActionEvent e) -> {

			try {
				obs.exportProduct();
				JOptionPane.showMessageDialog(ProductViewImpl.this, "Successfully saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(ProductViewImpl.this, "Impossible export data from file", "Error", JOptionPane.ERROR_MESSAGE);
			}

		});
		
		
		this.buttonBack.addActionListener((ActionEvent e) -> {
				obs.backToMenu();		
		});
		
		
	}

	@Override
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(ProductViewImpl.this,
				msg ,"Warning", JOptionPane.WARNING_MESSAGE );
	}
}
