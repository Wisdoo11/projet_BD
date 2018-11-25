package projet_BD;

import affichage.FenetreAccueil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Application {

	public static void main(String[] args) throws SQLException {
		
		// Connexion à la BD
		boolean estConnectBD = false;
		Connection conn = null;

	    ConnexionBD connexion = new ConnexionBD();
	    try {
			conn = connexion.connexion();
			estConnectBD = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

	    // quelques requetes de tests
	    String preStmt = "select * from Utilisateur1";
	    RequeteSansCo requete = new RequeteSansCo(conn, preStmt);
	    requete.execute();
	    
	    // requete non valide car violation de la contrainte d'intégrité : clé primaire
	    String preStmt1 = "insert into Utilisateur1 values ('toto@toto.fr', 'toto3', 'toto3', '2 avenue toto3')";
	    RequeteSansCo requete1 = new RequeteSansCo(conn, preStmt1);
	    try {
	    	requete1.executeUpdateReq();
	    } catch (SQLIntegrityConstraintViolationException e) { // recuperation de l'erreur
	          System.err.println("Cet email existe déjà !\n");
	          e.printStackTrace(System.err);
	    }
	    
	    String preStmt2 = "select * from Utilisateur1";
	    RequeteSansCo requete2 = new RequeteSansCo(conn, preStmt2);
	    requete2.execute();
		
	    // ouverture de l'application
		FenetreAccueil fenetre = new FenetreAccueil();
		
		//Déconnexion de la base de données
		if (estConnectBD) {
			connexion.deconnexion(conn);
		}
	}

}
