package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projet_BD.Requete;

public class FenetreNouvelUtilisateur extends JDialog {
	
	private JLabel emailLabel, nomLabel, prenomLabel, adresseLabel, cpLabel, villeLabel, paysLabel;
	private JTextField email, nom, prenom, adresse, cp, ville, pays;
	
	public FenetreNouvelUtilisateur(JFrame parent, String title, boolean modal){
		super(parent, title, modal); 
		this.setSize(400, 550);
		this.setLocationRelativeTo(null); 
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
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 16pt\"><B>Veuillez compléter le formulaire :</B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);
		
		//email
		JPanel panEmail = new JPanel();	
		panEmail.setBackground(Color.white);
		panEmail.setPreferredSize(new Dimension(90, 50));
		emailLabel = new JLabel("votre email :");
		email = new JTextField();
		email.setPreferredSize(new Dimension(150, 25));
		panEmail.add(emailLabel);
		panEmail.add(email);
		
		//nom
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(90, 50));
		nomLabel = new JLabel("Votre Nom : ");
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(100, 25));
		panNom.add(nomLabel);
		panNom.add(nom);
		
		//prenom
		JPanel panPrenom = new JPanel();
		panPrenom.setBackground(Color.white);
		panPrenom.setPreferredSize(new Dimension(90, 50));
		prenomLabel = new JLabel("Prenom : ");
		prenom = new JTextField();
		prenom.setPreferredSize(new Dimension(100, 25));
		panPrenom.add(prenomLabel);
		panPrenom.add(prenom);
		
		//adresse
		JPanel panAdresse = new JPanel();
		panAdresse.setBackground(Color.white);
		panAdresse.setPreferredSize(new Dimension(90, 50));
		adresseLabel = new JLabel("Adresse : ");
		adresse = new JTextField();
		adresse.setPreferredSize(new Dimension(150, 25));
		panAdresse.add(adresseLabel);
		panAdresse.add(adresse);
		
		//ville
		JPanel panVille = new JPanel();
		panVille.setBackground(Color.white);
		panVille.setPreferredSize(new Dimension(100, 50));
		villeLabel = new JLabel("Ville : ");
		ville = new JTextField();
		ville.setPreferredSize(new Dimension(100, 25));
		panVille.add(villeLabel);
		panVille.add(ville);
		
		//cp
		JPanel panCp = new JPanel();
		panCp.setBackground(Color.white);
		panCp.setPreferredSize(new Dimension(100, 50));
		cpLabel = new JLabel("Code Postal : ");
		cp = new JTextField();
		cp.setPreferredSize(new Dimension(50, 25));
		panCp.add(cpLabel);
		panCp.add(cp);
		
		//pays
		JPanel panPays = new JPanel();
		panPays.setBackground(Color.white);
		panPays.setPreferredSize(new Dimension(250, 50));
		paysLabel = new JLabel("Pays : ");
		pays = new JTextField();
		pays.setPreferredSize(new Dimension(100, 25));
		panPays.add(paysLabel);
		panPays.add(pays);

		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String preStmt = "insert into Utilisateur1 values ('" + email.getText() + "', '" + nom.getText() + "', '" +
						prenom.getText() + "', '" + adresse.getText() + "')";
				System.out.println(preStmt);
				Requete requete = new Requete(preStmt);
			    requete.execute();
			    
				String preStmt1 = "select * from Utilisateur1";
				//System.out.println(preStmt);
				Requete requete1 = new Requete(preStmt1);
			    requete1.execute();	
			    
			    setVisible(false);
			    
		    	FenetreDeConnexion fenetreConnexion = new FenetreDeConnexion(null, "Connexion", true, false);
		    	fenetreConnexion.afficher();
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
	    box0.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box1 = Box.createVerticalBox();
	    box1.add(panEmail);
	    box1.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box2 = Box.createVerticalBox();
	    box2.add(panNom);
	    box2.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box7 = Box.createVerticalBox();
	    box7.add(panPrenom);
	    box7.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box3 = Box.createVerticalBox();
	    box3.add(panAdresse);
	    box3.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box4 = Box.createVerticalBox();
	    box4.add(panCp);
	    box4.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box5 = Box.createVerticalBox();
	    box5.add(panVille);
	    box5.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box6 = Box.createVerticalBox();
	    box6.add(panPays);
	    box6.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box00 = Box.createVerticalBox();
	    box00.add(Box.createRigidArea(new Dimension(0,5)));
	    
	    Box box = Box.createVerticalBox();
	    box.add(box0);
	    box.add(box1);
	    box.add(box2);
	    box.add(box7);
	    box.add(box3);
	    box.add(box4);
	    box.add(box5);
	    box.add(box6);
	    box.add(box00);
	    
	    this.getContentPane().add(box);
	   	this.getContentPane().add(control, BorderLayout.SOUTH);
	  }

}
