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

import projet_BD.Requete;

public class ChoixSalleAjoutProd extends JDialog {
	
	private JComboBox<String> salle;
	private boolean estAdmin;

	public ChoixSalleAjoutProd(JFrame parent, String title, boolean modal, boolean estAdmin){
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
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B>Choissisez le numéro de la salle : </B></FONT></P></HTML>";
		JLabel label = new JLabel(labelText);
		panel.add(label);
		setContentPane(panel);
	
		//La catégorie de la salle
		JPanel panSalle = new JPanel();
		panSalle.setBackground(Color.white);
		panSalle.setPreferredSize(new Dimension(250, 30));
	
		Requete requete = new Requete(""); //TODO à compléter
		ArrayList<String[]> selection = new ArrayList<String[]>();
		requete.getSelection(selection);
	
		salle = new JComboBox<String>();
		for (String[] elt : selection) {
			for (String i:elt) {
				salle.addItem(i);
			}
		}
		panSalle.add(salle);
		
		JPanel content = new JPanel();
    	content.setBackground(Color.white);
    	content.add(panSalle);
    	

    	JPanel control = new JPanel();
    	JButton okBouton = new JButton("OK");

    	okBouton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			if (estAdmin) {
    	      	  	//afficher les salles de vente
    				//TODO à compléter
    			}
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
