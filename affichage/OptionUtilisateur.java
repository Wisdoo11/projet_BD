package affichage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OptionUtilisateur extends JDialog {
	
	private String email;
	private JButton bouton1 = new JButton("Je veux vendre un produit");
	private JButton bouton2 = new JButton("Je veux participer à une enchère");
	private JButton bouton3 = new JButton("Me déconnecter");
	
	public OptionUtilisateur(String email) {
		
		this.setTitle("Bienvue " + email + " !");
		this.email = email;
	    this.setSize(500, 200);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.getContentPane().setLayout(new FlowLayout());
	    
	    // texte de bienvenue
	 	JPanel panel = new JPanel();
	 	panel.setLayout(new FlowLayout());
	 	    
	 	// Texte de Bienvenue
	 	String labelText = "<html><P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><BR>Que souhaitez-vous faire ?</P></HTML>";  
	 	JLabel label = new JLabel(labelText);   
	 	label.setLayout(new BoxLayout(label, BoxLayout.LINE_AXIS));   
	 	panel.add(label); 
	 	setContentPane(panel);
	 	  
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
	 	
	 	//bouton utilisateur
	 	bouton1.addActionListener(new ActionListener(){
	 		public void actionPerformed(ActionEvent arg0) {
	 			FenetreVendreProduit fenetreVendre = new FenetreVendreProduit(null,"Vous souhaitez vendre un produit", true, email);
	 			fenetreVendre.afficher();
	 			// TODO a completer
	 		}
	 	});
	 	    
	 	//bouton admin
	 	bouton2.addActionListener(new ActionListener(){
	 		public void actionPerformed(ActionEvent arg0) {		   
	 			FenetreRealiserEnchere fenetreEnchere = new FenetreRealiserEnchere(null,"Vous souhaitez acheter un produit", true, email);
	 			fenetreEnchere.afficher();
	 			// TODO a completer    
	 		}
	 	}); 
	 	
	 	//bouton déconnexion
		bouton3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
	 	
	 	this.setVisible(true);
	}
}
