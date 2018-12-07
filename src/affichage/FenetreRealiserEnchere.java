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
	
	private String email, categorie, idVente, idSalle, nomProduit, prixCourant, idProduit;
	private JLabel emailLabel, nomLabel, CategorieLabel, prixProposeLabel, quantiteLabel;
	private JTextField prixProposeText;
	private JComboBox quantiteBox;

	public FenetreRealiserEnchere(JFrame parent, String title, boolean modal, String email, String categorie, 
			String nomProduit, String idVente, String idSalle, String prixCourant, String idProduit){
		super(parent, title, modal);
		this.email = email;
		this.nomProduit = nomProduit;
		this.categorie = categorie;
		this.idVente = idVente;
		this.idSalle = idSalle;
		this.prixCourant = prixCourant;
		this.idProduit = idProduit;
		this.setSize(650, 400);
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
				"Where Produit1.id_produit=Vente1.id_produit\r\n" +
				"AND Vente1.id_vente=" + idVente +"\r\n" +
				"AND Vente1.id_salle=" + idSalle + "\r\n"
				);
		ArrayList<String[]> selection = new ArrayList<String[]>();
		requeteStock.getSelection(selection);
		
		quantiteBox = new JComboBox<String>();
		for (String[] elt : selection) {
			for (String i:elt)
				for (int j=1 ; j < Integer.parseInt(i)+1 ; j++) {
					quantiteBox.addItem(j);
				}
		}
		panQuantite.add(quantiteLabel);
		panQuantite.add(quantiteBox);
		
    	//le prix de l'enchère
		JPanel panPrixPropose = new JPanel();	
		panPrixPropose.setBackground(Color.white);
		panPrixPropose.setPreferredSize(new Dimension(250, 80));
		panPrixPropose.setBorder(BorderFactory.createTitledBorder("Prix proposé :"));
		prixProposeLabel = new JLabel("Prix total :");
		prixProposeText = new JTextField();
		prixProposeText.setPreferredSize(new Dimension(150, 30));
		panPrixPropose.add(prixProposeLabel);
		panPrixPropose.add(prixProposeText);

    	JPanel content = new JPanel();
    	content.setBackground(Color.white);
    	content.add(panEmail);
    	content.add(panCategorie);
    	content.add(panNom);
    	content.add(panQuantite);
    	content.add(panPrixPropose);

    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");

    	okBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			
    			int[] info;
    			int type, libre, revocable, enchereM; //TODO ici on ne gère pas la fin des enchères :
    			//TODO les paramètres revocable et type : permet de déterminer le gagnant
    			Requete requeteTypeVente = new Requete("select type_vente, est_libre, est_revocable, enchere_multiple\r\n"
    					+ "From Salle1\r\n"
    					+ "Where id_salle=" + idSalle);
    			info = requeteTypeVente.recupTypeSalle();
    			
    			//si vente montante
    			if (info[0]==1) { //si montante
    				System.out.println("La vente est montante !");
    				JOptionPane.showMessageDialog(null,	"La vente est montante !", " ", JOptionPane.INFORMATION_MESSAGE);
    				if (info[3]==1) { //possiblité pour un même utilisateur d'enchérir plusieurs fois
    					System.out.println("Encheres multiples possible !");
    					JOptionPane.showMessageDialog(null,	"Encheres multiples possible !", " ", JOptionPane.INFORMATION_MESSAGE);
    					
    					if (Float.parseFloat(prixProposeText.getText().toString())
    							/ Float.parseFloat(quantiteBox.getSelectedItem().toString()) <= Float
										.parseFloat(prixCourant)) {
    						JOptionPane.showMessageDialog(null,
    								"Le prix de départ est : " + prixCourant + "euros"
    										+ "\nVeuillez rentrer un prix supérieur à celui-ci",
    										" ", JOptionPane.INFORMATION_MESSAGE);
    					} else {
						// on ajoute l'enchère dans la table Enchere1
						String preStmt = "insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values('"
								+ email + "', " + idVente + ", " + prixProposeText.getText().toString()
								+ ", CURRENT_TIMESTAMP" + ", " + quantiteBox.getSelectedItem().toString() + ")";
						Requete requete = new Requete(preStmt);
						requete.executeUpdateReq();

						System.out.println(preStmt);

    					}
    				} else {//ne peut pas encherir plusieurs fois
						System.out.println("Encheres multiples non possible !");
						JOptionPane.showMessageDialog(null, "Encheres multiples non possible !", " ", JOptionPane.INFORMATION_MESSAGE);
						Requete requeteEnchereM = new Requete("select COUNT(*)\r\n"
								+ "From Enchere1\r\n"
								+ "Where id_vente=" + idVente + "\r\n"
								+ "AND email='" + email + "'");
						boolean aDejaEncheri = requeteEnchereM.aDejaEncherir();
						if (aDejaEncheri) {
							JOptionPane.showMessageDialog(null, "Encheres multiples non possible !\n Vous avez déjà enchéri !", " ", JOptionPane.INFORMATION_MESSAGE);
						} else {
							if (Float.parseFloat(prixProposeText.getText().toString())
									/ Float.parseFloat(quantiteBox.getSelectedItem().toString()) <= Float
									.parseFloat(prixCourant)) {
								JOptionPane.showMessageDialog(null,
										"Le prix de départ est : " + prixCourant + "euros"
												+ "\nVeuillez rentrer un prix supérieur à celui-ci",
										" ", JOptionPane.INFORMATION_MESSAGE);
							} else {
								// on ajoute l'enchère dans la table Enchere1
								String preStmt = "insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values('"
										+ email + "', " + idVente + ", " + prixProposeText.getText().toString()
										+ ", CURRENT_TIMESTAMP" + ", " + quantiteBox.getSelectedItem().toString() + ")";
								Requete requete = new Requete(preStmt);
								requete.executeUpdateReq();

								System.out.println(preStmt);
							}
						}
					}

				} else {//la vente est descendante
					System.out.println("La vente est descendante !");
					JOptionPane.showMessageDialog(null,	"La vente est descendante !", " ", JOptionPane.INFORMATION_MESSAGE);
					
					// on ajoute l'enchère dans la table Enchere1
					String preStmt = "insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values('"
							+ email + "', " + idVente + ", " + prixProposeText.getText().toString()
							+ ", CURRENT_TIMESTAMP" + ", " + quantiteBox.getSelectedItem().toString() + ")";
					Requete requete = new Requete(preStmt);
					requete.executeUpdateReq();

					// on met à jour la quantité de la vente
					String preStmt1 = "update Produit1 set stock = stock - " + quantiteBox.getSelectedItem().toString() + "where id_produit = " +
							idProduit;
					Requete requete1 = new Requete(preStmt1);
					requete1.executeUpdateReq();


					System.out.println(preStmt1);
				}
    			
    			//multiplicité des enchères par un même utilisateur
    			if (info[3]==1)
    			
    			
    			

				//on ferme la fenêtre à la fin de la manipulation
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
