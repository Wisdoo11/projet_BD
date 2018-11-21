import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Fenetre extends JFrame {
  private JButton bouton1 = new JButton("Mise en place salle des ventes");
  private JButton bouton2 = new JButton("Réaliser une enchère");
  private JButton bouton3 = new JButton("Fin de ventes");


  public Fenetre(){
    this.setTitle("Enchères BDD");
    this.setSize(350, 100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.getContentPane().setLayout(new FlowLayout());
    this.getContentPane().add(bouton1);
    this.getContentPane().add(bouton2);
    this.getContentPane().add(bouton3);
    bouton1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {
        SalleDialog zd = new SalleDialog(null, "Salle de ventes", true);
        ZDialogInfo zInfo = zd.showZDialog();
        JOptionPane jop = new JOptionPane();
        jop.showMessageDialog(null, zInfo.toString(), "Informations Salle de Ventes", JOptionPane.INFORMATION_MESSAGE);
      }
    });
    // bouton2.addActionListener(new ActionListener(){
    //   public void actionPerformed(ActionEvent arg0) {
    //     SalleDialog zd = new ZDialog(null, "Enchère", true);
    //     ZDialogInfo zInfo = zd.showZDialog();
    //     JOptionPane jop = new JOptionPane();
    //     jop.showMessageDialog(null, zInfo.toString(), "Informations personnage", JOptionPane.INFORMATION_MESSAGE);
    //   }
    // });
    // bouton3.addActionListener(new ActionListener(){
    //   public void actionPerformed(ActionEvent arg0) {
    //     ZDialog zd = new ZDialog(null, "Ventes", true);
    //     ZDialogInfo zInfo = zd.showZDialog();
    //     JOptionPane jop = new JOptionPane();
    //     jop.showMessageDialog(null, zInfo.toString(), "Informations personnage", JOptionPane.INFORMATION_MESSAGE);
    //   }
    // });
    this.setVisible(true);
  }

  public static void main(String[] main){
    Fenetre fen = new Fenetre();
  }
}
