package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import projet_BD.Requete;

import java.util.*;


public class SalleDialog extends JDialog {
	
	private ZDialogInfo zInfo = new ZDialogInfo();
	private boolean sendData;
	private JLabel nomLabel, CategorieLabel, produitLabel, venteLabel,vente2Label, typeLabel, prixLabel,prix2Label, icon;
	private JComboBox Categorie, produit, type, Produit;
	private JTextField nom, vente, prix;
	private String catSelectionne;

	public SalleDialog(JFrame parent, String title, boolean modal, String catSelectionne){
		super(parent, title, modal);
		this.catSelectionne = catSelectionne;
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	public ZDialogInfo showZDialog(){
		this.sendData = false;
		this.setVisible(true);
		return this.zInfo;
	}

	private void initComponent(){
		//Icône
		icon = new JLabel(new ImageIcon("images/icone.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);

		/*
		//Le nom de la salle de vente
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
    	panNom.setPreferredSize(new Dimension(250, 80));
    	nom = new JTextField();
    	nom.setPreferredSize(new Dimension(100, 25));
    	panNom.setBorder(BorderFactory.createTitledBorder("Nom de la salle"));
    	nomLabel = new JLabel("Saisir un nom :");
    	panNom.add(nomLabel);
    	panNom.add(nom);

    	//La catégorie de la salle
    	JPanel panCategorie = new JPanel();
    	panCategorie.setBackground(Color.white);
    	panCategorie.setPreferredSize(new Dimension(250, 80));
    	panCategorie.setBorder(BorderFactory.createTitledBorder("Catégorie d'objets"));

    	Requete requete = new Requete("select nom from Categorie1");
		ArrayList<String[]> selection = new ArrayList<String[]>();
		requete.getSelection(selection);

    	Categorie = new JComboBox();
    	int item = 1;
    	int pos = 2;
    	System.out.println(catSelectionne);
    	for (String[] elt : selection) {
    		for (String i:elt) {
    			if (i.equals(catSelectionne)) {
    				pos = item;
    			}
    			Categorie.addItem(i);
    		}
    		item++;
    	}
    	CategorieLabel = new JLabel("Catégorie : " + catSelectionne);
    	panCategorie.add(CategorieLabel);
    	Categorie.setSelectedIndex(pos-1);
    	

    	//Le nombre de ventes
    	JPanel panVente = new JPanel();
    	panVente.setBackground(Color.white);
    	panVente.setPreferredSize(new Dimension(250, 80));
    	panVente.setBorder(BorderFactory.createTitledBorder("Nombre de ventes"));
    	venteLabel = new JLabel("Nombre de ventes : ");
    	vente2Label = new JLabel(" ventes");
    	vente = new JTextField("1");
    	vente.setPreferredSize(new Dimension(90, 25));
    	panVente.add(venteLabel);
    	panVente.add(vente);
    	panVente.add(vente2Label);
    	*/

    	//Le type de la salle
    	JPanel panType = new JPanel();
    	panType.setBackground(Color.white);
    	panType.setPreferredSize(new Dimension(250, 80));
    	panType.setBorder(BorderFactory.createTitledBorder("Type de vente"));
    	type = new JComboBox();
    	type.addItem("Montante");
    	type.addItem("Descendante");
    	typeLabel = new JLabel("Type : ");
    	panType.add(typeLabel);
    	panType.add(type);
    	
    	/*
    	//Le nom du produit
    	JPanel panProduit = new JPanel();
    	panProduit.setBackground(Color.white);
    	panProduit.setPreferredSize(new Dimension(250, 80));
    	panProduit.setBorder(BorderFactory.createTitledBorder("Le(s) produit(s)"));
    	produit = new JComboBox();
    	
    	String catSelectionne = (String) Categorie.getSelectedItem();
    	Requete requete2 = new Requete("select Produit1.nom from Produit1 where Produit1.nom_categorie = " + catSelectionne);
    	ArrayList<String[]> selectionCategorie = new ArrayList<String[]>();
    	requete2.getSelection(selectionCategorie);
    	
    	//
    	ListeProduit listeP = new ListeProduit(catSelectionne);
    	ArrayList<String> lp = listeP.afficheListe();

    	for (String elt : lp) {
    		produit.addItem(elt);
    	}
    	produitLabel = new JLabel("Nom du produit");
    	panProduit.add(produitLabel);
    	panProduit.add(produit);
    	

    	//Le prix de vente
    	JPanel panPrix = new JPanel();
    	panPrix.setBackground(Color.white);
    	panPrix.setPreferredSize(new Dimension(250, 80));
    	panPrix.setBorder(BorderFactory.createTitledBorder("Prix de vente"));
    	prixLabel = new JLabel("Prix de vente :     ");
    	prix2Label = new JLabel(" euro");
    	prix = new JTextField("1");
    	prix.setPreferredSize(new Dimension(90, 25));
    	panPrix.add(prixLabel);
    	panPrix.add(prix);
    	panPrix.add(prix2Label);
    	*/

    	JPanel content = new JPanel();
    	content.setBackground(Color.white);
    	//content.add(panNom);
    	//content.add(panCategorie);
    	//content.add(panProduit);
    	//content.add(panVente);
    	content.add(panType);
    	//content.add(panPrix);


    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");

    	okBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			zInfo = new ZDialogInfo(nom.getText(), (String)Categorie.getSelectedItem(), (String)produit.getSelectedItem() ,getVente(), (String)type.getSelectedItem(), getPrix());
    			setVisible(false);
    		}


    		public String getVente(){
    			return (vente.getText().equals("")) ? "1" : vente.getText();
    		}

    		public String getPrix(){
    			return (prix.getText().equals("")) ? "1" : prix.getText();
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

    	this.getContentPane().add(panIcon, BorderLayout.WEST);
    	this.getContentPane().add(content, BorderLayout.CENTER);
    	this.getContentPane().add(control, BorderLayout.SOUTH);
	}
}
