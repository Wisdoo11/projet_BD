package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreDeConnexion extends JDialog {
	
	private boolean estAdmin;
	private String email;
	private JLabel emailLabel;
	private JTextField emailText;
	
	public FenetreDeConnexion(JFrame parent, String title, boolean modal, boolean estAdmin){
		super(parent, title, modal);
		this.estAdmin = estAdmin;
		this.setSize(350, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	public void afficher(){
		this.setVisible(true);
	}
	
	private void initComponent(){
		
		//Titre
		JPanel panel = new JPanel();
		//panel.setLayout(new FlowLayout());
	    String labelText = "<html><P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><BR>\r\n" + 
	    "</P>\r\n" + 
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 16pt\"><B>Connexion</B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);

		//email de l'utilisateur
		JPanel panEmail = new JPanel();
		panEmail.setBackground(Color.white);
		panEmail.setPreferredSize(new Dimension(250, 80));
		emailText = new JTextField();
		emailText.setPreferredSize(new Dimension(200, 25));
		emailLabel = new JLabel("Votre Email : ");
		panEmail.add(emailLabel);
		panEmail.add(emailText);
		
    	// ajout des boutons ok et annuler
    	JPanel control = new JPanel();
    	
    	JButton okBouton = new JButton("OK"); // bouton ok
    	okBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			email = emailText.getText();
    			ConfirmationCo co = new ConfirmationCo(email, estAdmin);
    	        JOptionPane.showMessageDialog(null, co.toString(), " ", JOptionPane.INFORMATION_MESSAGE);
    	        boolean continuer = co.continuer(email);
    	        setVisible(!continuer);
    		}
    	});

    	JButton cancelBouton = new JButton("Annuler"); // bouton annuler
    	cancelBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			setVisible(false);
    		}
    	});

    	control.add(okBouton);
    	control.add(cancelBouton);
    	
	    // Choisir entre utilisateur et administrateur
	    Box box0 = Box.createVerticalBox();
	    box0.add(Box.createRigidArea(new Dimension(0,10)));
	    
	    Box box1 = Box.createVerticalBox();
	    box1.add(panEmail);

	    Box box00 = Box.createVerticalBox();
	    box00.add(Box.createRigidArea(new Dimension(0,10)));
	    
	    Box box = Box.createVerticalBox();
	    box.add(box0);
	    box.add(box1);
	    box.add(box00);

	    this.getContentPane().add(box);
	    this.getContentPane().add(control, BorderLayout.SOUTH);
		
	}
	

}
