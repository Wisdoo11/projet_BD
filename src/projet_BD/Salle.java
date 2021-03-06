package projet_BD;

import Produits.Categorie;

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
	    String preStmt = "insert into Salle1 values ('" + this.categorie.getNomCategorie() + "', '" 
	    + this.montante + "', '" + this.dureeLibre + "', '" 
	    + this.nonRevocable + "', '" + this.encherirPlusieursFois + "');";
	    System.out.println(preStmt);
	    Requete requete = new Requete(preStmt);
	    requete.execute();
	}
	
}
