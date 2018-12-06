package affichage;

import java.util.ArrayList;

public class ListeProduit {
	
	private String cat;
	
	public ListeProduit(String cat) {
		this.cat = cat;
	}
	
	public ArrayList<String> afficheListe() {
		ArrayList<String> liste = new ArrayList<String>();
		if (cat.equals("Informatique")) {
			liste.add("Ordinateur bureau");
			liste.add("Ordinateur portable");
			liste.add("Souris");
			liste.add("Clavier");
			liste.add("Ecran");
		} else if (cat.equals("Gaming")) {
			liste.add("Ordinateur bureau Gaming");
			liste.add("Ordinateur portable Gaming");
			liste.add("Souris Gaming");
			liste.add("Clavier Gaming");
			liste.add("Ecran Gaming");
		} else if (cat.equals("Electromenager")) {
			liste.add("Cafetière");
			liste.add("Micro-onde");
			liste.add("Four");
			liste.add("Boulloire");
			liste.add("Ecran");
		} else if (cat.equals("Jouets")) {
			liste.add("Poupée");
			liste.add("Peluche");
			liste.add("Dinette");
		} else if (cat.equals("Meuble")) {
			liste.add("Commode");
			liste.add("Armoire");
			liste.add("Table basse");
			liste.add("Table à manger");
			liste.add("Chaise");
		} else if (cat.equals("Vetements")) {
			liste.add("Chaussettes");
			liste.add("Chemise");
			liste.add("Pull");
			liste.add("Pantalon");
			liste.add("Jeans");
		} else if (cat.equals("Telephonie")) {
			liste.add("Smartphone");
			liste.add("Téléphone classique");
			liste.add("Ecouteur");
			liste.add("Batterie portable");
		} else if (cat.equals("Tv_son_photo")) {
			liste.add("Télévision");
			liste.add("Enceinte");
			liste.add("Appareil photo");
		}
		
		return liste;
	}

}
