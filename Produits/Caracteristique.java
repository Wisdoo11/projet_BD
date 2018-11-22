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
		// TODO on n'a pas besoin de se reconnecter sur le serveur à chaque fois que l'on fait une requête !!
	    String connUrl = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
	    String user = "vilayvos";
	    String passwd = "vilayvos";
	    // TODO

	    String preStmt = "insert into Caracteristique values ('" + this.nom + "', '" + this.produit.getIdProduit() + "', '" + this.valeur + "');";
	    System.out.println(preStmt);
	    Requete requete = new Requete(connUrl, user, passwd, preStmt);
	    requete.execute();
	}

}
