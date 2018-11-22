package Tests;

import Produits.Caracteristique;
import Produits.Categorie;
import Produits.CategorieProduit;
import Produits.Produit;
import projet_BD.Utilisateur;

public class TestCaracteristique {

	public static void main(String[] args) {
		
		// ajout d'un produit et de ses caracteristiques
		
		CategorieProduit nom = CategorieProduit.INFORMATIQUE;
		Categorie cat = new Categorie(nom);
		Utilisateur user = new Utilisateur("sophia.vilayvong@grenoble-inp.org", "Vilayvong", "Sophia", "6 Place Pasteur, 38000 Grenoble");
		Produit prod = new Produit(cat, user, "souris", 12, 10);
		prod.ajouter();
		Caracteristique caract1 = new Caracteristique("Couleur", "Noir", prod);
		Caracteristique caract2 = new Caracteristique("Liaison", "Filaire", prod);
		Caracteristique caract3 = new Caracteristique("Resolution", "12000 dpi", prod);
		Caracteristique[] tab = {caract1, caract2, caract3};
		prod.ajouterCaracteristique(tab);
	}

}
