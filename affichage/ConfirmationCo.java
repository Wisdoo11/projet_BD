package affichage;

import javax.swing.JOptionPane;

/**
 * Cette classe vérifie si les idendifiants entrés correspondent bien à un utilisateur inscrit dans la base de données.
 * Si celui-ci n'est pas reconnu par le système, une fenêtre d'erreur apparaît et demande à l'utlisateur de rentrer à nouveau ses identifiants.
 */
public class ConfirmationCo {

	/* TODO vérfication dans la base de donnée des utlisateurs */
		
	// pour le test
	private String email, pwd;
	private boolean estAdmin;
	private boolean coReussie;
		 
	public ConfirmationCo(String email, boolean estAdmin){
		this.email = email;
		this.estAdmin = estAdmin;
	}

	public String toString(){
		String str;
		if((this.email.equals("vilayvos") && !estAdmin) ||
				(this.email.equals("admin") && estAdmin)){
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
			OptionUtilisateur fenetreUser = new OptionUtilisateur(email);
		}
		return coReussie;
	}

}
