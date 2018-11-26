package Produits;

import java.util.LinkedList;
import java.util.List;

import projet_BD.Requete;
import projet_BD.Utilisateur;

public class Produit {
	
	  // TODO la vérification des contraintes de valeur : à faire au niveau de l'interface graphique

	  private int idProduit;
	  private Categorie categorie;
	  private Utilisateur utilisateur;
	  private String nom;
	  private float prixDeRevient;
	  private int stock;
	  
	  public Produit(Categorie cat, Utilisateur utilisateur, String nom, float prix, int stock) {
		  this.categorie = cat;
		  this.utilisateur = utilisateur;
		  this.nom = nom;
		  this.prixDeRevient = prix;
		  this.stock = stock;
	  }
	  
	  public int getIdProduit() {
		  return idProduit;
	  }
	  
	  public void setIdProduit() {
		  // TODO récupérer l'ID produit généré automatiquement
	  }
	  
	  public void ajouter() {
		  String preStmt = "insert into Produit1 ('" + this.categorie + "', '" + this.utilisateur.getEmail() 
		    + "', '" + this.nom + "', '" + this.prixDeRevient + "', '" + this.stock + "');";
		  Requete requete = new Requete(preStmt);
		  requete.execute();
	  }
	  
	  public void ajouterCaracteristique(Caracteristique[] tableau) {
		  for (Caracteristique i:tableau) {
			  i.ajouter();
		  }
	  }
}
