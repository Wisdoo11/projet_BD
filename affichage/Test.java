package affichage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import projet_BD.Requete;

public class Test {
	
	public static String[][] conversion(ArrayList<String[]> listeUser)
	{
		String[][] liste = new String[listeUser.size()][listeUser.get(0).length];
		Iterator<String[]> it = listeUser.iterator();
		int i = 0;
		while (it.hasNext())
		{
			liste[i] = it.next();
			i++;
		}
		return liste;

	}

	public static void main(String[] args) throws SQLException {
		ArrayList<String[]> listeUser = new ArrayList<String[]>();
		
		Requete requete = new Requete("select email from Utilisateur1");
		//requete.execute();
		//System.out.println("OOOOOOOK \n");
		
		requete.getSelection(listeUser);
		String[][] liste = conversion(listeUser);
		for (String[] i:liste) {
			for (String j:i) {
				System.out.println(j);
			}
		}

	}

}
