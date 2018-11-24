package affichage;

import javax.swing.JDialog;

public class InfoProduitAVendre {
	
	private String email, nom, categorie, prix, stock;
	
	public InfoProduitAVendre(String email, String categorie, String nom, String prix, String stock){
		this.email = email;
		this.nom = nom;
		this.categorie = categorie;
		this.prix = prix;
		this.stock = stock;
	}

	public String toString(){
		String str;
		if(this.email != null && this.categorie != null && this.nom != null){
			str = "Description du produit que vous voulez vendre :\n\n";
			str += "Nom du vendeur : " + this.email + "\n";
			str += "Categorie du produit : " + this.categorie + "\n";
			str += "Nom du produit : " + this.nom + "\n";
			str += "Prix de revient : " + this.prix + " euro\n";
			str += "Quantite : " + this.stock + "\n";
		} else{
			str = "Veuillez saisir toutes les informations !";
		}
		return str;
	}

}
