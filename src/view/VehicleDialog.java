package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/* JDialog called by VehicleViewImpl Frame */
public class VehicleDialog extends JDialog {

	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTextField idField;
	private JTextField descriptionField;
	private JTextField typeField;
	
	
	private boolean buttonOKPressed = false;
	
	
	public VehicleDialog(final JFrame f, final String title) {
		setup(f, title);
		setVisible(true);
	}
	
	public VehicleDialog(final JFrame f, final String title, final String id,
			final String description, final String type) {
		
		setup(f, title);
		
		this.idField.setText(id);
		this.idField.setEditable(false);
		this.descriptionField.setText(description);
		this.typeField.setText(type);

		
		setVisible(true);
		
			
		
		
	}
	
	public void setIdField(final String txt) {
		this.idField.setText(txt);
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getButtonOK() {
		return buttonOK;
	}

	public JButton getButtonCancel() {
		return buttonCancel;
	}

	public JTextField getIdField() {
		return idField;
	}

	public JTextField getDescriptionField() {
		return descriptionField;
	}

	public JTextField getTypeField() {
		return typeField;
	}


	public boolean isButtonOKPressed() {
		return buttonOKPressed;
	}
	

	private void setup(final JFrame f, final String title) {
		
		setTitle(title);
		
		setGUI();
		setListener();
		
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		
		setLocationRelativeTo(f);
	}
	
	
	private void setGUI() {
		
		
		
		contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());
		
		
		final JLabel idLabel = new JLabel("ID");
		idField = new JTextField(15);
		
		final JLabel descriptionLabel = new JLabel("Description");
		descriptionField = new JTextField(15);
		
		final JLabel typeLabel = new JLabel("Type[0=AIR;1=LAND]");
		typeField = new JTextField(15);
		
		
		
		
		buttonOK = new JButton();
		buttonOK.setText("OK");
		buttonCancel = new JButton();
		buttonCancel.setText("CANCEL");
		
		
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		
		
		
		
		
		
		
		
		// Layout
		GridBagConstraints gbc;
		
		gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(panel1, gbc);	
        
        panel1.add(buttonOK);
        panel1.add(buttonCancel);
        
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(panel2, gbc);
		
		
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(idLabel, gbc);
        
        idField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(idField, gbc);
		
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(descriptionLabel, gbc);
        
        descriptionField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(descriptionField, gbc);
		
		
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(typeLabel, gbc);
        
        typeField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(typeField, gbc);
		

		
	}
	
	private void setListener() {
		
		
		buttonOK.addActionListener((ActionEvent e) -> {
			buttonOKPressed = true;
			dispose();
		});
		
	
		buttonCancel.addActionListener((ActionEvent e) -> {
			dispose();  // release the resource
		});
		
		
	}
	
	
	
}
