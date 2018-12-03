package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projet_BD.Requete;

public class FenetreChoixProduitEnchere extends JDialog {
	
	private String categorie, email;
	private JComboBox<String> produitBox;

	public FenetreChoixProduitEnchere(JFrame parent, String title, boolean modal, String categorie, String email){
		super(parent, title, modal);
		this.categorie = categorie;
		this.email = email;
		this.setSize(600, 300);
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
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B> Sélectionnez le produit à acheter : </B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);
    	
		JPanel panProduit = new JPanel();	
		panProduit.setBackground(Color.white);
		panProduit.setPreferredSize(new Dimension(200, 80));
		JLabel produit = new JLabel("Nom du produit : ");
			
		Requete requete = new Requete("Select Produit1.id_produit, Produit1.nom\r\n" + 
				"From Salle1, Vente1, Produit1\r\n" + 
				"Where Salle1.nom_categorie = '" + categorie +"'\r\n" + 
				"AND Produit1.nom_categorie = '" + categorie + "'\r\n" + 
				"AND Vente1.id_salle = Salle1.id_salle\r\n" +
				"AND Vente1.id_produit = Produit1.id_produit");
		ArrayList<String[]> selection = new ArrayList<String[]>();
		requete.getSelection(selection);
		
		produitBox = new JComboBox<String>();
		for (String[] elt : selection) {
			produitBox.addItem(elt[0] + ", " + elt[1]);
		}
		panProduit.add(produit);
		panProduit.add(produitBox);
	    
		//organisation visuelle
	    Box box0 = Box.createVerticalBox();
	    box0.add(Box.createRigidArea(new Dimension(0,10)));
	    
	    Box b1 = Box.createHorizontalBox();
	    b1.add(panProduit);
	    b1.add(Box.createRigidArea(new Dimension(0,10)));
	    
	    Box box00 = Box.createVerticalBox();
	    box00.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box = Box.createVerticalBox();
	    box.add(box0);
	    box.add(b1);
	    box.add(Box.createRigidArea(new Dimension(0,5)));
		box.add(box0);
		
		//bouton ok
    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");
    	
    	okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//TODO à compléter
    		}
    	});
    	
    	//bouton annuler
    	JButton cancelBouton = new JButton("Annuler");
    	cancelBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			setVisible(false);
    		}
    	});

    	control.add(okBouton);
    	control.add(cancelBouton);

    	this.getContentPane().add(box);
    	this.getContentPane().add(control, BorderLayout.SOUTH);
	}
}
