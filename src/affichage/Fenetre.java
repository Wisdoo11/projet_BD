package affichage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Fenetre extends JDialog {
	
  private JButton bouton1 = new JButton("Mise en place salle des ventes");
  private JButton bouton2 = new JButton("Réaliser une enchère");
  private JButton bouton6 = new JButton("Ajouter des produits dans une salle de vente");
  private JButton bouton3 = new JButton("Fin de ventes");
  private JButton bouton4 = new JButton("Me déconnecter");
  private JButton bouton5 = new JButton("Ajouter une caractégorie de produit");

  public Fenetre(JFrame parent, String title, boolean modal) {
	super(parent, title, modal);
    this.setTitle("Enchères BDD");
    this.setSize(350, 400);
    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.getContentPane().setLayout(new FlowLayout());
    
    // texte de bienvenue
 	JPanel panel = new JPanel();
 	panel.setLayout(new FlowLayout());
 			   
 	// Texte de Bienvenue
 	String labelText = "<html><P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><BR>Que souhaitez-vous faire ?</P></HTML>";
 	JLabel label = new JLabel(labelText);
 	label.setLayout(new BoxLayout(label, BoxLayout.LINE_AXIS));
 	panel.add(label);
 	setContentPane(panel);
    
 	//organisation des boutons sur la fenêtre
    Box b1 = Box.createHorizontalBox();
    b1.add(bouton1);
    
 	Box b3 = Box.createHorizontalBox();
 	b3.add(bouton5);
 	
 	Box b5 = Box.createHorizontalBox();
 	b5.add(bouton6);
 	
 	Box b2 = Box.createHorizontalBox(); 
 	b2.add(bouton2);
 	b2.add(Box.createRigidArea(new Dimension(10,0)));
 	b2.add(bouton3);
 	
 	Box b4 = Box.createHorizontalBox();
 	b4.add(bouton4);
 	    
 	Box box = Box.createVerticalBox();
 	box.add(b1);
 	box.add(Box.createRigidArea(new Dimension(0,20)));
 	box.add(b5);
 	box.add(Box.createRigidArea(new Dimension(0,20)));
 	box.add(b3);
 	box.add(Box.createRigidArea(new Dimension(0,20)));
 	box.add(b2);
 	box.add(Box.createRigidArea(new Dimension(0,30)));
 	box.add(b4);
 	
 	this.getContentPane().add(box);
    
 	//mise en place des salles de vente
    bouton1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {
    	  ChoixCatProduit choix = new ChoixCatProduit(null, "Catégorie des produits à vendre", true, "admin", true);
    	  choix.afficher();
      }
    }
    );
    
    //ajouter une catégorie de produits
    bouton5.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0) {
      	  AjouterCategorie choix = new AjouterCategorie(null, "Catégorie des produits à vendre", true);
      	  choix.afficher();
        }
      }
      );
    
    //ajouter un produit à vendre dans une salle de vente
    bouton6.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0) {
        	//cette fenêtre affiche un menu déroulant des catégories de produits mis à la vente
        	FenetreChoixCatAjoutProdSalle choix = new FenetreChoixCatAjoutProdSalle(null, "Catégorie des produits à vendre", true, true);
      	  	choix.afficher();
        }
      }
      );
    
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
    
	bouton4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
		}
	});
    this.setVisible(true);
  }
  
  public static void main(String[] args) {
	  Fenetre fenetre = new Fenetre(null, "Que souhaitez-vous faire ?", true);
  }
}
