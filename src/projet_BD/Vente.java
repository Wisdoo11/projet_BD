package projet_BD;

import java.util.Date;

import Produits.Produit;

public class Vente {
	
	private int idVente;
	private Produit produit;
	private Salle salle;
	private float prixDeDepart;
	private Date dateFin;
	private boolean estMontante;
	private boolean estRevocable;
	private boolean estDureeLibre;
	private boolean encherirPlusieursFois;
	
	
	public Vente(Produit produit, Salle salle, float prix, Date date) {
		this.produit = produit;
		this.salle = salle;
		this.prixDeDepart = prix;
		this.dateFin = date;
	}
	
	private void setDate() {
		// TODO pour récupérer l'ID vente
	}
	
	private void accepterEnchere(Enchere enchere) {
		//TODO a completer
		/* pour récupérer les utilisateurs qui ont enchérir sur ce produit
		 req1 : select email from Enchere1
		 where idVente = this.idVente
		 pour récupérer les prix unitaires proposée par les utilisateurs :
		 select prix_propose, quantite from Enchere1 where idVente = this.idVente
		 prix_unitaire = prix_propose/quantite
		 if montante et prix_propose <= max(prix_unitaire) : return false
		 sinon return true
		 if descendante :
		 if l'utilisateur a déjà enchéri et vente ne permet pas d'enchérir plusieurs fois :
		  */
	}
	
	public void ajouter() {
	    String preStmt = "insert into Vente1 values ('" + this.produit.getIdProduit() + "', '" 
	    + this.salle.getIdSalle() + "', '" + this.prixDeDepart + "', '" 
	    + this.dateFin + "');";
	    System.out.println(preStmt);
	    Requete requete = new Requete(preStmt);
	    requete.execute();
	}
	
	
	
	
}
