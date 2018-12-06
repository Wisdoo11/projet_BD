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

public class FenetreRealiserEnchere extends JDialog {
	
	private String email, categorie, idVente, idSalle, nomProduit;
	private JLabel emailLabel, nomLabel, CategorieLabel, prixProposeLabel, quantiteLabel;
	private JTextField prixProposeText;
	private JComboBox quantiteBox;

	public FenetreRealiserEnchere(JFrame parent, String title, boolean modal, String email, String categorie, String nomProduit, String idVente, String idSalle){
		super(parent, title, modal);
		this.email = email;
		this.nomProduit = nomProduit;
		this.categorie = categorie;
		this.idVente = idVente;
		this.idSalle = idSalle;
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

		//email
		JPanel panEmail = new JPanel();
		panEmail.setBackground(Color.white);
    	panEmail.setPreferredSize(new Dimension(250, 80));
    	panEmail.setBorder(BorderFactory.createTitledBorder("Identité du vendeur :"));
    	emailLabel = new JLabel("Votre identité : " + email);
    	panEmail.add(emailLabel);

    	//La catégorie du produit   	
    	JPanel panCategorie = new JPanel();
    	panCategorie.setBackground(Color.white);
    	panCategorie.setPreferredSize(new Dimension(250, 80));
    	panCategorie.setBorder(BorderFactory.createTitledBorder("Catégorie :"));
    	CategorieLabel = new JLabel("Catégorie : " + categorie);
    	panCategorie.add(CategorieLabel);

    	//Le nom du produit
    	JPanel panNom = new JPanel();
    	panNom.setBackground(Color.white);
    	panNom.setPreferredSize(new Dimension(250, 80));
    	panNom.setBorder(BorderFactory.createTitledBorder("Produit :"));
    	nomLabel = new JLabel("Nom du produit :" + nomProduit);
    	panNom.add(nomLabel);
    	
    	//le prix de l'enchère
		JPanel panPrixPropose = new JPanel();	
		panPrixPropose.setBackground(Color.white);
		panPrixPropose.setPreferredSize(new Dimension(250, 80));
		panPrixPropose.setBorder(BorderFactory.createTitledBorder("Prix proposé :"));
		prixProposeLabel = new JLabel("Prix proposé :");
		prixProposeText = new JTextField();
		prixProposeText.setPreferredSize(new Dimension(150, 30));
		panPrixPropose.add(prixProposeLabel);
		panPrixPropose.add(prixProposeText);
		
    	//la quantité de produit souhaitée
		JPanel panQuantite = new JPanel();	
		panQuantite.setBackground(Color.white);
		panQuantite.setPreferredSize(new Dimension(250, 80));
		panQuantite.setBorder(BorderFactory.createTitledBorder("Prix proposé :"));
		quantiteLabel = new JLabel("Quantité souhaitée :");
		quantiteBox = new JComboBox<>();
		//on récupère le stock du produit
		Requete requeteStock = new Requete("select Produit1.stock\r\n" +
				"From Produit1, Vente1\r\n" +
				"Where Produit1.id_produit=Vente1.id_produit\r\n"
				);
		ArrayList<String[]> selection = new ArrayList<String[]>();
		requeteStock.getSelection(selection);
		
		quantiteBox = new JComboBox<String>();
		for (String[] elt : selection) {
			for (String i:elt)
			quantiteBox.addItem(i);
		}
		panQuantite.add(quantiteLabel);
		panQuantite.add(quantiteBox);

    	JPanel content = new JPanel();
    	content.setBackground(Color.white);
    	content.add(panEmail);
    	content.add(panCategorie);
    	content.add(panNom);
    	content.add(panPrixPropose);
    	content.add(panQuantite);

    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");

    	okBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			// TODO à finir
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
