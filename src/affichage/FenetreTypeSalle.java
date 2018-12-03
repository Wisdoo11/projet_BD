package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import projet_BD.Requete;

public class FenetreTypeSalle extends JDialog {
	
	private JLabel typeLabel, libreLabel, revocableLabel, enchereMultipleLabel;
	private String categorie;
	private JTextField categorieText;
	private InfoSalle infoSalle;

	public FenetreTypeSalle(JFrame parent, String title, boolean modal, String categorie){
		super(parent, title, modal);
		this.categorie = categorie;
		this.setSize(400, 500);
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
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B>Rentrez les caractéristiques des ventes de la salle : </B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);
		
		//catégorie
		categorieText = new JTextField(categorie);
		
    	//Le type de la salle
    	JPanel panType = new JPanel();
    	panType.setBackground(Color.white);
    	panType.setPreferredSize(new Dimension(250, 80));
    	panType.setBorder(BorderFactory.createTitledBorder("Type de vente"));
    	JComboBox<String> type = new JComboBox<String>();
    	type.addItem("Montante");
    	type.addItem("Descendante");
    	typeLabel = new JLabel("Type : ");
    	panType.add(typeLabel);
    	panType.add(type);
    	
    	//vente à duree libre ou à duree limitee
    	JPanel panLibre = new JPanel();
    	panLibre.setBackground(Color.white);
    	panLibre.setPreferredSize(new Dimension(250, 80));
    	panLibre.setBorder(BorderFactory.createTitledBorder("Type de durée"));
    	JComboBox<String> libre = new JComboBox<String>();
    	libre.addItem("Durée libre");
    	libre.addItem("Durée limitée");
    	libreLabel = new JLabel("Type : ");
    	panLibre.add(libreLabel);
    	panLibre.add(libre);
    	
    	//révocabilité des ventes
    	JPanel panRevocable = new JPanel();
    	panRevocable.setBackground(Color.white);
    	panRevocable.setPreferredSize(new Dimension(250, 80));
    	panRevocable.setBorder(BorderFactory.createTitledBorder("Révocabilité"));
    	JComboBox<String> revocable = new JComboBox<String>();
    	revocable.addItem("Oui");
    	revocable.addItem("Non");
    	revocableLabel = new JLabel("Révocable : ");
    	panRevocable.add(revocableLabel);
    	panRevocable.add(revocable);
    	
    	//enchère multiple
    	JPanel panEnchMult = new JPanel();
    	panEnchMult.setBackground(Color.white);
    	panEnchMult.setPreferredSize(new Dimension(300, 80));
    	panEnchMult.setBorder(BorderFactory.createTitledBorder("Enchère multiple"));
    	JComboBox<String> enchereMultiple = new JComboBox<String>();
    	enchereMultiple.addItem("Oui");
    	enchereMultiple.addItem("Non");
    	enchereMultipleLabel = new JLabel("Un même utilsateur peut \n enchèrir plusieurs fois : ");
    	panEnchMult.add(enchereMultipleLabel);
    	panEnchMult.add(enchereMultiple);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		
		//content.add(panEmail);
		//content.add(panType);
		//content.add(panLibre);
		//content.add(panRevocable);
		//content.add(panEnchMult);
		
	    Box box0 = Box.createVerticalBox();
	    box0.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box1 = Box.createVerticalBox();
	    box1.add(panType);
	    box1.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box2 = Box.createVerticalBox();
	    box2.add(panLibre);
	    box2.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box3 = Box.createVerticalBox();
	    box3.add(panRevocable);
	    box3.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box4 = Box.createVerticalBox();
	    box3.add(panEnchMult);
	    box3.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box00 = Box.createVerticalBox();
	    box00.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box = Box.createVerticalBox();
	    box.add(box0);
	    box.add(box1);
	    box.add(box2);
	    box.add(box3);
	    box.add(box4);
	    box.add(box00);
		
    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");

    	okBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			int type1 = 1, libre1 = 1, revocable1 = 0, enchere1 = 1;
    			if (type.getSelectedItem().equals("Descendante")) {
    				type1 = 0;
    			}
    			if (libre.getSelectedItem().equals("Durée limitée")) {
    				libre1 = 0;
    			}
    			if (revocable.getSelectedItem().equals("Oui")) {
    				revocable1 = 1;
    			}
    			if (enchereMultiple.getSelectedItem().equals("Non")) {
    				enchere1 = 0;
    			}
    			String preStmt = "insert into Salle1(nom_categorie, type_vente, est_libre, est_revocable, enchere_multiple)"
    					+ " values ('" + categorieText.getText() + "', '" + type1 + "', '" + libre1 + "', '"
    					+ revocable1 + "', '" + enchere1 + "')";
    			Requete requete = new Requete(preStmt);
    			requete.executeUpdateReq();
    			
			    String id = requete.recupIdSalle(categorieText.getText(), type1, libre1, revocable1, enchere1);
			    
    			infoSalle = new InfoSalle(id, categorieText.getText(), type1, libre1, revocable1, enchere1);
    	        JOptionPane.showMessageDialog(null, infoSalle.toString(), "Récapitulatif sur la salle de vente", JOptionPane.INFORMATION_MESSAGE);
    	        
    	        //affiche la requête
    	        System.out.println("insert into Salle1(id_salle, nom_categorie, type_vente, est_libre, est_revocable, enchere_multiple)"
    					+ " values (" + id + "'" + categorieText.getText() + "', '" + type1 + "', '" + libre1 + "', '"
    					+ revocable1 + "', '" + enchere1 + "')");
    	        
    	        //affiche tout les produits de la catégorie catégorie
				System.out.println("Voici les produits de la catégorie : " + categorie);
    			String preStmt1 = "select * from Produit1 where nom_categorie='" + categorie + "'";
    			Requete requete1 = new Requete(preStmt1);
    			requete1.execute();
    	        
    	        FenetreChoixNbrProduits fenetre = new FenetreChoixNbrProduits(null, "Nombre de produits à vendre dans la salle", true,
    	        		"Admin", true, (String) categorieText.getText(), id, libre1);
    	        fenetre.afficher();
    	        
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

    	this.getContentPane().add(box);
    	//this.getContentPane().add(new JScrollPane(), BorderLayout.EAST);
    	this.getContentPane().add(control, BorderLayout.SOUTH);
	}
	

}
