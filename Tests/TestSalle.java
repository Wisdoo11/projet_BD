package Tests;

import Produits.Categorie;
import Produits.CategorieProduit;
import projet_BD.Salle;

public class TestSalle {

	public static void main(String[] args) {
		CategorieProduit nom = CategorieProduit.INFORMATIQUE;
		Categorie cat = new Categorie(nom);
		//cat.ajouter();
		Salle salle = new Salle(cat);
		salle.ajouter();
	}

}
