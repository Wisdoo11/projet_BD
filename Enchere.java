import java.sql.*;

public class Enchere {
  private String emailUtilisateur;
  private int idVente;
  private float prixPropose;
  private String temps;
  private int quantite;


  public Enchere(String emailUtilisateur, int idVente, float prixPropose, String temps, int quantite) {
    this.emailUtilisateur = emailUtilisateur;
    this.idVente = idVente;
    this.prixPropose = prixPropose;
    this.temps = temps;
    this.quantite = quantite;

  }

  public void ajouter() {
    String connUrl = "jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1";
    String user = "herbrets";
    String passwd = "herbrets";

    String preStmt = "insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values ('" + this.emailUtilisateur + "', " + this.idVente + ", " + this.prixPropose + ", " + this.temps + ", " + this.quantite + ")";
    System.out.println(preStmt);
    Requete requete = new Requete(connUrl, user, passwd, preStmt);
    requete.execute();
  }
}
