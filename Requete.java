import java.sql.*;

public class Requete {

    private String connUrl; // "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
    private String user; // "herbrets";
    private String passwd; // "herbrets";
    private String preStmt; // "select * from Salle1";

    public Requete(String connUrl, String user, String passwd, String preStmt) {
      this.connUrl = connUrl;
      this.user = user;
      this.passwd = passwd;
      this.preStmt = preStmt;
    }

    public void execute() {
      try {
	    // Enregistrement du driver Oracle
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

	    // Etablissement de la connection
	    Connection conn = DriverManager.getConnection(this.connUrl, this.user, this.passwd);

	    // Creation de la requete
      PreparedStatement stmt = conn.prepareStatement(this.preStmt);
	    // Execution de la requete
      ResultSet rset = stmt.executeQuery();
	    // Affichage du resultat
      System.out.println("Results:");
      //dumpResultSet(rset);
      System.out.println("");

	    // Fermeture
	    rset.close();
      stmt.close();
      conn.close();

      } catch (SQLException e) {
          System.err.println("failed");
          e.printStackTrace(System.err);
      }
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
}
