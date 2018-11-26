package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import projet_BD.Requete;

public class SelectionProduit extends JDialog {
	
	private JComboBox produit;
	private JLabel produitLabel;
	private String email;
	private boolean estAdmin;

	public SelectionProduit(JFrame parent, String title, boolean modal, String email, boolean estAdmin){
		super(parent, title, modal);
		this.email = email;
		this.estAdmin = estAdmin;
		this.setSize(300, 200);
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
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B>Sélectionner le produit : </B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);
	
		//les produits détenus par l'utilisateur
		JPanel panCategorie = new JPanel();
		panCategorie.setBackground(Color.white);
		panCategorie.setPreferredSize(new Dimension(250, 30));
	
		Requete requete = new Requete("select produit from Categorie1");
		ArrayList<String[]> selection = new ArrayList<String[]>();
		requete.getSelection(selection);
	
		produit = new JComboBox();
		for (String[] elt : selection) {
			for (String i:elt) {
				produit.addItem(i);
			}
		}
		panCategorie.add(produit);
		
		JPanel content = new JPanel();
    	content.setBackground(Color.white);
    	content.add(panCategorie);
    	

    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");

    	okBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			if (estAdmin) {
    				SalleDialog salle = new SalleDialog(null, "Ajouter une nouvelle salle", true, (String) produit.getSelectedItem());
    				ZDialogInfo zInfo = salle.showZDialog();
    				JOptionPane jop = new JOptionPane();
    				jop.showMessageDialog(null, zInfo.toString(), "Informations Salle de Ventes", JOptionPane.INFORMATION_MESSAGE);
    			} else {
    	 			FenetreVendreProduit fenetreVendre = new FenetreVendreProduit(null,"Vous souhaitez vendre un produit", true, email, (String) produit.getSelectedItem());
    	 			fenetreVendre.afficher();
    			}
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
