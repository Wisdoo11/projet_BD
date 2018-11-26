package affichage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import projet_BD.Requete;

/**
 * Cette classe vérifie si les idendifiants entrés correspondent bien à un utilisateur inscrit dans la base de données.
 * Si celui-ci n'est pas reconnu par le système, une fenêtre d'erreur apparaît et demande à l'utlisateur de rentrer à nouveau ses identifiants.
 */
public class ConfirmationCo {

	/* TODO vérfication dans la base de donnée des utlisateurs */
		
	// pour le test
	private String email;
	private boolean estAdmin;
	private boolean coReussie;
		 
	public ConfirmationCo(String email, boolean estAdmin){
		this.email = email;
		this.estAdmin = estAdmin;
	}
	
	public boolean estDansListe(ArrayList<String[]> liste, String chaine) {
		for (String[] i:liste) {
			for (String j:i) {
				if (j.equals(chaine)) {
					return true;
				}
			}
		}
		return false;
	}

	public String toString(){
		
		Requete requete = new Requete("select email from Utilisateur1");
		ArrayList<String[]> listeUser = new ArrayList<String[]>();
		requete.getSelection(listeUser);
		
		String str;
		if((estDansListe(listeUser, this.email)&& !estAdmin) || (this.email.equals("admin") && estAdmin)){
			str = "Connexion réussie !\n";
			str += "Email : " + this.email + "\n";
			coReussie = true;
		} else{
			str = "Connexion échouée !\nIdentifiant incorrect !";
			coReussie = false;
		}
		return str;
	}
	
	public boolean continuer(String email) {
		if (coReussie && estAdmin) {
			Fenetre fenetreAdmin = new Fenetre();
		} else if (coReussie && !estAdmin) {
			OptionUtilisateur fenetreUser = new OptionUtilisateur(null, "Bienvenue " + email, true, email);
			fenetreUser.afficher();
		}
		return coReussie;
	}

}
