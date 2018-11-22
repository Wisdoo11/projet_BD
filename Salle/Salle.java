package Salle;

import Produits.Categorie;
import projet_BD.Requete;

public class Salle {
	
	private int idSalle;
	private Categorie categorie;
	private boolean montante;
	private boolean nonRevocable;
	private boolean dureeLibre;
	private boolean encherirPlusieursFois;
	
	public Salle(Categorie categorie) {
		this.categorie = categorie;
		this.montante = true;
		this.nonRevocable = true;
		this.dureeLibre = true;
		this.encherirPlusieursFois = true;
	}
	
	public Salle(boolean montante, boolean nonRevocable, boolean dureeLibre, boolean encherir, Categorie categorie) {
		this.categorie = categorie;
		this.montante = montante;
		this.nonRevocable = nonRevocable;
		this.dureeLibre = dureeLibre;
		this.encherirPlusieursFois = encherir;
	}
	
	// comment fait on pour récupérer l'id d'une salle ?
	public void setIdSalle() {
		//TODO récupérer IDSalle
	}
	
	public int getIdSalle() {
		return idSalle;
	}
	
	public void ajouter() {
		// TODO on n'a pas besoin de se reconnecter sur le serveur à chaque fois que l'on fait une requête !!
	    String connUrl = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
	    String user = "vilayvos";
	    String passwd = "vilayvos";
	    //TODO

	    String preStmt = "insert into Salle1 values ('" + this.categorie.getNomCategorie() + "', '" 
	    + this.montante + "', '" + this.dureeLibre + "', '" 
	    + this.nonRevocable + "', '" + this.encherirPlusieursFois + "');";
	    System.out.println(preStmt);
	    Requete requete = new Requete(connUrl, user, passwd, preStmt);
	    requete.execute();
	}
	
}
