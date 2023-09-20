package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
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

/* Frame for add,modify and remove vehicles in the garage */
public class VehicleViewImpl extends JFrame implements VehicleView {

	private JPanel mainPanel;
	private JButton buttonAdd;
	private JButton buttonEdit;	
	private JButton buttonRemove;
	private JButton buttonExport;
	private JButton buttonBack;
	private JTable tableVehicle;
	private TableModelVehicle tableModel;
	
	private VehicleObserver obs;
	
	
	
	public VehicleViewImpl() {
		super("CREATE VEHICLE");
		
		
	}

	@Override
	public void setObserver(final VehicleObserver obs) {
		this.obs = obs;
	}

	@Override
	public void start() {
		
		setGUI(); // istruzioni per la creazione dell'interfaccia grafica di questo JFrame
		setListener(); // metodi ActionListener utilizzati nella finestra
		
		
		
		
		//FRAME SETUP
		this.setContentPane(this.mainPanel);
		this.setSize(800, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}
	
	@Override
	public void showMessage(String msg) { 
		JOptionPane.showMessageDialog(VehicleViewImpl.this,
				msg ,"Warning", JOptionPane.WARNING_MESSAGE );
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

	public JTable getTableVehicle() {
		return tableVehicle;
	}

	public TableModelVehicle getTableModel() {
		return tableModel;
	}



	public VehicleObserver getObs() {
		return obs;
	}
	
	private void setGUI() {
		
		buttonAdd = new JButton("ADD");
		buttonEdit = new JButton("EDIT");
		buttonRemove = new JButton("REMOVE");
		
		buttonExport = new JButton("SAVE");
		buttonBack = new JButton("BACK TO MENU");
		tableModel = new TableModelVehicle(obs);
		tableVehicle = new JTable(tableModel);
		
		mainPanel = new JPanel(new BorderLayout(0,0));
		
		
		final JPanel panel2 = new JPanel(new BorderLayout(0, 0));
		mainPanel.add(panel2, BorderLayout.NORTH);
		
		final JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel2.add(panel3, BorderLayout.WEST);
		
		final JLabel labelWareHouse = new JLabel();
		labelWareHouse.setText("GARAGE");
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
		tabPane.setBorder(BorderFactory.createTitledBorder(null, "Vehicles", 
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		tabPane.setViewportView(tableVehicle);
		
		final JPanel panel5 = new JPanel(new GridBagLayout());
		mainPanel.add(panel5, BorderLayout.EAST);
		
		
	}
	
	
	private void setListener() {
		
		
		this.buttonAdd.addActionListener((ActionEvent e) -> {
			
			VehicleDialog dialog = new VehicleDialog(VehicleViewImpl.this, "New Vehicle");
			if(dialog.isButtonOKPressed()) {
				
				String id = dialog.getIdField().getText().trim();
				String description = dialog.getDescriptionField().getText().trim();
				String type = dialog.getTypeField().getText().trim();
				
				
				
				
				obs.addVehicle(id, description, type);
				tableModel.fireTableDataChanged();

				
			}

		
	});
	
	this.buttonEdit.addActionListener((ActionEvent e) -> {
			int row = getTableVehicle().getSelectedRow();
			if(row < 0) {
				showMessage("NO ROW SELECTED");
			}else {
				
				String id = tableModel.getValueAt(row, 0).toString();
				String description = tableModel.getValueAt(row, 1).toString();
				String type = ""+tableModel.getType(row);
			
				
				VehicleDialog dialog = new VehicleDialog(VehicleViewImpl.this, "Edit Vehicle", id, description, type);

				if(dialog.isButtonOKPressed()) {
					
					
					description = dialog.getDescriptionField().getText().trim();
					type = dialog.getTypeField().getText().trim();
					
					
					
					
					obs.editVehicle(id, description, type);						
					tableModel.fireTableDataChanged();
						
					
					
				}
					
				
			
		}
		
	});
	
	
	this.buttonRemove.addActionListener((ActionEvent e) ->{
			int row = getTableVehicle().getSelectedRow();

			if(row < 0) {
				showMessage("NO ROW SELECTED");
			}else {
				
				String id = tableModel.getValueAt(row, 0).toString();

				
				int val = JOptionPane.showConfirmDialog(VehicleViewImpl.this, "Remove "+ id + " from tabel ? ", "Remove vehicle", JOptionPane.YES_OPTION);
				
				if(val == JOptionPane.YES_OPTION) {
				
					obs.removeVehicle(id);
					tableModel.fireTableDataChanged();

			
				}
			}
		
	});
	
	

	
	
	this.buttonExport.addActionListener((ActionEvent e) -> {

		try {
			obs.exportVehicle();
			JOptionPane.showMessageDialog(VehicleViewImpl.this, "Successfully saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(VehicleViewImpl.this, "Impossible export data from file", "Error", JOptionPane.ERROR_MESSAGE);
		}
			
	});
	
	
	this.buttonBack.addActionListener((ActionEvent e) -> {
			obs.backToMenu();		
	});
		
		
		
		
		
		
	}


}
