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
		// TODO on n'a pas besoin de se reconnecter sur le serveur à chaque fois que l'on fait une requête !!
	    String connUrl = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
	    String user = "vilayvos";
	    String passwd = "vilayvos";
	    // TODO

	    String preStmt = "insert into Utilisateur1 values ('" + this.email + "', '" + this.nom + "', '" + this.prenom + "', '" + this.adressePostale + "');";
	    System.out.println(preStmt);
	    Requete requete = new Requete(connUrl, user, passwd, preStmt);
	    requete.execute();
	}
	
	public void miseAJourEmail(String email) {
		this.email = email;
		
		// TODO on n'a pas besoin de se reconnecter sur le serveur à chaque fois que l'on fait une requête !!
	    String connUrl = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
	    String user = "vilayvos";
	    String passwd = "vilayvos";
	    // TODO

	    String preStmt = "update Utilisateur1 set email = '" + this.email + "' where nom = '" + this.nom + "' and prenom = '"
	    		+ this.prenom + "' and adressePostale = '" + this.adressePostale + ";";
	    System.out.println(preStmt);
	    Requete requete = new Requete(connUrl, user, passwd, preStmt);
	    requete.execute();
	}
	
	public void miseAJourNom(String nom) {
		this.nom = nom;
		
		// TODO on n'a pas besoin de se reconnecter sur le serveur à chaque fois que l'on fait une requête !!
	    String connUrl = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
	    String user = "vilayvos";
	    String passwd = "vilayvos";
	    // TODO

	    String preStmt = "update Utilisateur1 set nom = '" + this.nom + "' where email = '" + this.email + "' and prenom = '"
	    		+ this.prenom + "' and adressePostale = '" + this.adressePostale + ";";
	    System.out.println(preStmt);
	    Requete requete = new Requete(connUrl, user, passwd, preStmt);
	    requete.execute();
	}
	
	public void miseAJourPrenom(String prenom) {
		this.prenom = prenom;
		
		// TODO on n'a pas besoin de se reconnecter sur le serveur à chaque fois que l'on fait une requête !!
	    String connUrl = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
	    String user = "vilayvos";
	    String passwd = "vilayvos";
	    //TODO

	    String preStmt = "update Utilisateur1 set prenom = '" + this.prenom + "' where nom = '" + this.nom + "' and email = '"
	    		+ this.email + "' and adressePostale = '" + this.adressePostale + ";";
	    System.out.println(preStmt);
	    Requete requete = new Requete(connUrl, user, passwd, preStmt);
	    requete.execute();
	}
	
	public void miseAJourAdressePostale(String adresse) {
		this.adressePostale = adresse;
		
		// TODO on n'a pas besoin de se reconnecter sur le serveur à chaque fois que l'on fait une requête !!
	    String connUrl = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
	    String user = "vilayvos";
	    String passwd = "vilayvos";
	    //TODO

	    String preStmt = "update Utilisateur1 set adresse = '" + this.adressePostale + "' where nom = '" + this.nom + "' and prenom = '"
	    		+ this.prenom + "' and email = '" + this.email + ";";
	    System.out.println(preStmt);
	    Requete requete = new Requete(connUrl, user, passwd, preStmt);
	    requete.execute();
	}
	
}
