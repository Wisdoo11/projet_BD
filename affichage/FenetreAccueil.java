package affichage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

public class FenetreAccueil extends JFrame {
	
	  private JButton bouton1 = new JButton("    Utilisateur    ");
	  private JButton bouton2 = new JButton(" Administrateur ");

	public FenetreAccueil() {
		this.setTitle("Bienvenue sur le service de vente aux enchères de Baie-électronique");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		
		// texte de bienvenue
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
	    
	    String labelText = "<html><P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><BR>\r\n" + 
	    "</P>\r\n" + 
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT COLOR=\"#c45911\"><FONT SIZE=5 STYLE=\"font-size: 16pt\"><B>Bienvenue\r\n" + 
	    "sur le service de vente aux enchères de Baie-électronique</B></FONT></FONT></P>\r\n" + 
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><BR>\r\n" + 
	    "</P>\r\n" + 
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><A NAME=\"_GoBack\"></A><BR>\r\n" + 
	    "</P>\r\n" + 
	    "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=4 STYLE=\"font-size: 16pt\"><B>Vous\r\n" + 
	    "êtes&nbsp;:</B></FONT></P>\r\n" +  "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><BR>\r\n" + 
	    "</P>\r\n" + "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><BR>\r\n" + 
	    "</P>\r\n" +  "</HTML>";
	    
	    JLabel label = new JLabel(labelText);
	    label.setLayout(new BoxLayout(label, BoxLayout.LINE_AXIS));
	    panel.add(label);

	    setContentPane(panel);
	    

	    Box b2 = Box.createHorizontalBox();
	    b2.add(bouton1);
	    b2.add(b2.createRigidArea(new Dimension(20,0)));
	    b2.add(bouton2);
	    
	    Box b4 = Box.createVerticalBox();
	    b4.add(b2);

	    this.getContentPane().add(b4);


	    this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		FenetreAccueil fenetre = new FenetreAccueil();
	}

}
