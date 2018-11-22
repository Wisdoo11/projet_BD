package Tests;

import projet_BD.Utilisateur;

public class TestUtilisateur {

	public static void main(String[] args) {
		String email = "sophia@sophia.fr";
		String nom = "Vilayvong";
		String prenom = "Sophia";
		String adresse_postale = "6 Place Place, Grenoble 38000";
		Utilisateur user = new Utilisateur(email, nom, prenom, adresse_postale);
		user.ajouter();
		//user.miseAJourPrenom("So");
		//user.miseAJourNom("Vil");
		//user.miseAJourEmail("s@sv.fr");

	}

}
