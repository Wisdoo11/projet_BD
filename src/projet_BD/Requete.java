package projet_BD;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Requete {
	
    static final String CONN_URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
    static final String USER = "herbrets";
    static final String PASSWD = "herbrets";
    
    private String preStmt;
    
    public Requete(String preStmt) {
    	this.preStmt = preStmt;
    }
    
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
    
	public void executeUpdateReq() throws SQLException {
		
		try {
	  	    // Enregistrement du driver Oracle
	  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

	  	    // Etablissement de la connection
	  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);

	  	    // Creation de la requete
	        PreparedStatement stmt = conn.prepareStatement(this.preStmt);
	  	    // Execution de la requete
		    int rset = stmt.executeUpdate();
		    System.out.println("Nombre d'élèments ajoutés : " + rset); // TODO à modifier

	  	    // Fermeture
	        stmt.close();
	        conn.close();

	        } catch (SQLException e) {
	            System.err.println("Modification non possible !");
	            //e.printStackTrace(System.err);
	        }
	}
	
	public String recupIdProduit(String categorie, String email, String produit) {
		String id = "";
        try {
  	    // Enregistrement du driver Oracle
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

  	    // Etablissement de la connection
  	    Connection conn = DriverManager.getConnection(Requete.CONN_URL, Requete.USER, Requete.PASSWD);

  	    // Creation de la requete
  	    
        PreparedStatement stmt = conn.prepareStatement("select max(id_produit) from Produit1 where "
        		+ "nom_categorie='" + categorie + "' and email='" + email + "' and nom='" + produit + "'"); // TODO il faut se déplacer en serializable !
  	    // Execution de la requete
        ResultSet rset = stmt.executeQuery();
        
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        rset.next();
        System.out.println(i);
        id = rset.getString(1);
        System.out.println(id);

  	    // Fermeture
  	    rset.close();
        stmt.close();
        conn.close();

        } catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace(System.err);
        }
        
        return id;
    }
	
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
            System.err.println("failed !");
            //e.printStackTrace(System.err);
        }	
    }
}
