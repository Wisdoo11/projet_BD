package Produits;

import projet_BD.Requete;

public class Categorie {
	
	private CategorieProduit nom;
	private String description;
	
	public Categorie(CategorieProduit nom, String description) {
		this.nom = nom;
		this.description = description;
	}
	
	public Categorie(CategorieProduit nom) {
		this.nom = nom;
	}
	
	public String getNomCategorie() {
		return nom.toString();
	}
	
	public void modifierDescription(String description) {
		this.description = description;
	    String preStmt = "update Categorie1 set description = '" + this.description + "' where nom = '" + this.nom + "';";
	    //System.out.println(preStmt);
	    Requete requete = new Requete(preStmt);
	    requete.execute();
	}
	
	public void ajouter() {
	    String preStmt = "insert into Categorie1 values ('" + this.nom + "', '" + this.description + "');";
	    //System.out.println(preStmt);
	    Requete requete = new Requete(preStmt);
	    requete.execute();
	}

}
