package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.*;
import java.lang.String;

import projet_BD.Requete;

public class FenetreAjoutProduits extends JDialog {

	private boolean estAdmin;
	private String categorie;
	private String nombreProduits;
	private String idSalle;
	private JTextField categorieText, idSalleText;
	private JComboBox jourBox, anneeBox, moisBox, heureBox, minuteBox,secondeBox;
	private int typeDuree;
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
				"<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B> Ajoutez vos " + nombreProduits + " produits : </B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);

		Box box = Box.createVerticalBox();

		Box box0 = Box.createVerticalBox();
		box0.add(Box.createRigidArea(new Dimension(0,20)));

		box.add(box0);

		categorieText = new JTextField(categorie);
		idSalleText = new JTextField(idSalle);

		for (int i = 0; i < Integer.parseInt(nombreProduits); i++) {
			// Ajouts des produits
			JPanel panProduit = new JPanel();	
			panProduit.setBackground(Color.white);
			panProduit.setPreferredSize(new Dimension(200, 35));
			JLabel produit = new JLabel("Nom du produit : ");

			Requete requete = new Requete("Select Produit1.id_produit, Produit1.nom\r\n" + 
					"From Produit1\r\n" + 
					"Where Produit1.nom_categorie = '" + categorie + "'\r\n" +
					"MINUS\r\n" + 
					"Select Produit1.id_produit, Produit1.nom\r\n" + 
					"From Salle1, Vente1, Produit1\r\n" + 
					"Where Salle1.nom_categorie = '" + categorie + "'\r\n" + 
					"AND Produit1.nom_categorie = '" + categorie + "'\r\n" + 
					"AND Vente1.id_salle = Salle1.id_salle\r\n" + 
					"AND Vente1.id_produit = Produit1.id_produit");
			ArrayList<String[]> selection = new ArrayList<String[]>();
			requete.getSelection(selection);

			produitBox = new JComboBox<String>();
			for (String[] elt : selection) {
				produitBox.addItem(elt[0] + " - " + elt[1]);
			}
			panProduit.add(produit);
			panProduit.add(produitBox);


			//prix de départ des produits
			JPanel panPrix = new JPanel();	
			panPrix.setBackground(Color.white);
			panPrix.setPreferredSize(new Dimension(250, 50));
			JLabel prix;
			JTextField prixText;
			prix = new JLabel("Prix de départ du produit : ");
			prixText = new JTextField();
			prixText.setPreferredSize(new Dimension(100, 25));
			panPrix.add(prix);
			panPrix.add(prixText);

			//date et heure de fin de vente
			JPanel panDate = new JPanel();	
			panDate.setBackground(Color.white);
			panDate.setPreferredSize(new Dimension(200, 35));

			JPanel panHeure = new JPanel();	
			panHeure.setBackground(Color.white);
			panHeure.setPreferredSize(new Dimension(200, 35));

			if (typeDuree==1) { //durée libre
				JLabel date;
				JTextField dateText;
				date = new JLabel("Date de fin de vente : null");
				panDate.add(date);

				JLabel heure;
				JTextField heureText;
				heure = new JLabel("Heure de fin de vente : null");
				panDate.add(heure);

			} else { //durée limitée
				JLabel date = new JLabel("Date de fin de vente : ");

				//année
				anneeBox = new JComboBox<>();
				JLabel annee = new JLabel("Annee : ");
				for (int j=2018 ; j<2020 ; j++) {
					anneeBox.addItem(j);
				}
				//mois
				moisBox = new JComboBox<>();
				JLabel mois = new JLabel("Mois : ");
				String[] moisTab = {"01","02","03","04","05","06","07","08","09","10","11","12"};
				for (String j:moisTab) {
					moisBox.addItem(j);
				}
				//jour
				jourBox = new JComboBox<>();
				JLabel jour = new JLabel("Jour : ");
				String[] jourTab = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15",
						"16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
				for (String j:jourTab) {
					jourBox.addItem(j);
				}
				panDate.add(date);
				panDate.add(annee);
				panDate.add(anneeBox);
				panDate.add(mois);
				panDate.add(moisBox);
				panDate.add(jour);
				panDate.add(jourBox);

				JLabel heure = new JLabel("Heure de fin de vente : ");

				//heure
				heureBox = new JComboBox<>();
				JLabel heureLabel = new JLabel("Heure : ");
				String[] heureTab = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15",
						"16","17","18","19","20","21","22","23"};
				for (String j:heureTab) {
					heureBox.addItem(j);
				}
				//minute
				JLabel minute = new JLabel("Minute : ");
				minuteBox = new JComboBox<>();
				for (int j=0 ; j <60 ; j++) {
					minuteBox.addItem(j);
				}
				//seconde
				secondeBox = new JComboBox<>();
				JLabel seconde = new JLabel("Seconde : ");
				for (int j=0 ; j <60 ; j++) {
					secondeBox.addItem(j);
				}
				panHeure.add(heure);
				panHeure.add(heureLabel);
				panHeure.add(heureBox);
				panHeure.add(minute);
				panHeure.add(minuteBox);
				panHeure.add(seconde);
				panHeure.add(secondeBox);
			}

			Box b1 = Box.createHorizontalBox();
			b1.add(panProduit);
			b1.add(Box.createRigidArea(new Dimension(0,10)));
			b1.add(panPrix);

			Box b = Box.createVerticalBox();
			b.add(panDate);
			b1.add(Box.createRigidArea(new Dimension(0,10)));
			b.add(panHeure);

			box.add(b1);
			box.add(b);
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
				String preStmt;
				String preStmt1;
				Requete requete1;
				ArrayList<String[]> selection;
				int elt1, elt2;
				int salle = Integer.parseInt( (String) idSalleText.getText());

				while (itProd.hasNext() ) {

					String date = anneeBox.getSelectedItem().toString() + "-" + moisBox.getSelectedItem().toString() +"-" +
							jourBox.getSelectedItem().toString() + " " + heureBox.getSelectedItem().toString() + ":" + minuteBox.getSelectedItem().toString() +
							":" + secondeBox.getSelectedItem().toString() + ".0";
					elt1 = Integer.parseInt(((String) itProd.next().getSelectedItem()).split(" - ")[0]); //on récupère l'id_produit du produit
					elt2 = Integer.parseInt(itPrix.next().getText()); //on récupère son prix de départ
					preStmt1 = "select id_produit from Vente1 where id_produit=" + elt1;
					requete1 = new Requete(preStmt1);
					selection = new ArrayList<String[]>();
					requete1.getSelection(selection);
					//on vérifie que le produit que l'on veut mettre en vente n'est pas déjà mis à la vente
					if (!selection.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Vous ne pouvez pas ajouter plusieurs fois le même produit", " ", JOptionPane.INFORMATION_MESSAGE);
					} else {
						//sinon on l'ajoute à la base de données
						preStmt = "insert into Vente1(id_produit, id_salle, prix_depart, temps) values("
								+ elt1 + ", " + salle +	"," + elt2 + ", " + date + ")"; //TODO récupérer le temps suivant le type de vente
						Requete requete = new Requete(preStmt);
						requete.executeUpdateReq();

						System.out.println(preStmt);
					}

				}
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

		this.getContentPane().add(box);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
}
