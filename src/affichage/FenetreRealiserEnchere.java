package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreRealiserEnchere extends JDialog {
	
	private String email;
	private JLabel emailLabel, nomLabel, CategorieLabel;
	private JComboBox categorieBox, nomBox;
	private JTextField prixText, stockText;

	public FenetreRealiserEnchere(JFrame parent, String title, boolean modal, String email){
		super(parent, title, modal);
		this.email = email;
		this.setSize(600, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	public void afficher(){
		this.setVisible(true);
	}

	private void initComponent(){

		//email
		JPanel panEmail = new JPanel();
		panEmail.setBackground(Color.white);
    	panEmail.setPreferredSize(new Dimension(250, 80));
    	panEmail.setBorder(BorderFactory.createTitledBorder("Identité du vendeur"));
    	emailLabel = new JLabel("Votre identité : " + email);
    	panEmail.add(emailLabel);

    	//La catégorie du produit
    	JPanel panCategorie = new JPanel();
    	panCategorie.setBackground(Color.white);
    	panCategorie.setPreferredSize(new Dimension(250, 80));
    	panCategorie.setBorder(BorderFactory.createTitledBorder("Catégorie d'objets"));

    	//Requete requete = new Requete("jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1", "herbrets", "herbrets", "select nom from Categorie1");
    	//ArrayList<String> selection = requete.getSelection();

    	categorieBox = new JComboBox();
    	//for (String elt : selection) {
    	//	categorieBox.addItem(elt);
    	//}
    	categorieBox.addItem("Jouets"); // pour le test
    	categorieBox.addItem("Vêtements"); // pour le test
    	categorieBox.addItem("Electroménager"); // pour le test
    	CategorieLabel = new JLabel("Catégorie : ");
    	panCategorie.add(CategorieLabel);
    	panCategorie.add(categorieBox);

    	//Le nom du produit
    	JPanel panNom = new JPanel();
    	panNom.setBackground(Color.white);
    	panNom.setPreferredSize(new Dimension(250, 80));
    	panNom.setBorder(BorderFactory.createTitledBorder("Le(s) produit(s)"));
    	nomBox = new JComboBox();
    	
    	//String catSelectionne = (String) categorieBox.getSelectedItem();

    	//Requete requete2 = new Requete("jdbc:oracle:thin:@ensioracle1.imag.fr:1521:ensioracle1", "herbrets", "herbrets", "select Produit1.nom from Produit1 where Produit1.nom_categorie = " + catSelectionne);
    	//ArrayList<String> selectionCategorie = requete2.getSelection();

    	nomBox = new JComboBox();
    	//for (String elt : selectionCategorie) {
    	//	nomBox.addItem(elt);
    	//}


    	nomLabel = new JLabel("Nom du produit");
    	panNom.add(nomLabel);
    	panNom.add(nomBox);

    	JPanel content = new JPanel();
    	content.setBackground(Color.white);
    	content.add(panEmail);
    	content.add(panCategorie);
    	content.add(panNom);

    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");

    	okBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			// TODO lui afficher les salles disponibles
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
