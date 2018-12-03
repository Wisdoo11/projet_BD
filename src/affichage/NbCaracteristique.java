package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NbCaracteristique extends JDialog {
	
	private String categorie, email, idProduit;
	private JTextField nbCaracteristiqueText;
	private JLabel nbCaracteristiqueLabel;

	public NbCaracteristique(JFrame parent, String title, boolean modal, String email, String idProduit, String categorie){
		super(parent, title, modal);
		this.email = email;
		this.idProduit = idProduit;
		this.categorie = categorie;
		this.setSize(300, 300);
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
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B>Choissisez le nombre de caractéristiques : </B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);
	
		// Nom de la caractéristique
		JPanel panCaract = new JPanel();	
		panCaract.setBackground(Color.white);
		panCaract.setPreferredSize(new Dimension(200, 80));
		nbCaracteristiqueLabel = new JLabel("Nombre de caractéristiques : ");
		nbCaracteristiqueText = new JTextField();
		nbCaracteristiqueText.setPreferredSize(new Dimension(150, 25));
		panCaract.add(nbCaracteristiqueLabel);
		panCaract.add(nbCaracteristiqueText);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		
		content.add(panCaract);
		
    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");

    	okBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			AjouterCaracteristique fenetreAjoutCarac = new AjouterCaracteristique(null, "Ajoutez les caractéristiques du produit", true,
    					(String) nbCaracteristiqueText.getText(), idProduit);
    			fenetreAjoutCarac.afficher();
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
