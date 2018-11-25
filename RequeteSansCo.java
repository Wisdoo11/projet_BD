package projet_BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class RequeteSansCo {
	
	private Connection conn;
	private String preStmt; // "select * from Salle1";
	
	public RequeteSansCo(Connection conn, String preStmt) {
		this.conn = conn;
		this.preStmt = preStmt;		
	}
	
	public void execute() throws SQLException {
		
		// Creation de la requete
	    PreparedStatement stmt = conn.prepareStatement(this.preStmt);
		// Execution de la requete
	    ResultSet rset = stmt.executeQuery();
		// Affichage du resultat
	    System.out.println("Results:");
	    dumpResultSet(rset);

		// Fermeture
		rset.close();
	    stmt.close();
	}
	
	public void executeUpdateReq() throws SQLException {
		// Creation de la requete
	    PreparedStatement stmt = conn.prepareStatement(this.preStmt);
		// Execution de la requete
	    int rset = stmt.executeUpdate();
	    System.out.println("Nombre d'élèments ajoutés : " + rset); // TODO à modifier
		// Fermeture
	    stmt.close();
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
