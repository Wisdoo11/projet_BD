package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projet_BD.Requete;

public class AjouterCategorie extends JDialog {
	
	private JTextField categorie, description;
	private JLabel descriptionLabel, categorieLabel;
	
	public AjouterCategorie(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(400, 250); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
		this.initComponent();
	}
 
	public void afficher(){  
		this.setVisible(true);
	}
	
	private void initComponent(){
		
		//categorie
		JPanel panCategorie = new JPanel();	
		panCategorie.setBackground(Color.white);
		panCategorie.setPreferredSize(new Dimension(200, 80));
		categorieLabel = new JLabel("Nom de la catégorie : ");
		categorie = new JTextField();
		categorie.setPreferredSize(new Dimension(150, 25));
		panCategorie.add(categorieLabel);
		panCategorie.add(categorie);
		
		//description
		JPanel panDescription = new JPanel();	
		panDescription.setBackground(Color.white);
		panDescription.setPreferredSize(new Dimension(200, 80));
		descriptionLabel = new JLabel("Description de la catégorie : ");
		description = new JTextField();
		description.setPreferredSize(new Dimension(150, 25));
		panDescription.add(descriptionLabel);
		panDescription.add(description);	

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String preStmt = "insert into Categorie1 values ('" + categorie.getText() + "', '" + description.getText() + "')";
				//System.out.println(preStmt);
				Requete requete = new Requete(preStmt);
			    requete.execute();
			    
			    String preStmt1 = "select * from Categorie1";
				//System.out.println(preStmt);
				Requete requete1 = new Requete(preStmt1);
			    requete1.execute();
			    
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
	   	
    	// Choisir entre utilisateur et administrateur
	    Box box0 = Box.createVerticalBox();
	    box0.add(Box.createRigidArea(new Dimension(0,10)));
	    
	    Box box1 = Box.createVerticalBox();
	    box1.add(panCategorie);
	    box1.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box2 = Box.createVerticalBox();
	    box2.add(panDescription);
	    box2.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box00 = Box.createVerticalBox();
	    box00.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box = Box.createVerticalBox();
	    box.add(box0);
	    box.add(box1);
	    box.add(box2);
	    box.add(box00);
	   	
	    this.getContentPane().add(box);
	   	this.getContentPane().add(control, BorderLayout.SOUTH);
		  
	  }

}
