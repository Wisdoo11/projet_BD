package projet_BD;

import affichage.FenetreAccueil;
import java.sql.Connection;
import java.sql.SQLException;

public class Application {

	public static void main(String[] args) throws SQLException {
		
		// TODO se connecter à la base de données
		boolean estConnectBD = false;
		Connection conn = null;

	    ConnexionBD connexion = new ConnexionBD();
	    try {
			conn = connexion.connexion();
			estConnectBD = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//TODO se connecter à la base de données
		
		FenetreAccueil fenetre = new FenetreAccueil();
		
		
		//Déconnexion de la base de données
		if (estConnectBD) {
			connexion.deconnexion(conn);
		}
	}

}
