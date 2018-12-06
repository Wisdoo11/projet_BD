package affichage;

import projet_BD.Requete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by herbrets on 12/3/18.
 */
public class ChoixCatProduitEnchere extends JDialog {

    private JComboBox<String> Categorie;
    private String email;
    private FenetreChoixProduitEnchere fenetreChoixProdEnchere;

    public ChoixCatProduitEnchere(JFrame parent, String title, boolean modal, String email){
        super(parent, title, modal);
        this.email = email;
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.initComponent();
    }

    public void afficher(){
        this.setVisible(true);
    }

    private void initComponent(){

        //Titre
        JPanel panel = new JPanel();
        //panel.setLayout(new FlowLayout());
        String labelText = "<html><P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><BR>\r\n" +
                "</P>\r\n" +
                "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B>Choissisez la catégorie du produit que vous voulez acheter : </B></FONT></P></HTML>";
        JLabel label = new JLabel(labelText);
        panel.add(label);
        setContentPane(panel);

        //La catégorie de la salle
        JPanel panCategorie = new JPanel();
        panCategorie.setBackground(Color.white);
        panCategorie.setPreferredSize(new Dimension(250, 30));

        Requete requete = new Requete("select distinct Produit1.nom_categorie from Vente1, Produit1 where Vente1.id_produit=Produit1.id_produit");
        ArrayList<String[]> selection = new ArrayList<String[]>();
        requete.getSelection(selection);

        Categorie = new JComboBox<String>();
        for (String[] elt : selection) {
            for (String i:elt) {
                Categorie.addItem(i);
            }
        }
        panCategorie.add(Categorie);

        JPanel content = new JPanel();
        content.setBackground(Color.white);
        content.add(panCategorie);


        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");

        okBouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
            	System.out.print("Voici les produits qui sont vendus: \n");
            	//affiche les produits à vendre avec leurs caractéritisques
            	System.out.println("id_produit, nom du produit, stock, caracteristique, description\n");
            	Requete requete = new Requete("Select Produit1.id_produit, Produit1.nom, Produit1.stock, Caracteristique1.nom, Caracteristique1.valeur\r\n" + 
            			"From Salle1, Vente1, Produit1, Caracteristique1\r\n" + 
            			"Where Salle1.nom_categorie='" + (String) Categorie.getSelectedItem() + "'\r\n" + 
            			"AND Produit1.nom_categorie='" + (String) Categorie.getSelectedItem() + "'\r\n" + 
            			"AND Vente1.id_salle=Salle1.id_salle\r\n" + 
            			"AND Caracteristique1.id_produit=Produit1.id_produit\r\n" +
            			"AND Vente1.id_produit=Produit1.id_produit\r\n" +
            			"UNION\r\n" +
            			"Select Produit1.id_produit, Produit1.nom, Produit1.stock, NULL, NULL\r\n" + 
            			"From Salle1, Vente1, Produit1\r\n" + 
            			"Where Salle1.nom_categorie='" + (String) Categorie.getSelectedItem() + "'\r\n" +
            			"AND Produit1.nom_categorie='" + (String) Categorie.getSelectedItem() + "'\r\n" + 
            			"AND Vente1.id_salle=Salle1.id_salle\r\n" + 
            			"AND Vente1.id_produit=Produit1.id_produit\r\n" +
            			"MINUS\r\n" +
            			"Select Produit1.id_produit, Produit1.nom, Produit1.stock, NULL, NULL\r\n" + 
            			"From Salle1, Vente1, Produit1, Caracteristique1\r\n" + 
            			"Where Salle1.nom_categorie='" + (String) Categorie.getSelectedItem() + "'\r\n" + 
            			"AND Produit1.nom_categorie='" + (String) Categorie.getSelectedItem() + "'\r\n" + 
            			"AND Vente1.id_salle=Salle1.id_salle\r\n" +
            			"AND Caracteristique1.id_produit=Produit1.id_produit\r\n" +
            			"AND Vente1.id_produit=Produit1.id_produit"
            			);
            	requete.execute();
            	fenetreChoixProdEnchere = new FenetreChoixProduitEnchere(null, "Choisissez le produit à acheter", true, (String) Categorie.getSelectedItem(), email);
            	fenetreChoixProdEnchere.afficher();
            	
            	setVisible(false);
            }
        });

        JButton cancelBouton = new JButton("Annuler");
        cancelBouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });

        control.add(okBouton);
        control.add(cancelBouton);

        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }


}
