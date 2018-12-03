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
import javax.swing.JPanel;
import javax.swing.JTextField;

import projet_BD.Requete;

public class FenetreAjoutProduits extends JDialog {

	private boolean estAdmin;
	private String categorie;
	private String nombreProduits;
	private String idSalle;
	private JTextField categorieText, idSalleText;
	private int typeDuree;
	private Date date;
	private JComboBox<String> produitBox;
	ArrayList<JTextField> listePrix = new ArrayList<JTextField>();
	ArrayList<JComboBox> listeProduits = new ArrayList<JComboBox>();

	public FenetreAjoutProduits(JFrame parent, String title, boolean modal, String categorie, boolean estAdmin, String nombreProduits, String idSalle, int typeDuree){
		super(parent, title, modal);
		this.estAdmin = estAdmin;
		this.categorie = categorie;
		this.idSalle = idSalle;
		this.typeDuree = typeDuree;
		this.setSize(600, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.nombreProduits = nombreProduits;
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
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B> Ajoutez vos " + Integer.parseInt(nombreProduits) + " produits : </B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);

    	Box box = Box.createVerticalBox();
    	
	    Box box0 = Box.createVerticalBox();
	    box0.add(Box.createRigidArea(new Dimension(0,10)));
	    
	    box.add(box0);
	    
	    categorieText = new JTextField(categorie);
	    idSalleText = new JTextField(idSalle);
    	
		for (int i = 0; i < Integer.parseInt(nombreProduits); i++) {
			// Ajouts des produits
			JPanel panProduit = new JPanel();	
			panProduit.setBackground(Color.white);
			panProduit.setPreferredSize(new Dimension(200, 80));
			JLabel produit = new JLabel("Nom du produit : ");
			
			Requete requete = new Requete("select id_produit from Produit1 where nom_categorie='" + categorie + "'");
			ArrayList<String[]> selection = new ArrayList<String[]>();
			requete.getSelection(selection);
		
			produitBox = new JComboBox<String>();
			for (String[] elt : selection) {
				for (String j:elt) {
					produitBox.addItem(j);
				}
			}
			panProduit.add(produit);
			panProduit.add(produitBox);
			
			
			//prix de départ des produits
			JPanel panPrix = new JPanel();	
			panPrix.setBackground(Color.white);
			panPrix.setPreferredSize(new Dimension(200, 80));
			JLabel prix;
			JTextField prixText;
			prix = new JLabel("Prix de départ du produit : ");
			prixText = new JTextField();
			prixText.setPreferredSize(new Dimension(150, 25));
			panPrix.add(prix);
			panPrix.add(prixText);
	    	
	        Box b1 = Box.createHorizontalBox();
	        b1.add(panProduit);
	        b1.add(Box.createRigidArea(new Dimension(0,10)));
	        b1.add(panPrix);
	        
	        box.add(b1);
	        box.add(Box.createRigidArea(new Dimension(0,5)));
	        
	        listeProduits.add(produitBox);
	        listePrix.add(prixText);
	        
		}	
	    
	    Box box00 = Box.createVerticalBox();
	    box00.add(Box.createRigidArea(new Dimension(0,5)));
		box.add(box0);
		
    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");
    	
    	
    	okBouton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
    			
    			Iterator<JComboBox> itProd = listeProduits.iterator();
    			Iterator<JTextField> itPrix = listePrix.iterator();
    			String preStmt = "";
    			while (itProd.hasNext() ) {
    				preStmt += preStmt + "insert into Vente1(id_produit, id_salle, prix_depart, temps) values('"
    						+ Integer.parseInt((String) itProd.next().getSelectedItem()) + "', " + Integer.parseInt(idSalleText.getText())  +
    						"," + Integer.parseInt(itPrix.next().getText()) + "," + Integer.parseInt("2") + ");" ; //TODO récupérer le temps suivant la type de vente	
    			}
    			Requete requete = new Requete(preStmt);
    			requete.executeUpdateReq();
    			
    		}
    	});

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
