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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreUtilisateur extends JDialog {
	
	  private JButton bouton1 = new JButton("Se connecter");
	  private JButton bouton2 = new JButton("S'inscrire");
	  private JButton bouton3 = new JButton("Annuler");

	  public FenetreUtilisateur(JFrame parent, String title, boolean modal){
		  super(parent, title, modal);
		  this.setSize(300, 150);
		  this.setLocationRelativeTo(null);
		  this.setResizable(false);
		  this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		  this.initComponent();
	  }

	  public void afficher(){
		  this.setVisible(true);
	  }

	  private void initComponent(){
		 	  
		 // Choisir entre utilisateur et administrateur
		 Box b1 = Box.createHorizontalBox(); 
		 b1.add(bouton1); 
		 b1.add(Box.createRigidArea(new Dimension(30,0)));
		 b1.add(bouton2);
		 
		 Box b2 = Box.createHorizontalBox(); 
		 b2.add(bouton3); 
		 	    
		 Box box = Box.createVerticalBox();
		 box.add(Box.createRigidArea(new Dimension(0,20)));
		 box.add(b1);
		 box.add(Box.createRigidArea(new Dimension(0,30)));
		 box.add(b2);
		 	
		 this.getContentPane().add(box);    
		 	
		 //bouton se connecter
		 bouton1.addActionListener(new ActionListener(){
		 	public void actionPerformed(ActionEvent arg0) {
		    	FenetreDeConnexion fenetreConnexion = new FenetreDeConnexion(null, "Connexion", true, false);
		    	fenetreConnexion.afficher();
		 	}
		 });
		 	    
		 //bouton s'inscrire
		 bouton2.addActionListener(new ActionListener(){
		 	public void actionPerformed(ActionEvent arg0) {
		 		FenetreNouvelUtilisateur fenetreNouvU = new FenetreNouvelUtilisateur(null, "Inscrivez vous", true);
		 		fenetreNouvU.afficher();
		 	}
		 });
		 
		 //bouton annuler
	    bouton3.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
	    		setVisible(false);
	    	}
	    });

	    this.setVisible(true); 
	}

}
