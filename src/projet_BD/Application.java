package projet_BD;

import affichage.FenetreAccueil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import Produits.CategorieProduit;

public class Application {

	public static void main(String[] args) throws SQLException {	

	    //quelques requetes de tests
	    String preStmt = "select * from vente1 where id_vente=13";
	    Requete requete = new Requete(preStmt);
	    //requete.executeUpdateReq();
	    requete.execute();
	    
	    System.out.println("");
	    
	    // requete non valide car violation de la contrainte d'intégrité : clé primaire
	    //String preStmt1 = "insert into Utilisateur1 values ('toto3@toto3.fr', 'toto3', 'toto3', '2 avenue toto3')";
	    //Requete requete1 = new Requete(preStmt1);
	    //try {
	    	//requete1.executeUpdateReq();
	    //} catch (SQLIntegrityConstraintViolationException e) { // recuperation de l'erreur
	    //}
	    
	    //String preStmt2 = "select * from Categorie1";
	    //Requete requete2 = new Requete(preStmt2);
	    //requete2.execute();
		
	    // ouverture de l'application
		FenetreAccueil fenetre = new FenetreAccueil();
		
	}

}
