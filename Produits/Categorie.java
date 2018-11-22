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
		
		// TODO on n'a pas besoin de se reconnecter sur le serveur à chaque fois que l'on fait une requête !!
	    String connUrl = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
	    String user = "vilayvos";
	    String passwd = "vilayvos";
	    // TODO
		
	    String preStmt = "update Categorie1 set description = '" + this.description + "' where nom = '" + this.nom + "';";
	    System.out.println(preStmt);
	    Requete requete = new Requete(connUrl, user, passwd, preStmt);
	    requete.execute();
	}
	
	public void ajouter() {
		// TODO on n'a pas besoin de se reconnecter sur le serveur à chaque fois que l'on fait une requête !!
	    String connUrl = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
	    String user = "vilayvos";
	    String passwd = "vilayvos";
	    // TODO

	    String preStmt = "insert into Categorie1 values ('" + this.nom + "', '" + this.description + "');";
	    System.out.println(preStmt);
	    Requete requete = new Requete(connUrl, user, passwd, preStmt);
	    requete.execute();
	}

}
