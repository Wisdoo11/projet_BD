package Tests;

import Produits.Categorie;
import Produits.CategorieProduit;

public class TestCategorie {

	public static void main(String[] args) {
		CategorieProduit nom = CategorieProduit.INFORMATIQUE;
		Categorie cat = new Categorie(nom);
		cat.ajouter();
		cat.modifierDescription("rien à dire");
	}

}
