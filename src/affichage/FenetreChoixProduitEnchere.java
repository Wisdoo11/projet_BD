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
	private JComboBox<String> produitBox, idBox;
	private FenetreRealiserEnchere fenetre;

	public FenetreChoixProduitEnchere(JFrame parent, String title, boolean modal, String categorie, String email){
		super(parent, title, modal);
		this.categorie = categorie;
		this.email = email;
		this.setSize(300, 250);
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
    	
		//choix du produit à acheter
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
			produitBox.addItem(elt[0] + " - " + elt[1]);
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
				
				//on récupère le numéro de la salle et le numéro de la vente
				Requete requete1 = new Requete("Select distinct id_salle, id_vente\r\n" +
						"From Vente1\r\n" + 
						"Where id_produit=" + produitBox.getSelectedItem().toString().split(" - ")[0] + "\r\n"
						);
				ArrayList<String[]> selection1 = new ArrayList<String[]>();
				requete1.getSelection(selection1);
				
				idBox = new JComboBox<String>();
				for (String[] elt : selection1) {
					idBox.addItem(elt[0] + " - " + elt[1]);
				}
				
				String idSalle = idBox.getSelectedItem().toString().split(" - ")[0];
				String idVente = idBox.getSelectedItem().toString().split(" - ")[1];
				String nomProduit = produitBox.getSelectedItem().toString().split(" - ")[1];
				String idProduit = produitBox.getSelectedItem().toString().split(" - ")[0];
				System.out.println("Salle de vente du produit : " + idSalle + "\nSon numéro de vente : " + idVente + "\nSon nom : " + nomProduit);
				
				//pour récuperer le prix courant de l'enchère
    			String prixCourant;
    			
    			Requete requete00 = new Requete("select COUNT(*) from Enchere1 where id_vente=" + idVente);
    			System.out.println("Nom d'enchères : ");
    			requete00.execute();
    			//si pas d'enchère on récupère le prix de départ
    			if(requete00.premiereEnchereDeVente()) {
    				Requete requete0b = new Requete("select prix_depart from Vente1 where id_vente=" + idVente);
    				prixCourant = requete0b.recupPrixDepartEnchere();
    				System.out.println("Le prix de départ est : " + prixCourant + "euros");
    			} else {
        			String preStmt0 = "select prix_propose, quantite, temps\r\n"
        					+ "From Enchere1\r\n"
        					+ "Where id_vente=" + idVente + "\r\n"
        					+ "Group by id_enchere, email, id_vente, prix_propose, temps, quantite\r\n"
        					+ "HAVING temps=max(temps)"
        					+ "order by temps desc";
        			Requete requete0 = new Requete(preStmt0);
        			System.out.println("Les enchères des autres utlisateurs : ");
        			requete0.execute();
        			prixCourant = "" + requete0.recupPrixCourantEnchere() + "";
        			System.out.println("Le prix courant est : " + prixCourant);
    			}

    			JOptionPane.showMessageDialog(null, "Le prix de départ est : " + prixCourant + "euros", " ", JOptionPane.INFORMATION_MESSAGE);
    			
    			int[] info;
    			int type, libre, revocable, enchereM; //ici on ne gère pas la fin des enchères :
    			Requete requeteTypeVente = new Requete("select type_vente, est_libre, est_revocable, enchere_multiple\r\n"
    					+ "From Salle1\r\n"
    					+ "Where id_salle=" + idSalle);
    			info = requeteTypeVente.recupTypeSalle();
    			
    			if (info[0]==0) { //descendante
    				JOptionPane.showMessageDialog(null,	"La vente est descendante !", " ", JOptionPane.INFORMATION_MESSAGE);
    			} else {//montante
    				JOptionPane.showMessageDialog(null,	"La vente est montante !", " ", JOptionPane.INFORMATION_MESSAGE);
    			}
    			
    			if (info[3]==1) {
    				JOptionPane.showMessageDialog(null, "Encheres multiples possible !", " ", JOptionPane.INFORMATION_MESSAGE);
    			} else {
    				JOptionPane.showMessageDialog(null, "Encheres multiples non possible !", " ", JOptionPane.INFORMATION_MESSAGE);
    			}
				
				fenetre = new FenetreRealiserEnchere(null, "Bienvenue dans la salle n° " + idSalle + "de vente du produit",
						true, email, categorie, nomProduit, idVente, idSalle, prixCourant, idProduit);
				fenetre.afficher();
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
