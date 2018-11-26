package affichage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AjouterUneSalle extends JDialog {
	
	private JButton bouton1 = new JButton("ajouter une salle");
	
	public AjouterUneSalle() {
		this.setTitle("Beinvenue");
		this.setSize(750, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		// texte de bienvenue
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
			   
		// Texte de Bienvenue
		String labelText = "<html><P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><BR>Que souhaitez-vous faire ?</P></HTML>";
		JLabel label = new JLabel(labelText);
		label.setLayout(new BoxLayout(label, BoxLayout.LINE_AXIS));
		panel.add(label);
		setContentPane(panel);
			    
		// Choisir entre utilisateur et administrateur
		Box b2 = Box.createHorizontalBox();
		b2.add(bouton1);
			    
		Box b4 = Box.createVerticalBox();
		b4.add(b2);

		this.getContentPane().add(b4);
			    
		bouton1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Fenetre fenetre = new Fenetre();
			}
		});

		this.setVisible(true);
	}

}
