package projet_BD;
import java.sql.*;
import java.util.ArrayList;

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
	  	    conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);

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
  	    conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
  	    
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
	
	public boolean premiereEnchereDeVente() {
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
        	System.out.println("Il n'y a pas encore d'enchères proposés pour cette vente");
        	bool = true;
        } else {
        	System.out.println("prix: ");
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
  	    conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
  	    
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
}
