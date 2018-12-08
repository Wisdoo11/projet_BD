package affichage;

import projet_BD.Requete;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

public class FenetreFindeVente extends JDialog {

    private JComboBox<String> venteBox;

    public FenetreFindeVente(JFrame parent, String title, boolean modal){
        super(parent, title, modal);
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
                "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B> Sélectionnez la vente à cloturer : </B></FONT></P></HTML>";
        JLabel label = new JLabel(labelText);
        panel.add(label);
        setContentPane(panel);

        //choix de la vente à cloturer
        JPanel panVente = new JPanel();
        panVente.setBackground(Color.white);
        panVente.setPreferredSize(new Dimension(200, 80));
        JLabel vente = new JLabel("Id de la vente : ");

        Requete requete = new Requete("Select id_vente\r\n" +
                "From Vente1\r\n");
        ArrayList<String[]> selection = new ArrayList<String[]>();
        requete.getSelection(selection);

        venteBox = new JComboBox<String>();
        for (String[] elt : selection) {
            for (String i:elt) {
                venteBox.addItem(i);
            }
        }
        panVente.add(vente);
        panVente.add(venteBox);

        //organisation visuelle
        Box box0 = Box.createVerticalBox();
        box0.add(Box.createRigidArea(new Dimension(0,10)));

        Box b1 = Box.createHorizontalBox();
        b1.add(panVente);
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

                int idSalle, idProduit;
                String idVente = venteBox.getSelectedItem().toString();
                int[] info_vente, info_gagnants;

                int type, libre, revocable, enchereM;
                Requete requete= new Requete("select id_salle\r\n"
                        + "From Vente1\r\n"
                        + "Where id_vente=" + idVente);
                idSalle = requete.recupIdSalle1();

                //on récupère les infos sur le type de vente de la salle de vente
                Requete requeteTypeVente1 = new Requete("select type_vente, est_libre, est_revocable, enchere_multiple\r\n"
                        + "From Salle1\r\n"
                        + "Where id_salle=" + idSalle);
                info_vente = requeteTypeVente1.recupTypeSalle();

                if (info_vente[0] == 0) { //si la vente est descendante
                    Requete requeteGagnants = new Requete("select email, prix_propose, quantite, temps\r\n"
                            + "From Enchere1\r\n"
                            + "Where id_vente=" + idVente + "\r\n"
                            + "order by temps asc");
                                        
                    requeteGagnants.execute();
                	ArrayList<String[]> gagnant = new ArrayList<String[]>();
                	requeteGagnants.getSelection(gagnant);
                		
                	String listeGagnants = "email \t prix propose \t quantite \t temps \n";
                		
            		//on récupére l'id produit
                    Requete requeteIdProduit = new Requete("select id_produit from Vente1 where id_vente=" + idVente);
                    idProduit = requeteIdProduit.recupIdProduit();
                    int nbGagnants=0;

                	for (String[] elt : gagnant) {
                		for (String i:elt) {
                			listeGagnants += i + "  \t  ";
                		}
                		listeGagnants += "\n";
                		nbGagnants++;
                	}
                	listeGagnants += "\n";
                	listeGagnants += "Il y a au total : " + nbGagnants + " gagnants !";                   
                    
                    if (info_vente[2] == 1) { //revocabilite de la vente
                        JOptionPane.showMessageDialog(null, "La vente est non révocable.\n Voici les gagnants:", " ", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "La vente est révocable.\n L'administrateur peut encore invalider la vente. \n Voici les gagnants provisoires:", " ", JOptionPane.INFORMATION_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(null, listeGagnants, " ", JOptionPane.INFORMATION_MESSAGE);
                   
                } else { //si la vente est montante
                	//on détermine et recupère les gagnants, on les affiche par ordre décroissant de prix unitaire
                	//i.e.: heure et date de l'enchère
                	if (info_vente[0]==0) {
	                    Requete requeteVenteLimiteeMont = new Requete("select Enchere1.email, Enchere1.prix_propose, Enchere1.quantite, Enchere1.temps\r\n"
	                        + "From Enchere1, Vente1\r\n"
	                        + "Where Enchere1.id_vente=Vente1.id_vente\r\n"
	                        + "And Enchere1.id_vente=" + idVente + "\r\n"
	                        + "And Enchere1.temps <= Vente1.temps\r\n" 
	                        + "Order by Enchere1.temps Desc");
	                    requeteVenteLimiteeMont.execute();
	                	ArrayList<String[]> gagnant = new ArrayList<String[]>();
	                	requeteVenteLimiteeMont.getSelection(gagnant);
	                		
	                	String listeGagnants = "email \t prix propose \t quantite \t temps \n";
	                		
	            		//on récupére l'id produit
	                    Requete requeteIdProduit = new Requete("select id_produit from Vente1 where id_vente=" + idVente);
	                    idProduit = requeteIdProduit.recupIdProduit();
	                    int nbGagnants=0;
	
	                	for (String[] elt : gagnant) {
	                			
	                		//on vérifie que les stocks > 0 : pour déterminer les gagnants
	                		//on récupère le stock restant du produit
	                		Requete requeteVerifStock = new Requete("select stock from Produit1 where id_produit=" + idProduit);
	                		int stock = requeteVerifStock.recupStockProduit();
	                		if (stock - Integer.parseInt(elt[2]) >= 0 ) {
	                			for (String i:elt) {
	                				listeGagnants += i + "  \t  ";
	                			}
	                			nbGagnants++;
	    						// on met à jour la quantité de produits disponible
	    						String preStmt1 = "update Produit1 set stock = stock - " + Integer.parseInt(elt[2]) + " where id_produit = " +
	    								idProduit;
	    						Requete requete1 = new Requete(preStmt1);
	    						requete1.executeUpdateReq();
	
	    						System.out.println(preStmt1);
	                		}
	                		listeGagnants += "\n";
	                	}
	                	listeGagnants += "Il y a au total : " + nbGagnants + " gagnants !";
	                		
	                	JOptionPane.showMessageDialog(null, listeGagnants, " ", JOptionPane.INFORMATION_MESSAGE);
	                } else {//a duree libre
	                	Requete requeteVenteLimiteeMont = new Requete("select Enchere1.email, Enchere1.prix_propose, Enchere1.quantite, Enchere1.temps\r\n"
		                        + "From Enchere1, Vente1\r\n"
		                        + "Where Enchere1.id_vente=Vente1.id_vente\r\n"
		                        + "And Enchere1.id_vente=" + idVente + "\r\n" 
		                        + "Order by Enchere1.temps Desc");
		                    requeteVenteLimiteeMont.execute();
		                	ArrayList<String[]> gagnant = new ArrayList<String[]>();
		                	requeteVenteLimiteeMont.getSelection(gagnant);
		                		
		                	String listeGagnants = "email \t prix propose \t quantite \t temps \n";
		                		
		            		//on récupére l'id produit
		                    Requete requeteIdProduit = new Requete("select id_produit from Vente1 where id_vente=" + idVente);
		                    idProduit = requeteIdProduit.recupIdProduit();
		                    int nbGagnants=0;
		
		                	for (String[] elt : gagnant) {
		                			
		                		//on vérifie que les stocks > 0 : pour déterminer les gagnants
		                		//on récupère le stock restant du produit
		                		Requete requeteVerifStock = new Requete("select stock from Produit1 where id_produit=" + idProduit);
		                		int stock = requeteVerifStock.recupStockProduit();
		                		if (stock - Integer.parseInt(elt[2]) >= 0 ) {
		                			for (String i:elt) {
		                				listeGagnants += i + "  \t  ";
		                			}
		                			nbGagnants++;
		    						// on met à jour la quantité de produits disponible
		    						String preStmt1 = "update Produit1 set stock = stock - " + Integer.parseInt(elt[2]) + " where id_produit = " +
		    								idProduit;
		    						Requete requete1 = new Requete(preStmt1);
		    						requete1.executeUpdateReq();
		
		    						System.out.println(preStmt1);
		                		}
		                		listeGagnants += "\n";
		                	}
		                	listeGagnants += "Il y a au total : " + nbGagnants + " gagnants !";
		                		
		                	JOptionPane.showMessageDialog(null, listeGagnants, " ", JOptionPane.INFORMATION_MESSAGE);
	                }
                }
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
