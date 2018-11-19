import java.sql.*;

public class TestBddConnection {
  public static void main(String[] args) {
    Connection con = BddConnection.getConnection();
    try {

      String requete = "insert into Categorie1 values ('Pantoufles', 'Des pantoufles de toutes les sortes...')";
      //String requete = "select * from Produit1";
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(requete);
      // while (rs.next()) {
      //   System.out.println(rs.getString("nom") + "\t" + rs.getInt("prix_revient"));
      // }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
