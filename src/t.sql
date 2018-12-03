Select Produit1.id_produit, Salle1.id_salle, Vente1.id_vente
From Salle1, Vente1, Produit1
Where Salle1.nom_categorie = 'Vetements'
AND Vente1.id_salle = Salle1.id_salle
AND Vente1.id_produit = Produit1.id_produit
/
