package affichage;

public class ZDialogInfo {
  
	private String nom, categorie, produit, vente, type, prix;
 
	public ZDialogInfo(){}
  
	public ZDialogInfo(String nom, String categorie, String produit, String vente, String type, String prix){
		this.nom = nom;
		this.categorie = categorie;
		this.produit = produit;
		this.type = type;
		this.vente = vente;
		this.prix = prix;
	}

	public String toString(){
		String str;
		if(this.nom != null && this.categorie != null && this.produit != null && this.type != null){
			str = "Description de la salle de vente\n";
			str += "Nom : " + this.nom + "\n";
			str += "Categorie : " + this.categorie + "\n";
			str += "Nombre de ventes : " + this.vente + "\n";
			str += "Type de vente : " + this.type + "\n";
			str += "produit(s) : " + this.produit + " à " + this.prix + " euro\n";
		} else{
			str = "Aucune information !";
		}
		return str;
	}
}
