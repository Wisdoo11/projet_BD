package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
	
	private String nombreCaract, idProduit;
	ArrayList<JTextField> listeNom = new ArrayList<JTextField>();
	ArrayList<JTextField> listeDescription = new ArrayList<JTextField>();

	public AjouterCaracteristique(JFrame parent, String title, boolean modal, String nombreCaract, String idProduit){
		super(parent, title, modal);
		this.idProduit = idProduit;
		this.nombreCaract = nombreCaract;
		this.setSize(600, 800);
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
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B> Ajoutez vos " + Integer.parseInt(nombreCaract) + " caractéristiques : </B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);

    	Box box = Box.createVerticalBox();
    	
	    Box box0 = Box.createVerticalBox();
	    box0.add(Box.createRigidArea(new Dimension(0,10)));
	    
	    box.add(box0);
    	
		for (int i = 0; i < Integer.parseInt(nombreCaract); i++) {
			// Ajouts des noms des caractéristiques
			JPanel panNomCract = new JPanel();	
			panNomCract.setBackground(Color.white);
			panNomCract.setPreferredSize(new Dimension(200, 80));
			JLabel nomCaractLabel = new JLabel("Nom de la caractéristique : ");
			JTextField nomCaractText = new JTextField();
			nomCaractText.setPreferredSize(new Dimension(150, 25));
			panNomCract.add(nomCaractLabel);
			panNomCract.add(nomCaractText);
			
			
			//prix de départ des produits
			JPanel panDescription = new JPanel();	
			panDescription.setBackground(Color.white);
			panDescription.setPreferredSize(new Dimension(200, 80));
			JLabel descriptionLabel;
			JTextField descriptionText;
			descriptionLabel = new JLabel("Description : ");
			descriptionText = new JTextField();
			descriptionText.setPreferredSize(new Dimension(150, 25));
			panDescription.add(descriptionLabel);
			panDescription.add(descriptionText);
	    	
	        Box b1 = Box.createHorizontalBox();
	        b1.add(panNomCract);
	        b1.add(Box.createRigidArea(new Dimension(0,10)));
	        b1.add(panDescription);
	        
	        box.add(b1);
	        box.add(Box.createRigidArea(new Dimension(0,5)));
	        
	        listeNom.add(nomCaractText);
	        listeDescription.add(descriptionText);
	        
		}	
	    
	    Box box00 = Box.createVerticalBox();
	    box00.add(Box.createRigidArea(new Dimension(0,5)));
		box.add(box0);
		
    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");
    	
    	
    	okBouton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
    			
    			Iterator<JTextField> itNom = listeNom.iterator();
    			Iterator<JTextField> itDescription = listeDescription.iterator();
    			String preStmt;
    			String preStmt1;
    			Requete requete1;
				ArrayList<String[]> selection;
				int id = Integer.parseInt(idProduit);

    			while (itNom.hasNext() ) {
    				
					//sinon on l'ajoute à la base de données
					preStmt = "insert into Caracteristique1(nom, id_produit, valeur) values('"
							+ (String) itNom.next().getText() + "', " + id +	",'" + (String) itDescription.next().getText() + "')";
					Requete requete = new Requete(preStmt);
					requete.executeUpdateReq();
					
					System.out.println(preStmt);
					
					setVisible(false);
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

    	this.getContentPane().add(box);
    	this.getContentPane().add(control, BorderLayout.SOUTH);
	}
}
