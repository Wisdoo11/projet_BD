package Tests;

import Produits.Categorie;
import Produits.CategorieProduit;
import Produits.Produit;
import projet_BD.Utilisateur;

public class TestProduit {

	public static void main(String[] args) {
		
		// création simple d'un produit sans caractéristique
		
		CategorieProduit nom = CategorieProduit.INFORMATIQUE;
		Categorie cat = new Categorie(nom);
		Utilisateur user = new Utilisateur("sophia.vilayvong@grenoble-inp.org", "Vilayvong", "Sophia", "6 Place Pasteur, 38000 Grenoble");
		Produit prod = new Produit(cat, user, "souris", 12, 10);
		prod.ajouter();
	}

}
