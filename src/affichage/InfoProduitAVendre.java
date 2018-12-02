package affichage;

import javax.swing.JDialog;

public class InfoProduitAVendre {
	
	private String email, categorie, nom, prix, stock, idProduit;
	
	public InfoProduitAVendre(String email, String categorie, String nom, String prix, String stock, String id){
		this.email = email;
		this.categorie = categorie;
		this.nom = nom;
		this.prix = prix;
		this.stock = stock;
		this.idProduit = id;
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
			str += "NUMERO PRODUIT : " + this.idProduit  + "\n\n";
			str += "Retenez bien le NUMERO, il vous fera utile\n pour de prochaines transactions\n";
		} else{
			str = "Veuillez saisir toutes les informations !";
		}
		return str;
	}

}
