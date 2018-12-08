package projet_BD;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import affichage.FenetreRealiserEnchere;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Requete {
	
    static final String CONN_URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
    static final String USER = "herbrets";
    static final String PASSWD = "herbrets";
    
    private String preStmt;
    
    public Requete(String preStmt) {
    	this.preStmt = preStmt;
    }
    
    /**
     * Cette méthode permet d'exécuter des requêtes de type SELECT uniquement
     */
    public void execute() {
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);

  	    // Creation de la requete
        PreparedStatement stmt = conn.prepareStatement(this.preStmt);
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
  	    // Affichage du resultat
        System.out.println("Results: ");
        dumpResultSet(rset);

  	    // Fermeture
  	    rset.close();
        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
    }
    
    /**
     * Cette méthode exécute des requêtes de type insert, update, delete
     */
	public void executeUpdateReq() {
		
		try {
	  	    // Enregistrement du driver Oracle
	  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

	  	    // Etablissement de la connection
	  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);
	  	    
	  	    // Demarrage de la transaction
	  	    conn.setAutoCommit(false);
	  	    conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);

	  	    // Creation de la requete
	        PreparedStatement stmt = conn.prepareStatement(this.preStmt);
	  	    // Execution de la requete
		    int rset = stmt.executeUpdate();
		    System.out.println("Nombre d'élèments modifiés : " + rset);

	  	    // Terminaison de la transaction
	  	    conn.commit();
		    
	        stmt.close();
	        conn.close();

	        } catch (SQLException e) {
	            System.err.println("Modification non possible !");
	            e.printStackTrace(System.err);
	        }
	}
	
	/**
	 * Cette méthode exécute une transaction de type select id et récupère cet id
	 * @param categorie
	 * @param email
	 * @param produit
	 * @return
	 */
	public String recupIdProduit(String categorie, String email, String produit) {
		String id = "";
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);
  	    
  	    // Demarrage de la transaction
  	    conn.setAutoCommit(false);
  	    conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
  	    
  	    // Creation de la requete
        PreparedStatement stmt1 = conn.prepareStatement(this.preStmt);
  	    // Execution de la requete
	    int rset1 = stmt1.executeUpdate();
	    System.out.println("Nombre d'élèments modifiés : " + rset1);

  	    // Creation de la requete qui récupère l'indice du produit
        PreparedStatement stmt = conn.prepareStatement("select max(id_produit) from Produit1 where "
        		+ "nom_categorie='" + categorie + "' and email='" + email + "' and nom='" + produit + "'");
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
        
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        rset.next();
        System.out.println("Nombre d'élèments récupéré : " + i);
        id = rset.getString(1);
        System.out.println("id_produit = " + id);

  	    // Fermeture
  	    rset.close();
  	    
  	    // Terminaison de la transaction
  	    conn.commit();
  	    
  	    stmt1.close();
        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
        
        return id;
    }
	
	public String recupIdSalle(String categorie, int vente, int libre, int revocable, int enchere) {
		String id = "";
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);

  	    // Demarrage de la transaction
  	    conn.setAutoCommit(false);
  	    conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
  	    
  	    // Creation de la requete principale
        PreparedStatement stmt1 = conn.prepareStatement(this.preStmt);
  	    // Execution de la requete
	    int rset1 = stmt1.executeUpdate();
	    System.out.println("Nombre d'élèments modifiés : " + rset1);

  	    
  	    // Creation de la requete
  	    
        PreparedStatement stmt = conn.prepareStatement("select max(id_salle) from Salle1 where "
        		+ "nom_categorie='" + categorie + "' and type_vente=" + vente + " and est_libre=" + libre + 
        		" and est_revocable=" + revocable + " and enchere_multiple=" + enchere);
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
        
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        rset.next();
        System.out.println(i);
        id = rset.getString(1);
        System.out.println("Le numéro de la salle est : " + id);

  	    // Fermeture
  	    rset.close();
  	    
  	    // Terminaison de la transaction
  	    conn.commit();
  	    
  	    stmt1.close();
        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
        
        return id;
    }
	
	/**
	 * Cette méthode renvoie l'idSalle
	 * @return
	 */
	public int recupIdSalle1() {
		int id = -1;
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);

  	    // Demarrage de la transaction
  	    conn.setAutoCommit(false);
  	    conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);

  	    // Creation de la requete
        PreparedStatement stmt = conn.prepareStatement(this.preStmt);
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
        
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        rset.next();
        System.out.println(i);
        id = rset.getInt("id_salle");
        System.out.println("Le numéro de la salle est : " + id);

  	    // Fermeture
  	    rset.close();
  	    
  	    // Terminaison de la transaction
  	    conn.commit();

        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
        
        return id;
    }
	
	/**
	 * Cette méthode renvoie l'idSalle
	 * @return
	 */
	public int recupIdProduit() {
		int id = -1;
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);

  	    // Demarrage de la transaction
  	    conn.setAutoCommit(false);
  	    conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);

  	    // Creation de la requete
        PreparedStatement stmt = conn.prepareStatement(this.preStmt);
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
        
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        rset.next();
        System.out.println(i);
        id = rset.getInt("id_produit");
        System.out.println("L'identifiant du produit est : " + id);

  	    // Fermeture
  	    rset.close();
  	    
  	    // Terminaison de la transaction
  	    conn.commit();

        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
        
        return id;
    }
	
	/**
	 * Cette méthode renvoie les stocks restant d'un produit
	 * @return
	 */
	public int recupStockProduit() {
		int id = -1;
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);

  	    // Demarrage de la transaction
  	    conn.setAutoCommit(false);
  	    conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);

  	    // Creation de la requete
        PreparedStatement stmt = conn.prepareStatement(this.preStmt);
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
        
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        rset.next();
        System.out.println(i);
        id = rset.getInt("stock");
        System.out.println("Il reste : " + id + " produit(s)");

  	    // Fermeture
  	    rset.close();
  	    
  	    // Terminaison de la transaction
  	    conn.commit();

        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
        
        return id;
    }
	
	/**
	 * Cette méthode vérifie si une vente a déjà eu une enchère
	 * @return
	 */
	public boolean premiereEnchereDeVente() {
		boolean bool = false;
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);
  	    
  	    // Demarrage de la transaction
  	    conn.setAutoCommit(false);
  	    conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
  	    
  	    // Creation de la requete
        PreparedStatement stmt = conn.prepareStatement(this.preStmt);
        
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
        
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        rset.next();
        String nb = rset.getString(1);;
        System.out.println("nb résultats : " +nb);
        
        if (nb.equals("0")) { //si pas d'enchère
        	System.out.println("Il n'y a pas encore d'enchères proposés pour cette vente");
        	bool = true;
        } else {
        	System.out.println("Présences d'enchères !");
        }
  	    
  	    // Terminaison de la transaction
  	    conn.commit();
  	    
        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
		return bool;
	}
	
	/**
	 * Cette méthode récupère le prix courante d'une vente en cours
	 * Si aucune enchère n'a été effectuée, alors renvoie 0
	 * @return
	 */
	public String recupPrixDepartEnchere() {
		String prix = "";
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);
  	    
  	    // Demarrage de la transaction
  	    conn.setAutoCommit(false);
  	    conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
  	    
  	    // Creation de la requete
        PreparedStatement stmt = conn.prepareStatement(this.preStmt);
        
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
        
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        rset.next();
        System.out.println("Nombre de colonnes récupéré : " + i);
        
        prix = rset.getString(1);

  	    // Fermeture
  	    rset.close();
  	    
  	    // Terminaison de la transaction
  	    conn.commit();
  	    
        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
        
        return prix;
    }
	
	/**
	 * Cette méthode récupère le prix courant d'une vente aux enchères
	 * @return
	 */
	public float recupPrixCourantEnchere() {
		float prix = -1;
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);
  	    
  	    // Demarrage de la transaction
  	    conn.setAutoCommit(false);
  	    conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
  	    
  	    // Creation de la requete
        PreparedStatement stmt = conn.prepareStatement(this.preStmt);
        
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
        
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        rset.next();
        System.out.println("Nombre de colonnes récupéré : " + i);
        
        prix = rset.getFloat("prix_propose")/rset.getInt("quantite");

  	    // Fermeture
  	    rset.close();
  	    
  	    // Terminaison de la transaction
  	    conn.commit();
  	    
        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
        
        return prix;
    }
	
	/**
	 * Cette méthode récupère les infos type vente, type duree, revocabilité, multiplicité des enchères d'une vente
	 * @return
	 */
	public int[] recupTypeSalle() {
		int[] info = {-1,-1,-1,-1};
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);
  	    
  	    // Demarrage de la transaction
  	    conn.setAutoCommit(false);
  	    conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
  	    
  	    // Creation de la requete
        PreparedStatement stmt = conn.prepareStatement(this.preStmt);
        
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
        
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        rset.next();
        System.out.println("Nombre de colonnes récupéré : " + i);
        
        info[0] = rset.getInt("type_vente");
        info[1] = rset.getInt("est_libre");
        info[2] = rset.getInt("est_revocable");
        info[3] = rset.getInt("enchere_multiple");
        

  	    // Fermeture
  	    rset.close();
  	    
  	    // Terminaison de la transaction
  	    conn.commit();
  	    
        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
        
        return info;
    }
	
	/**
	 * Cette méthode récupère la date et l'heure d'une enchère
	 * @param categorie
	 * @param vente
	 * @param libre
	 * @param revocable
	 * @param enchere
	 * @return
	 */
	public Calendar recupDateEnchere() {
		Calendar id = Calendar.getInstance();
		id.set(0, 0, 0, 0, 0, 0);
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);

  	    // Demarrage de la transaction
  	    conn.setAutoCommit(false);
  	    conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
  	    
  	    // Creation de la requete principale
        PreparedStatement stmt = conn.prepareStatement(this.preStmt);
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
        
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        rset.next();
        String[] date = rset.getString("temps").split(" ")[0].split("-");
        String[] heure = rset.getString("temps").split(" ")[1].split(":");
        int seconde = (int) Float.parseFloat(heure[2]);
        id.set(Integer.parseInt(date[0]), Integer.parseInt(date[1])-1, Integer.parseInt(date[2]), 
        		Integer.parseInt(heure[0]), Integer.parseInt(heure[1]), seconde);

  	    // Fermeture
  	    rset.close();
  	    
  	    // Terminaison de la transaction
  	    conn.commit();

        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
        
        return id;
    }
	
	
	/**
	 * Cette méthode affiche les réponses d'une reqête SQL
	 * @param rset le résultat de la requête
	 * @throws SQLException
	 */
    private void dumpResultSet(ResultSet rset) throws SQLException {
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        while (rset.next()) {
            for (int j = 1; j <= i; j++) {
                System.out.print(rset.getString(j) + "\t");
	           }
	      System.out.println();
        }
    }
    
	public boolean aDejaEncherir() {
		boolean bool = false;
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);
  	    
  	    // Demarrage de la transaction
  	    conn.setAutoCommit(false);
  	    conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
  	    
  	    // Creation de la requete
        PreparedStatement stmt = conn.prepareStatement(this.preStmt);
        
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
        
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        rset.next();
        String nb = rset.getString(1);;
        System.out.println("nb résultats : " +nb);
        
        if (nb.equals("0")) { //si pas d'enchère
        	System.out.println("L'utilisateur n'a pas encore enchéri");
        	bool = false;
        } else {
        	System.out.println("L'utilisateur a déjà enchéri");
        	bool = true;
        }
  	    
  	    // Terminaison de la transaction
  	    conn.commit();
  	    
        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
		return bool;
	}
    
    
    
    
    /**
     * Cette méthode permet de récupérer les réponses d'une requête SQL sous la forme d'une ArrayList
     * @param selection prend en argument une ArrayList vide
     */
    public void getSelection(ArrayList<String[]> selection) {
    	
    	try {
      	    // Enregistrement du driver Oracle
      	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
      	    // Etablissement de la connection
      	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);

      	    // Creation de la requete
            PreparedStatement stmt = conn.prepareStatement(this.preStmt);
      	    // Execution de la requete
            ResultSet rset = stmt.executeQuery();
            
            ResultSetMetaData rsetmd = rset.getMetaData();
            int i = rsetmd.getColumnCount();
            //System.out.println(i);
            int ligne = 0;
            
            while (rset.next()) {
                String[] tab = new String[i];
                for (int j = 1; j <= i; j++) {
                    tab[j-1] = rset.getString(j);
    	           }
                selection.add(tab);
                ligne++;
            }

      	    // Fermeture
      	    rset.close();
            stmt.close();
            conn.close();
            
    	} catch (SQLException e) {
            System.err.println("Nous ne parvenons pas à récupérer les réponses de la requête !");
            //e.printStackTrace(System.err);
        }	
    }
    
    /**
     * Cette méthode réalise la transaction pour une enchère
     * @param fenetre
     * @param email
     * @param idSalle
     * @param idVente
     * @param idProduit
     * @param prixProposeText
     * @param quantiteBox
     */
    public void transactionRealiserEnchere(FenetreRealiserEnchere fenetre, String email, String idSalle, String idVente, String idProduit,
    		JTextField prixProposeText, JComboBox quantiteBox ) {
    	
    	try {
      	    // Enregistrement du driver Oracle
      	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

      	    // Etablissement de la connection
      	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);

      	    // Demarrage de la transaction
      	    conn.setAutoCommit(false);
      	    conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
      	    
      	    //pour récuperer le prix courant de l'enchère
			Float prixCourant;
			
			String requete000 = "select COUNT(*) from Enchere1 where id_vente=" + idVente;
	  	    // Creation de la requete
	        PreparedStatement stmt000 = conn.prepareStatement(requete000);
	  	    // Execution de la requete
		    int rset000 = stmt000.executeUpdate();
		    System.out.println("Nombre d'élèments modifiés : " + rset000);
		    
	        stmt000.close();
	        
			//si pas d'enchère on récupère le prix de départ
			if(rset000==0) {
				String requete00b = "select prix_depart from Vente1 where id_vente=" + idVente;
		  	    // Creation de la requete
		        PreparedStatement stmt00b = conn.prepareStatement(requete000);
		  	    // Execution de la requete
			    ResultSet rset00b = stmt00b.executeQuery();
				prixCourant = rset00b.getFloat("prix_depart");
				System.out.println("Le prix de départ est : " + prixCourant + "euros");
			} else {
    			String preStmt0 = "select prix_propose, quantite, temps\r\n"
    					+ "From Enchere1\r\n"
    					+ "Where id_vente=" + idVente + "\r\n"
    					+ "Group by id_enchere, email, id_vente, prix_propose, temps, quantite\r\n"
    					+ "HAVING temps=max(temps)"
    					+ "order by temps desc";
    			
          	    // Creation de la requete
                PreparedStatement stmt0 = conn.prepareStatement(preStmt0);
          	    // Execution de la requete
                ResultSet rset = stmt0.executeQuery();
                
                ResultSetMetaData rsetmd = rset.getMetaData();
                int i = rsetmd.getColumnCount();
                rset.next();
                System.out.println("Nombre de colonnes récupéré : " + i);
                
                prixCourant = rset.getFloat("prix_propose")/rset.getInt("quantite");

          	    // Fermeture
          	    rset.close();
          	    System.out.println("ici ?");
			}

			JOptionPane.showMessageDialog(null, "Le prix de départ est : " + prixCourant + "euros", " ", JOptionPane.INFORMATION_MESSAGE);
      	    
   	    
        	int[] info;
    		int type, libre, revocable, enchereM; //ici on ne gère pas la fin des enchères :
    		Requete requeteTypeVente = new Requete("select type_vente, est_libre, est_revocable, enchere_multiple\r\n"
    				+ "From Salle1\r\n"
    				+ "Where id_salle=" + idSalle);
    		info = requeteTypeVente.recupTypeSalle();
    		
    		//si vente montante
    		if (info[0]==1) { //si montante
    			System.out.println("La vente est montante !");
    			if (info[3]==1) { //possiblité pour un même utilisateur d'enchérir plusieurs fois
    				System.out.println("Encheres multiples possible !");
    				
    				if (Float.parseFloat(prixProposeText.getText().toString())
    						/ Float.parseFloat(quantiteBox.getSelectedItem().toString()) <= prixCourant) {
    					JOptionPane.showMessageDialog(null, "Le prix de départ est : " + prixCourant + "euros"
    									+ "\nVeuillez rentrer un prix supérieur à celui-ci",
    									" ", JOptionPane.INFORMATION_MESSAGE);
    				} else {
    					if (info[1]==0) {//si vente a duree limitee
    						
    							//on récupère la date et l'heure de fin de vente
    							Requete requeteDate0 = new Requete("select distinct Vente1.temps\r\n"
        	                            + "From Vente1\r\n"
        	                            + "And Vente1.id_vente=" + idVente);
    							requeteDate0.execute();
    							Calendar dateCouranteEnchere0 = requeteDate0.recupDateEnchere();
    							System.out.println(dateCouranteEnchere0.getTime());
    							
    							Calendar dateCourante0 = Calendar.getInstance();
    							System.out.println(dateCourante0.getTime());
    							
    							if (dateCourante0.compareTo(dateCouranteEnchere0) > 0) { //l'enchère est finie
    								System.out.println("Cette vente est terminée");
    	    						JOptionPane.showMessageDialog(null, "Cette vente est terminée !", " ", JOptionPane.INFORMATION_MESSAGE);
    	    						
    							} else {
    								// on ajoute l'enchère dans la table Enchere1
    								String preStmt = "insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values('"
    										+ email + "', " + idVente + ", " + prixProposeText.getText().toString()
    										+ ", CURRENT_TIMESTAMP" + ", " + quantiteBox.getSelectedItem().toString() + ")";
    								Requete requete = new Requete(preStmt);
    								requete.executeUpdateReq();

    								System.out.println(preStmt);
    							}
    							fenetre.setVisible(false);
    						
    					} else {//si vente a duree libre

    						//on vérifie si la vente a reçu une enchère
    						Requete requete00 = new Requete("select COUNT(*) from Enchere1 where id_vente=" + idVente);
    		    			requete00.execute();
    		    			
    		    			if (requete00.premiereEnchereDeVente()) { //si pas d'enchère
    		    				
    								// on ajoute l'enchère dans la table Enchere1
    								String preStmt = "insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values('"
    										+ email + "', " + idVente + ", " + prixProposeText.getText().toString()
    										+ ", CURRENT_TIMESTAMP" + ", " + quantiteBox.getSelectedItem().toString() + ")";
    								Requete requete = new Requete(preStmt);
    								requete.executeUpdateReq();

    								System.out.println(preStmt);
    							
    							fenetre.setVisible(false);
    		    			} else {//si des enchères existent déjà
    						
    		    				//on récupère la date de la dernière enchère acceptee
    		    				Requete requeteDate = new Requete("select distinct temps\r\n"
    		    						+ "From Enchere1\r\n"
    		    						+ "Where id_vente=" + idVente + "\r\n"
    		    						+ "Group by temps\r\n"
    		    						+ "Having temps = MAX(temps)\r\n"
    		    						+ "Order by temps desc");
    		    				requeteDate.execute();
    		    				Calendar dateCouranteEnchere = requeteDate.recupDateEnchere();
    		    				dateCouranteEnchere.add(Calendar.MINUTE, 1);//on incrémente de 10 min
    		    				// pour une vente à durée limitée : 2 offres sont espacées d'au plus 10min
    						
    		    				Calendar dateCourante = Calendar.getInstance();
    		    				System.out.println(dateCourante.getTime());
    						
    		    				if (dateCourante.compareTo(dateCouranteEnchere) >= 0) { //l'enchère est finie
    		    					System.out.println("Cette vente est terminée");
    		    					JOptionPane.showMessageDialog(null, "Cette vente est terminée !", " ", JOptionPane.INFORMATION_MESSAGE);
    		    				} else {
    		    					// on ajoute l'enchère dans la table Enchere1
    								String preStmt = "insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values('"
    										+ email + "', " + idVente + ", " + prixProposeText.getText().toString()
    										+ ", CURRENT_TIMESTAMP" + ", " + quantiteBox.getSelectedItem().toString() + ")";
    								Requete requete = new Requete(preStmt);
    								requete.executeUpdateReq();

    								System.out.println(preStmt);
    		    				}
    		    				fenetre.setVisible(false);
    		    			}	
    					}
    				}
    			} else {//ne peut pas encherir plusieurs fois
    				System.out.println("Encheres multiples non possible !");
    				Requete requeteEnchereM = new Requete("select COUNT(*)\r\n"
    						+ "From Enchere1\r\n"
    						+ "Where id_vente=" + idVente + "\r\n"
    						+ "AND email='" + email + "'");
    				boolean aDejaEncheri = requeteEnchereM.aDejaEncherir();
    				if (aDejaEncheri) {
    					JOptionPane.showMessageDialog(null, "Encheres multiples non possible !\n Vous avez déjà enchéri !", " ", JOptionPane.INFORMATION_MESSAGE);
    				} else {
    					if (Float.parseFloat(prixProposeText.getText().toString())
    							/ Float.parseFloat(quantiteBox.getSelectedItem().toString()) <= prixCourant) {//vérifie que le prix proposé unitaire > prix courant
    						JOptionPane.showMessageDialog(null,
    								"Le prix de départ est : " + prixCourant + "euros"
    										+ "\nVeuillez rentrer un prix supérieur à celui-ci",
    								" ", JOptionPane.INFORMATION_MESSAGE);
    					} else {
    						// on ajoute l'enchère dans la table Enchere1
    						String preStmt = "insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values('"
    								+ email + "', " + idVente + ", " + prixProposeText.getText().toString()
    								+ ", CURRENT_TIMESTAMP" + ", " + quantiteBox.getSelectedItem().toString() + ")";
    						Requete requete = new Requete(preStmt);
    						requete.executeUpdateReq();

    						System.out.println(preStmt);
    						
    						fenetre.setVisible(false);
    					}
    				}
    			}

    		} else {//la vente est descendante
    			System.out.println("La vente est descendante !");
    			
    			if (Float.parseFloat(prixProposeText.getText().toString())
    					/ Float.parseFloat(quantiteBox.getSelectedItem().toString()) != prixCourant) {
    				JOptionPane.showMessageDialog(null, "Vous acceptez le prix unitaire : " + prixCourant + " et achetez " + 
    						quantiteBox.getSelectedItem().toString() + " produits !", " ", JOptionPane.INFORMATION_MESSAGE);
    				
    				// on ajoute l'enchère dans la table Enchere1
    				String preStmt = "insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values('"
    						+ email + "', " + idVente + ", " + prixCourant*Integer.parseInt(quantiteBox.getSelectedItem().toString())
    						+ ", CURRENT_TIMESTAMP" + ", " + quantiteBox.getSelectedItem().toString() + ")";
    				Requete requete = new Requete(preStmt);
    				requete.executeUpdateReq();

    				// on met à jour la quantité de la vente
    				String preStmt1 = "update Produit1 set stock = stock - " + quantiteBox.getSelectedItem().toString() + " where id_produit = " +
    						idProduit;
    				Requete requete1 = new Requete(preStmt1);
    				requete1.executeUpdateReq();

    				System.out.println(preStmt1);
    				
    			} else {
    				if (info[1]==0) {//si vente a duree limitee
    					
    						//on récupère la date et l'heure de fin de vente
    						Requete requeteDate1 = new Requete("select distinct Vente1.temps\r\n"
    	                            + "From Vente1\r\n"
    	                            + "And Vente1.id_vente=" + idVente);
    						requeteDate1.execute();
    						Calendar dateCouranteEnchere1 = requeteDate1.recupDateEnchere();
    						System.out.println(dateCouranteEnchere1.getTime());
    						
    						Calendar dateCourante1 = Calendar.getInstance();
    						System.out.println(dateCourante1.getTime());
    						
    						if (dateCourante1.compareTo(dateCouranteEnchere1) >= 0) { //l'enchère est finie
    							System.out.println("Cette vente est terminée");
        						JOptionPane.showMessageDialog(null, "Cette vente est terminée !", " ", JOptionPane.INFORMATION_MESSAGE);
        						
    						} else {   								
    							
    							// on ajoute l'enchère dans la table Enchere1
    							String preStmt = "insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values('"
    									+ email + "', " + idVente + ", " + prixProposeText.getText().toString()
    									+ ", CURRENT_TIMESTAMP" + ", " + quantiteBox.getSelectedItem().toString() + ")";
    							Requete requete = new Requete(preStmt);
    							requete.executeUpdateReq();

    							// on met à jour la quantité de la vente
    							String preStmt1 = "update Produit1 set stock = stock - " + quantiteBox.getSelectedItem().toString() + " where id_produit = " +
    									idProduit;
    							Requete requete1 = new Requete(preStmt1);
    							requete1.executeUpdateReq();

    							System.out.println(preStmt1);
    							
    						}
    						fenetre.setVisible(false);
    					
    				} else {//si vente a duree libre
    						
    					// on ajoute l'enchère dans la table Enchere1
    					String preStmt = "insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values('"
    							+ email + "', " + idVente + ", " + prixProposeText.getText().toString()
    							+ ", CURRENT_TIMESTAMP" + ", " + quantiteBox.getSelectedItem().toString() + ")";
    					Requete requete = new Requete(preStmt);
    					requete.executeUpdateReq();

    					// on met à jour la quantité de la vente
    					String preStmt1 = "update Produit1 set stock = stock - " + quantiteBox.getSelectedItem().toString() + " where id_produit = " +
    							idProduit;
    					Requete requete1 = new Requete(preStmt1);
    					requete1.executeUpdateReq();

    					System.out.println(preStmt1);
    						
    					fenetre.setVisible(false);
    	    		} 
    			}
    			

    		}
    		
    		conn.setSavepoint("par précaution");
      	    // Terminaison de la transaction
      	    conn.commit();
      	    conn.close();


            } catch (SQLException e) {
                System.err.println("failed !");
                e.printStackTrace(System.err);
            }
    }
}
