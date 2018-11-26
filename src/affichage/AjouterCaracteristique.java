package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projet_BD.Requete;

public class AjouterCaracteristique extends JDialog {
	
	private JComboBox Categorie;
	private String email;
	private boolean estAdmin;

	public AjouterCaracteristique(JFrame parent, String title, boolean modal, String email){
		super(parent, title, modal);
		this.email = email;
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
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B>Ajouter une caractéristique à votre produit : </B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);
	
		//La catégorie de la salle
		JPanel panCategorie = new JPanel();
		panCategorie.setBackground(Color.white);
		panCategorie.setPreferredSize(new Dimension(250, 30));
	
		Requete requete = new Requete("select nom from Categorie1");
		ArrayList<String[]> selection = new ArrayList<String[]>();
		requete.getSelection(selection);
	
		Categorie = new JComboBox();
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
    			if (estAdmin) {
    				SalleDialog salle = new SalleDialog(null, "Ajouter une nouvelle salle", true, (String) Categorie.getSelectedItem());
    				ZDialogInfo zInfo = salle.showZDialog();
    				JOptionPane jop = new JOptionPane();
    				jop.showMessageDialog(null, zInfo.toString(), "Informations Salle de Ventes", JOptionPane.INFORMATION_MESSAGE);
    			} else {
    	 			FenetreVendreProduit fenetreVendre = new FenetreVendreProduit(null,"Vous souhaitez vendre un produit", true, email, (String) Categorie.getSelectedItem());
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