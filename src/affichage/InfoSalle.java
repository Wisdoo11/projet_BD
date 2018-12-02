package affichage;

public class InfoSalle {
		
	private String categorie, idSalle;
	private int vente, libre, revocable, enchere;
		
	public InfoSalle(String idSalle, String categorie, int vente, int libre, int revocable, int enchere_multiple){
		this.idSalle = idSalle;
		this.categorie = categorie;
		this.vente = vente;
		this.libre = libre;
		this.revocable = revocable;
		this.enchere = enchere_multiple;
		}

	public String toString(){
		String str;
		str = "Description du de la nouvelle salle de vente :\n\n";
		str += "Le numéro de la salle : " + this.idSalle + "\n";
		str += "Categorie des produits : " + this.categorie + "\n";
		if (this.vente == 0) {
			str += "Type de vente : Descendante \n";
		} else {
			str += "Type de vente : Montante \n";
		}
		if (this.libre == 0) {
			str += "Type de durée des ventes : Durée limité \n";
		} else {
			str += "Type de durée des ventes : Durée libre \n";
		}
		if (this.revocable == 0) {
			str += "Révocable : Non \n";
		} else {
			str += "Révocable : Oui \n";
		}
		if (this.enchere == 0) {
			str += "Un même utilisateur peut enchérir plusieurs fois : Non \n";
		}
		else {
			str += "Un même utilisateur peut enchérir plusieurs fois : Oui \n";
		}
		str += "Retenez bien le NUMERO, il vous fera utile\n pour de prochaines transactions\n";
		return str;
	}

}

