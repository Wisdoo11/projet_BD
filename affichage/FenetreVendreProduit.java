package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreVendreProduit extends JDialog{
	
	private String email;
	private JLabel emailLabel, nomLabel, prixLabel, CategorieLabel, prix2Label;
	private JComboBox categorieBox, nomBox;
	private JTextField prixText, stockText;
	private ConfirmationVente confirmation;
	private InfoProduitAVendre info;

	public FenetreVendreProduit(JFrame parent, String title, boolean modal, String email){
		super(parent, title, modal);
		this.email = email;
		this.setSize(600, 400);
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

    	nomBox.addItem("chaussettes"); // pour le test
    	nomBox.addItem("pantoufles"); // pour le test
    	nomBox.addItem("mocassins"); // pour le test
    	nomBox.addItem("savates"); // pour le test
    	nomLabel = new JLabel("Nom du produit");
    	panNom.add(nomLabel);
    	panNom.add(nomBox);

    	//Le prix de revient
    	JPanel panPrix = new JPanel();
    	panPrix.setBackground(Color.white);
    	panPrix.setPreferredSize(new Dimension(250, 80));
    	panPrix.setBorder(BorderFactory.createTitledBorder("Prix de revient :"));
    	prixLabel = new JLabel("Prix : ");
    	prix2Label = new JLabel(" euro");
    	prixText = new JTextField();
    	prixText.setPreferredSize(new Dimension(90, 25));
    	panPrix.add(prixLabel);
    	panPrix.add(prixText);
    	panPrix.add(prix2Label);
    	
    	//le stock
    	JPanel panStock = new JPanel();
    	panStock.setBackground(Color.white);
    	panStock.setPreferredSize(new Dimension(250, 80));
    	panStock.setBorder(BorderFactory.createTitledBorder("Stock :"));
    	JLabel stockLabel = new JLabel("Quantité de produit en stock : ");
    	stockText = new JTextField();
    	stockText.setPreferredSize(new Dimension(90, 25));
    	panStock.add(stockLabel);
    	panStock.add(stockText);

    	JPanel content = new JPanel();
    	content.setBackground(Color.white);
    	content.add(panEmail);
    	content.add(panCategorie);
    	content.add(panNom);
    	content.add(panPrix);
    	content.add(panStock);


    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");

    	okBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			confirmation = new ConfirmationVente(null, "Confirmation", true, email, (String) categorieBox.getSelectedItem(), (String) nomBox.getSelectedItem(),
    					prixText.getText(), stockText.getText());
    			info = new InfoProduitAVendre(email, (String) categorieBox.getSelectedItem(), (String) nomBox.getSelectedItem(),
    					prixText.getText(), stockText.getText());
    	        JOptionPane jop = new JOptionPane();
    	        jop.showMessageDialog(null, info.toString(), "Récapitulatif sur le produit à vendre", JOptionPane.INFORMATION_MESSAGE);
    	        confirmation.afficher();
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
