package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projet_BD.Requete;

public class FenetreVendreProduit extends JDialog{
	
	private String email;
	private JLabel emailLabel, nomLabel, prixLabel, CategorieLabel, prix2Label;
	private JComboBox<String> categorieBox, produit;
	private JTextField prixText, stockText, emailText;
	private String catSelectionne;
	private InfoProduitAVendre info;

	public FenetreVendreProduit(JFrame parent, String title, boolean modal, String email, String catSelectionne){
		super(parent, title, modal);
		this.email = email;
		this.catSelectionne = catSelectionne;
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	public void afficher(){
		this.setVisible(true);
	}

	private void initComponent(){

		//email
		JPanel panEmail = new JPanel();
		panEmail.setBackground(Color.white);
    	panEmail.setPreferredSize(new Dimension(250, 80));
    	panEmail.setBorder(BorderFactory.createTitledBorder("Identité du vendeur"));
    	emailLabel = new JLabel("Votre identité : " + email);
    	emailText = new JTextField(email);
    	panEmail.add(emailLabel);

    	//La catégorie de la salle
    	JPanel panCategorie = new JPanel();
    	panCategorie.setBackground(Color.white);
    	panCategorie.setPreferredSize(new Dimension(250, 80));
    	panCategorie.setBorder(BorderFactory.createTitledBorder("Catégorie d'objets"));

    	Requete requete = new Requete("select nom from Categorie1");
		ArrayList<String[]> selection = new ArrayList<String[]>();
		requete.getSelection(selection);

    	categorieBox = new JComboBox<String>();
    	int item = 1;
    	int pos = 2;
    	System.out.println(catSelectionne);
    	for (String[] elt : selection) {
    		for (String i:elt) {
    			if (i.equals(catSelectionne)) {
    				pos = item;
    			}
    			categorieBox.addItem(i);
    		}
    		item++;
    	}
    	CategorieLabel = new JLabel("Catégorie : " + catSelectionne);
    	panCategorie.add(CategorieLabel);
    	categorieBox.setSelectedIndex(pos-1);

    	//Le nom du produit
    	JPanel panProduit = new JPanel();
    	panProduit.setBackground(Color.white);
    	panProduit.setPreferredSize(new Dimension(250, 80));
    	panProduit.setBorder(BorderFactory.createTitledBorder("Le(s) produit(s)"));
    	produit = new JComboBox<String>();
    	
    	String catSelectionne = (String) categorieBox.getSelectedItem();
    	Requete requete2 = new Requete("select Produit1.nom from Produit1 where Produit1.nom_categorie = " + catSelectionne);
    	ArrayList<String[]> selectionCategorie = new ArrayList<String[]>();
    	requete2.getSelection(selectionCategorie);
    	
    	//
    	ListeProduit listeP = new ListeProduit(catSelectionne);
    	ArrayList<String> lp = listeP.afficheListe();

    	for (String elt : lp) {
    		produit.addItem(elt);
    	}
    	nomLabel = new JLabel("Nom du produit");
    	panProduit.add(nomLabel);
    	panProduit.add(produit);

    	//Le prix de revient
    	JPanel panPrix = new JPanel();
    	panPrix.setBackground(Color.white);
    	panPrix.setPreferredSize(new Dimension(250, 80));
    	panPrix.setBorder(BorderFactory.createTitledBorder("Prix de revient :"));
    	prixLabel = new JLabel("Prix : ");
    	prix2Label = new JLabel(" euro");
    	prixText = new JTextField();
    	prixText.setPreferredSize(new Dimension(90, 25));
    	panPrix.add(prixLabel);
    	panPrix.add(prixText);
    	panPrix.add(prix2Label);
    	
    	//le stock
    	JPanel panStock = new JPanel();
    	panStock.setBackground(Color.white);
    	panStock.setPreferredSize(new Dimension(250, 80));
    	panStock.setBorder(BorderFactory.createTitledBorder("Stock :"));
    	JLabel stockLabel = new JLabel("Quantité de produit en stock : ");
    	stockText = new JTextField();
    	stockText.setPreferredSize(new Dimension(90, 25));
    	panStock.add(stockLabel);
    	panStock.add(stockText);

    	JPanel content = new JPanel();
    	content.setBackground(Color.white);
    	content.add(panEmail);
    	content.add(panCategorie);
    	content.add(panProduit);
    	content.add(panPrix);
    	content.add(panStock);

    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("Mettre ce produit en vente");

    	okBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			String preStmt = "insert into Produit1(nom_categorie, email, nom, prix_revient, stock)"
    					+ " values ('" + (String) categorieBox.getSelectedItem() 
    			+ "', '" + emailText.getText() + "', '" + (String) produit.getSelectedItem() + "', '"
    					+ Float.parseFloat(prixText.getText()) + "', '" + Float.parseFloat(stockText.getText())+ "')";
    			Requete requete = new Requete(preStmt);
    			requete.execute();
			    String id = requete.recupIdProduit((String) categorieBox.getSelectedItem(), emailText.getText(), 
			    		(String) produit.getSelectedItem());
			    
    			info = new InfoProduitAVendre(emailText.getText(), (String) categorieBox.getSelectedItem(), (String) produit.getSelectedItem(), 
    					prixText.getText(), stockText.getText(), id);
    	        JOptionPane.showMessageDialog(null, info.toString(), "Récapitulatif sur le produit à vendre", JOptionPane.INFORMATION_MESSAGE);
    	        
    	        setVisible(false);
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

    	this.getContentPane().add(content, BorderLayout.CENTER);
    	this.getContentPane().add(control, BorderLayout.SOUTH);
	}
	

}
