--pour récupérer les id produits des produits de la catégorie Vetement
-- qui ne sont pas encore mis à la vente
Select distinct Produit1.id_produit, Produit1.nom
From Produit1
Where Produit1.nom_categorie = 'Vetements'
MINUS
Select Produit1.id_produit, Produit1.nom
From Salle1, Vente1, Produit1
Where Salle1.nom_categorie = 'Vetements'
AND Produit1.nom_categorie = 'Vetement'
AND Vente1.id_salle = Salle1.id_salle
AND Vente1.id_produit = Produit1.id_produit;

--pour récupérer les id produit de la catégorie Vetement
-- qui sont encore mis à la vente
Select distinct Produit1.id_produit, Produit1.nom
From Salle1, Vente1, Produit1
Where Salle1.nom_categorie = 'Vetements'
AND Produit1.nom_categorie = 'Vetement'
AND Vente1.id_salle = Salle1.id_salle
AND Vente1.id_produit = Produit1.id_produit;

--pour récupérer les produits qui sont mis à la vente (nom, id, caractéristiques)
Select distinct Produit1.id_produit, Produit1.nom, Caracteristique1.nom, Caracteristique1.valeur
From Salle1, Vente1, Produit1, Caracteristique1
Where Salle1.nom_categorie = 'Vetement'
AND Produit1.nom_categorie = 'Vetement'
AND Vente1.id_salle = Salle1.id_salle
AND Caracteristique1.id_produit = Produit1.id_produit
AND Vente1.id_produit = Produit1.id_produit
UNION
Select Produit1.id_produit, Produit1.nom, NULL, NULL
From Salle1, Vente1, Produit1
Where Salle1.nom_categorie = 'Vetement'
AND Produit1.nom_categorie = 'Vetement'
AND Vente1.id_salle = Salle1.id_salle
AND Vente1.id_produit = Produit1.id_produit
MINUS
Select Produit1.id_produit, Produit1.nom, NULL, NULL
From Salle1, Vente1, Produit1, Caracteristique1
Where Salle1.nom_categorie = 'Vetement'
AND Produit1.nom_categorie = 'Vetement'
AND Vente1.id_salle = Salle1.id_salle
AND Caracteristique1.id_produit = Produit1.id_produit
AND Vente1.id_produit = Produit1.id_produit;

--pour récupérer id_salle et id_vente d'un produit mis en vente
Select distinct id_salle, id_vente
From Vente1
Where id_produit='1'
AND nom_categorie='Vetement';

--pour récupérer les catégories dont des produits sont mis à la vente
Select distinct Produit1.nom_categorie
From Vente1, Produit1
Where Vente1.id_produit=Produit1.id_produit;

--pour récupérer le numéro de la salle et de la vente d'un produit
Select distinct id_salle, id_vente\r\n
From Vente1
Where id_produit='1';

--pour récupérer le prix courant d'une Vente
--on commence par vérifier s'il y a des enchères pour cette vente
select COUNT(*) from Enchere1 where id_vente=idVente
--si pas d'enchère on récupère le prix de départ
select prix_depart from Vente1 where id_vente=idVente
--prix courant correpond à prix_propose/stock (java code java)
--si des enchères existent, on récupère le dernier prix propose : prix_propose/stock
select prix_propose, quantite
From Enchere1
Where id_vente=idVente
Group by id_enchere, email, id_vente, prix_propose, temps, quantite
HAVING temps=max(temps)
