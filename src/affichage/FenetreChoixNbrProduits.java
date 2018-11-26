package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projet_BD.Requete;

public class FenetreChoixNbrProduits extends JDialog {

	private String email;
	private boolean estAdmin;
	private JLabel produit;
	private JTextField produitText;
	private String categorie;

	public FenetreChoixNbrProduits(JFrame parent, String title, boolean modal, String email, boolean estAdmin, String categorie){
		super(parent, title, modal);
		this.email = email;
		this.estAdmin = estAdmin;
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.categorie = categorie;
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
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B>Choissisez le nombre de produits : </B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);
	
		// Nombre de produits
		JPanel panProduit = new JPanel();	
		panProduit.setBackground(Color.white);
		panProduit.setPreferredSize(new Dimension(200, 80));
		produit = new JLabel("Nombre de produits : ");
		produitText = new JTextField();
		produitText.setPreferredSize(new Dimension(150, 25));
		panProduit.add(produit);
		panProduit.add(produitText);
		
    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");

    	okBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
//    			if (estAdmin) {
//    				SalleDialog salle = new SalleDialog(null, "Ajouter une nouvelle salle", true, (String) produitText.getSelectedItem());
//    				ZDialogInfo zInfo = salle.showZDialog();
//    				JOptionPane jop = new JOptionPane();
//    				jop.showMessageDialog(null, zInfo.toString(), "Informations Salle de Ventes", JOptionPane.INFORMATION_MESSAGE);
//    			} else {
//    	 			FenetreVendreProduit fenetreVendre = new FenetreVendreProduit(null,"Vous souhaitez vendre un produit", true, email, (String) produitText.getSelectedItem());
//    	 			fenetreVendre.afficher();
//    	 			setVisible(false);
//    			}
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
