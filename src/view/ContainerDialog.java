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


/* JDialog called by ContainerViewImpl Frame */
public class ContainerDialog extends JDialog {

	
	
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTextField fieldQta;
	private JLabel labelQta;
	
	private boolean buttonOKPressed = false;
	
	
	
	public ContainerDialog(final JFrame f) {
		setup(f);
		setTitle("ADD PRODUCT TO SEND");
		setVisible(true);
		
	}
	
	private void setup(final JFrame f) {
		
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
		
		labelQta = new JLabel("QTA");
		fieldQta = new JTextField(15);
		
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
        panel2.add(labelQta, gbc);
        
        fieldQta.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(fieldQta, gbc);
		
        

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

	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getButtonOK() {
		return buttonOK;
	}

	public JButton getButtonCancel() {
		return buttonCancel;
	}

	public JTextField getFieldQta() {
		return fieldQta;
	}

	public JLabel getLabelQta() {
		return labelQta;
	}

	public boolean isButtonOKPressed() {
		return buttonOKPressed;
	}
	
	
	
	
}
