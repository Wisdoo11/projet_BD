package Salle;

import java.util.Date;

import Produits.Produit;
import projet_BD.Requete;

public class Vente {
	
	private int idVente;
	private Produit produit;
	private Salle salle;
	private float prixDeDepart;
	private Date dateFin;
	
	public Vente(Produit produit, Salle salle, float prix, Date date) {
		this.produit = produit;
		this.salle = salle;
		this.prixDeDepart = prix;
		this.dateFin = date;
	}
	
	private void setDate() {
		// TODO pour récupérer l'ID vente
	}
	
	public void ajouter() {
		// TODO on n'a pas besoin de se reconnecter sur le serveur à chaque fois que l'on fait une requête !!
	    String connUrl = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
	    String user = "vilayvos";
	    String passwd = "vilayvos";
	    //TODO

	    String preStmt = "insert into Vente1 values ('" + this.produit.getIdProduit() + "', '" 
	    + this.salle.getIdSalle() + "', '" + this.prixDeDepart + "', '" 
	    + this.dateFin + "');";
	    System.out.println(preStmt);
	    Requete requete = new Requete(connUrl, user, passwd, preStmt);
	    requete.execute();
	}
	
	
	
	
}
