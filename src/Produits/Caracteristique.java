package Produits;

import projet_BD.Requete;

public class Caracteristique {
	
	private String nom;
	private String valeur;
	private Produit produit;
	
	public Caracteristique(String nom, String valeur, Produit produit) {
		this.nom = nom;
		this.valeur = valeur;
		this.produit = produit;
	}
	
	public Caracteristique(String nom, Produit produit) {
		this.nom = nom;
		this.produit = produit;
	}
	
	protected void ajouter() {
	    String preStmt = "insert into Caracteristique values ('" + this.nom + "', '" + this.produit.getIdProduit() + "', '" + this.valeur + "');";
	    System.out.println(preStmt);
	    Requete requete = new Requete(preStmt);
	    requete.execute();
	}

}
