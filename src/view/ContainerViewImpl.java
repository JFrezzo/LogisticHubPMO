package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ContainerViewImpl extends JFrame implements ContainerView {

	private JPanel choicePanel;
	private JPanel textAreaPanel;
	
	private JButton buttonAdd;
	private JButton buttonSend;
	private JLabel labelVehicle;
	private JLabel labelProduct;

	private JComboBox comboVehicle;
	private JComboBox comboProduct;
	private JTextArea textAreaContainer;
	private ContainerObserver obs;
	
	
	
	
	public ContainerViewImpl() {
		super("SEND PRODUCTS");

	}


	@Override
	public void setObserver(final ContainerObserver obs) {
		this.obs = obs;
	}

	@Override
	public void start() {
		setLayout(new BorderLayout());
		setChoicePanel();
		setAreaPanel();
		
		add(choicePanel, BorderLayout.LINE_START);
		add(textAreaPanel, BorderLayout.CENTER);
		add(buttonSend, BorderLayout.SOUTH);
		
		setSize(600, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}


	@Override
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(ContainerViewImpl.this,
				msg ,"Warning", JOptionPane.WARNING_MESSAGE );
	}
	
	private void setAreaPanel() {
		
		this.textAreaPanel = new JPanel(new BorderLayout());
		
		textAreaContainer = new JTextArea();
		
		this.textAreaPanel.add(new JScrollPane(textAreaContainer), BorderLayout.CENTER);
	}
	
	private void setChoicePanel() {
		
		this.choicePanel = new JPanel(new GridBagLayout());
		this.choicePanel.setPreferredSize(new Dimension(250,250));
		

		
		//Choice Vehicle
		labelVehicle = new JLabel("Select the vehicle: ");
		String[] optionVehicle = obs.getVehicles();


		comboVehicle = new JComboBox(optionVehicle);
		comboVehicle.setEditable(false);
		
		
		//Choice Product
		labelProduct = new JLabel("Select products to send: ");
		String[] optionProduct = obs.getProducts();
		
		
		comboProduct = new JComboBox(optionProduct);
		comboProduct.setEditable(false);
		comboProduct.setEnabled(false);
		
		
		
		//Button Add
		buttonAdd = new JButton("ADD");
		buttonAdd.setEnabled(false);
		

		
		
		//LISTENER
		
		comboVehicle.addActionListener((ActionEvent e) -> {
			try {
				String[] item = comboVehicle.getSelectedItem().toString().split("-");
				if(item != null) {
					this.obs.setVehicle(item[0]);

					comboVehicle.setEnabled(false);
					comboProduct.setEnabled(true);
					buttonAdd.setEnabled(true);
			}
			}catch (Exception err) {
				showMessage("NO VEHICLE SELECTED");
			}
			
		});
		
		buttonAdd.addActionListener((ActionEvent e) -> {
			try {
				String[] item = comboProduct.getSelectedItem().toString().split("-");
				
				if(item != null) {
					ContainerDialog dialog = new ContainerDialog(ContainerViewImpl.this);
					if(dialog.isButtonOKPressed()) {
						 
						obs.add(item[0], dialog.getFieldQta().getText());

						
						textAreaContainer.setText(obs.getContainer().toString());
								
	
				}
			}
			}catch (Exception err) {
				showMessage("NO PRODUCT SELECTED");
			}
			
			
		});
		
		
		buttonSend = new JButton("SEND");
		
		buttonSend.addActionListener((ActionEvent e) -> {
			try {
				obs.send();
			} 
			catch (IllegalArgumentException err) {
				showMessage(err.getMessage());
			}
			
			
		});
		
		
		
		//LAYOUT
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//LABEL VEHICLE
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.insets = new Insets(0,0,0,5);

		
		this.choicePanel.add(labelVehicle, gbc);
		
		//COMBO VEHICLE
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		
		gbc.insets = new Insets(0,0,0,0);

		
		this.choicePanel.add(comboVehicle, gbc);
		
		// LABEL PRODUCT
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.insets = new Insets(0,0,0,5);

		
		this.choicePanel.add(labelProduct, gbc);
		
		// COMBO PRODUCT
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(0,0,0,0);

		
		this.choicePanel.add(comboProduct, gbc);
		
		//BUTTON ADD
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		
		gbc.anchor = GridBagConstraints.PAGE_START;
		
	
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		this.choicePanel.add(buttonAdd, gbc);

		
		
	}




}
