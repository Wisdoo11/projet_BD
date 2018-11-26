package projet_BD;

public class Utilisateur {
	
	private String email;
	private String nom;
	private String prenom;
	private String adressePostale;
	
	public Utilisateur(String email,String nom,String prenom,String adressePostale) {
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.adressePostale = adressePostale;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void ajouter() {
	    String preStmt = "insert into Utilisateur1 values ('" + this.email + "', '" + this.nom + "', '" + this.prenom + "', '" + this.adressePostale + "');";
	    System.out.println(preStmt);
	    Requete Requete = new Requete(preStmt);
	    Requete.execute();
	}
	
	public void miseAJourEmail(String email) {
		this.email = email;
	    String preStmt = "update Utilisateur1 set email = '" + this.email + "' where nom = '" + this.nom + "' and prenom = '"
	    		+ this.prenom + "' and adressePostale = '" + this.adressePostale + ";";
	    System.out.println(preStmt);
	    Requete Requete = new Requete(preStmt);
	    Requete.execute();
	}
	
	public void miseAJourNom(String nom) {
	    String preStmt = "update Utilisateur1 set nom = '" + this.nom + "' where email = '" + this.email + "' and prenom = '"
	    		+ this.prenom + "' and adressePostale = '" + this.adressePostale + ";";
	    System.out.println(preStmt);
	    Requete Requete = new Requete(preStmt);
	    Requete.execute();
	}
	
	public void miseAJourPrenom(String prenom) {
		this.prenom = prenom;

	    String preStmt = "update Utilisateur1 set prenom = '" + this.prenom + "' where nom = '" + this.nom + "' and email = '"
	    		+ this.email + "' and adressePostale = '" + this.adressePostale + ";";
	    System.out.println(preStmt);
	    Requete Requete = new Requete(preStmt);
	    Requete.execute();
	}
	
	public void miseAJourAdressePostale(String adresse) {
		this.adressePostale = adresse;
		
	    String preStmt = "update Utilisateur1 set adresse = '" + this.adressePostale + "' where nom = '" + this.nom + "' and prenom = '"
	    		+ this.prenom + "' and email = '" + this.email + ";";
	    System.out.println(preStmt);
	    Requete Requete = new Requete(preStmt);
	    Requete.execute();
	}
	
}
