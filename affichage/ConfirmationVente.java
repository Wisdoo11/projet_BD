package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfirmationVente extends JDialog {
	
	private String email, nom, categorie, prix, stock;
	private JButton bouton1 = new JButton("Mettre ce produit à vendre");
	private JButton bouton2 = new JButton("Annuler");
	
	public ConfirmationVente(JFrame parent, String title, boolean modal, String email, String categorie, String nom, String prix, String stock) {
		super(parent, title, modal);
		this.email = email;
		this.nom = nom;
		this.categorie = categorie;
		this.prix = prix;
		this.stock = stock;
		this.setSize(400, 150);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}
	
	public void afficher() {
		this.setVisible(true);
	}
	
	public void initComponent() {
		
		//Titre
		JPanel panel = new JPanel();
		//panel.setLayout(new FlowLayout());
	    String labelText = "<html><P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><BR>\r\n" + 
	    "</P>\r\n" + 
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=4 STYLE=\"font-size: 12pt\"><B>Confirmer la mise en vente de votre produit : </B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);
    	
    	bouton1.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			// TODO a completer
    			// l'ajouter dans la base de données
    		}
    	});

    	bouton2.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			setVisible(false);
    		}
    	});
    	
    	JPanel control = new JPanel();
    	control.add(bouton1);
    	control.add(bouton2);
    	
	    this.getContentPane().add(control, BorderLayout.SOUTH);
	}
	
	
	
	

}
