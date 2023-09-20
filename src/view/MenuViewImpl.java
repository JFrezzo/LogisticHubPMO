package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuViewImpl extends JFrame implements MenuView{

	private JButton productsButton; //pulsante che apre il frame per inserire/modificare/rimuovere i prodotti dall'hub
	private JButton vehiclesButton; //pulsante che apre il frame per inserire/modificare/rimuovere i mezzi dal garage dell'hub
	private JButton sendButton; //pulsantre che apre il frame per creare i container di prodotti da spedire con un mezzo
	private JLabel labelMenu; // label contenente l'introduzione della finestra
	private JPanel mainPanel; //panel principale
	private MenuObserver obs;
	
	public MenuViewImpl() {
		
		setGUI();
		setListener();
		
		
		// FRAME SETUP
		this.setContentPane(this.mainPanel);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	private void setGUI() {
		
		this.productsButton = new JButton("ADD NEW PRODUCTS");
		this.vehiclesButton = new JButton("ADD NEW VEHICLES");
		this.sendButton = new JButton("SEND PRODUCTS");

				
		
		this.labelMenu = new JLabel();
		this.labelMenu.setText("LOGISTIC HUB MENU");
		this.mainPanel = new JPanel(new GridBagLayout());
		

		GridBagConstraints gbc;


		final JPanel panel2 = new JPanel();
		
	    panel2.setLayout(new GridBagLayout());
	        
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;

	    this.mainPanel.add(panel2, gbc);
			
			
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.PAGE_START;


	    //gbc.anchor = GridBagConstraints.WEST;
	   	panel2.add(labelMenu, gbc);
	        
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.ipady = 20;
        gbc.insets = new Insets(50,0,0,0);

        panel2.add(productsButton, gbc);
			
	        
	    gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10,0,0,0);

	    gbc.ipady = 20;
        gbc.gridx = 0;
	    gbc.gridy = 3;

        
	    panel2.add(vehiclesButton, gbc);
	        
	    gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10,0,0,10);
	    gbc.ipady = 20;
	    gbc.gridx = 0;
	    gbc.gridy = 4;


	    panel2.add(sendButton, gbc);
		
		
		
		
	}
	
	
	private void setListener() {
		
		this.productsButton.addActionListener((ActionEvent e) -> {
				
				obs.openFrame(MenuButtonType.PRODUCT);
				obs.quit();
		});
		
		this.vehiclesButton.addActionListener((ActionEvent e) -> {
	
				obs.openFrame(MenuButtonType.VEHICLE);
				obs.quit();

		});
		
		this.sendButton.addActionListener((ActionEvent e) -> {
				
				obs.openFrame(MenuButtonType.SEND);
				obs.quit();

		});
	}

	@Override
	public void setObserver(final MenuObserver obs) {
		this.obs = obs;
	}

	@Override
	public void start() {
		this.setVisible(true);
	}

	@Override
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(MenuViewImpl.this,
				msg ,"Warning", JOptionPane.WARNING_MESSAGE );
	}
	
	
	

	
	
}
