package projet_BD;

import affichage.FenetreAccueil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import Produits.CategorieProduit;

public class Application {

	public static void main(String[] args) throws SQLException {
		
		// peuplage de la table caractéristique
		//String description = "";
		//String cate;
		/*for (CategorieProduit cat:CategorieProduit.values()) {
			switch(cat) {
			case INFORMATIQUE:
				description = "ordinateurs, matériel informatique, connectiques informatiques, accessoires informatiques, etc.";
				break;
			case ELECTROMENAGER:
				description = "appareils et outils électriques domestiques";
				break;
			case TELEPHONIE:
				description = "téléphones et accessoires téléphoniques (écouteurs, kits mains-libres, etc) ";
				break;
			case TV_SON_PHOTO:
				description = "télévisions, enceintes, appareils photo et accesoires ...";
				break;
			case MEUBLE:
				description = "grandes variétés de meubles ...";
				break;
			case AUTRE:
				description = "autre";
			}
			cate = cat.toString().toCharArray()[0] + cat.toString().substring(1,cat.toString().length()).toLowerCase();
			String preStmt = "insert into Categorie1 values ('" + cate + "', '" + description + "')";
		    System.out.println(preStmt);
		    Requete requete = new Requete(preStmt);
		    requete.execute();
		}*/
		

	    //quelques requetes de tests
	    String preStmt = "delete from Utilisateur1 where nom='toto'";
	    Requete requete = new Requete(preStmt);
	    requete.execute();
	    
	    // requete non valide car violation de la contrainte d'intégrité : clé primaire
	    //String preStmt1 = "insert into Utilisateur1 values ('toto3@toto3.fr', 'toto3', 'toto3', '2 avenue toto3')";
	    //Requete requete1 = new Requete(preStmt1);
	    //try {
	    	//requete1.executeUpdateReq();
	    //} catch (SQLIntegrityConstraintViolationException e) { // recuperation de l'erreur
	    //}
	    
	    String preStmt2 = "select * from Utilisateur1";
	    Requete requete2 = new Requete(preStmt2);
	    requete2.execute();
		
	    // ouverture de l'application
		FenetreAccueil fenetre = new FenetreAccueil();
		
	}

}
