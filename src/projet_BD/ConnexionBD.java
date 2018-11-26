package projet_BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ConnexionBD {
	
    static final String CONN_URL = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
    static final String USER = "herbrets";
    static final String PASSWD = "herbrets";

    public ConnexionBD() {}

    public Connection connexion() throws SQLException {
      try {
	    // Enregistrement du driver Oracle
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    // Etablissement de la connection
	    Connection conn = DriverManager.getConnection(this.CONN_URL, this.USER, this.PASSWD);
	    
	    return conn;
	    
      } catch (SQLException e) {
    	  
          System.err.println("failed");
          e.printStackTrace(System.err);
          throw new SQLException("ERREUR de connexion à la BD !");
      }
    }
    
    public void deconnexion(Connection conn) throws SQLException {
	    // Fermeture
    	conn.close();
    }

}
