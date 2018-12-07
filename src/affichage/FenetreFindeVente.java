package affichage;

import projet_BD.Requete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FenetreFindeVente extends JDialog {

    private JComboBox<String> venteBox;

    public FenetreFindeVente(JFrame parent, String title, boolean modal){
        super(parent, title, modal);
        this.setSize(300, 250);
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
                "<P ALIGN=CENTER STYLE=\"margin-bottom: 0in\"><FONT SIZE=5 STYLE=\"font-size: 12pt\"><B> Sélectionnez la vente à cloturer : </B></FONT></P></HTML>";
        JLabel label = new JLabel(labelText);
        panel.add(label);
        setContentPane(panel);

        //choix de la vente à cloturer
        JPanel panVente = new JPanel();
        panVente.setBackground(Color.white);
        panVente.setPreferredSize(new Dimension(200, 80));
        JLabel vente = new JLabel("Id de la vente : ");

        Requete requete = new Requete("Select id_vente\r\n" +
                "From Vente1\r\n");
        ArrayList<String[]> selection = new ArrayList<String[]>();
        requete.getSelection(selection);

        venteBox = new JComboBox<String>();
        for (String[] elt : selection) {
            for (String i:elt) {
                venteBox.addItem(i);
            }
        }
        panVente.add(vente);
        panVente.add(venteBox);

        //organisation visuelle
        Box box0 = Box.createVerticalBox();
        box0.add(Box.createRigidArea(new Dimension(0,10)));

        Box b1 = Box.createHorizontalBox();
        b1.add(panVente);
        b1.add(Box.createRigidArea(new Dimension(0,10)));

        Box box00 = Box.createVerticalBox();
        box00.add(Box.createRigidArea(new Dimension(0,5)));

        Box box = Box.createVerticalBox();
        box.add(box0);
        box.add(b1);
        box.add(Box.createRigidArea(new Dimension(0,5)));
        box.add(box0);

        //bouton ok
        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");

        okBouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {

                int idSalle, idVente;
                int[] info_vente, info_gagnants;

                int type, libre, revocable, enchereM;
                Requete requete= new Requete("select id_salle\r\n"
                        + "From Vente1\r\n"
                        + "Where id_vente=" + idVente);
                idSalle = requete.recupIdSalle1(); //TODO récupérer idSalle

                Requete requeteTypeVente1 = new Requete("select type_vente, est_libre, est_revocable, enchere_multiple\r\n"
                        + "From Salle1\r\n"
                        + "Where id_salle=" + idSalle);
                info_vente = requeteTypeVente1.recupTypeSalle();
                setVisible(false);

                if (info_vente[0] == 0) {
                    Requete requeteGagnants = new Requete("select email, prix_propose, quantite\r\n"
                            + "From Enchere1\r\n"
                            + "Where id_vente=" + idVente);
                    info_gagnants = requeteGagnants.recupTypeSalle();
                    if (info_vente[2] == 1) {
                        JOptionPane.showMessageDialog(null, "La vente est non révocable.\n Voici les gagnants:", " ", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "La vente est révocable.\n L'administrateur peut encore invalider la vente. \n Voici les gagnants provisoires:", " ", JOptionPane.INFORMATION_MESSAGE);
                    }
                    //TODO: fenêtre zdialoginfo avec les gagnants
                    }
                else {
                    //TODO: requete vente montante: pour chaque gagnant qu'on récupère selon le max du prix proposé, on décrémente le stock qu'on a récupéré.
                }
            }
        });

        //bouton annuler
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
