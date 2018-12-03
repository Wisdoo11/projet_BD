--pour récupérer les id produits des produits de la catégorie Vetement
-- qui ne sont pas encore mis à la vente
Select Produit1.id_produit, Produit1.nom
From Produit1
MINUS
Select Produit1.id_produit, Produit1.nom
From Salle1, Vente1, Produit1
Where Salle1.nom_categorie = 'Vetements'
AND Produit1.nom_categorie = 'Vetement'
AND Vente1.id_salle = Salle1.id_salle
AND Vente1.id_produit = Produit1.id_produit
/

--pour récupérer les id produit de la catégorie Vetement
-- qui sont encore mis à la vente
Select Produit1.id_produit, Produit1.nom
From Salle1, Vente1, Produit1
Where Salle1.nom_categorie = 'Vetements'
AND Produit1.nom_categorie = 'Vetement'
AND Vente1.id_salle = Salle1.id_salle
AND Vente1.id_produit = Produit1.id_produit;
