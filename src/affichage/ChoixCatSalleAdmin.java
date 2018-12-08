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
import javax.swing.JPanel;
import javax.swing.JTextField;

import projet_BD.Requete;

public class ChoixCatSalleAdmin extends JDialog {
	
	private JComboBox<String> Categorie;
	private boolean estAdmin;

	public ChoixCatSalleAdmin(JFrame parent, String title, boolean modal, boolean estAdmin){
		super(parent, title, modal);
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
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B>Choissisez la catégorie du produit : </B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);
	
		//La catégorie de la salle
		JPanel panCategorie = new JPanel();
		panCategorie.setBackground(Color.white);
		panCategorie.setPreferredSize(new Dimension(250, 30));
	
		Requete requete = new Requete("select distinct nom_categorie from Produit1");
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
    			FenetreTypeSalle fenetreSalle = new FenetreTypeSalle(null, "Type de Salle de vente", true, (String) Categorie.getSelectedItem());
    			fenetreSalle.afficher();
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
