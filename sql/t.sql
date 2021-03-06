--Transaction :
--Mise en place d’une salle de vente et sélection de produits
--déjà disponibles à la vente et permettant le choix du type d’enchères et du prix de départ
Begin;
--choix de la catégorie
SELECT DISTINCT nom_categorie FROM Produit1 ;
--ajout de la salle de vente
INSERT INTO Salle1(nom_categorie, type_vente, est_libre, est_revocable, enchere_multiple) values ('Vetement', 0, 1, 1, 1);
--on récupère l’id de la salle créée
SELECT max(id_salle)
FROM Salle1
WHERE nom_categorie='Vetement'
AND type_vente=1 AND est_libre=1 AND est_revocable=0 AND enchere_multiple=1
--ajouter les produits souhaites parmis ceux autorisés
insert into Vente1(id_produit, id_salle, prix_depart, temps) values (3, 1, 80, TO_DATE('2018-12-06 10:02:08', 'YYYY-MM-DD HH24:MI:SS'));
insert into Vente1(id_produit, id_salle, prix_depart, temps) values (4, 1, 50, TO_DATE('2018-12-06 10:02:08', 'YYYY-MM-DD HH24:MI:SS'));
insert into Vente1(id_produit, id_salle, prix_depart, temps) values (5, 1, 30, TO_DATE('2018-12-06 10:02:08', 'YYYY-MM-DD HH24:MI:SS'));
commit;

--pour récupérer les produits disponile la Vente
--dans l'éventualité de les ajouter dans une salle de vente
Select Produit1.id_produit, Produit1.nom, Produit1.prix_revient
From Produit1
Where Produit1.nom_categorie = 'Vetement'
MINUS
Select Produit1.id_produit, Produit1.nom, Produit1.prix_revient
From Salle1, Vente1, Produit1
Where Salle1.nom_categorie = 'Vetement'
AND Produit1.nom_categorie = 'Vetement'
AND Vente1.id_salle = Salle1.id_salle
AND Vente1.id_produit = Produit1.id_produit


--Transaction
--











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
Where id_produit=1;

--pour récupérer le prix courant d'une Vente
--on commence par vérifier s'il y a des enchères pour cette vente
select COUNT(*) from Enchere1 where id_vente=idVente
--si pas d'enchère on récupère le prix de départ
select prix_depart from Vente1 where id_vente=idVente
--prix courant correpond à prix_propose/stock (java code java)
--si des enchères existent, on récupère le dernier prix propose : prix_propose/stock
select prix_propose, quantite, temps
From Enchere1
Where id_vente=idVente
Group by id_enchere, email, id_vente, prix_propose, temps, quantite
HAVING temps=max(temps)
Order by temps desc;


--pour récupérer les gaganants d'une vente montante
select Enchere1.email, Enchere1.prix_propose, Enchere1.quantite, Enchere1.temps
From Enchere1, Vente1
Where Enchere1.id_vente=Vente1.id_vente
And id_vente=idVente
And where Enchere1.temps <= Vente1.temps
Order by temps Desc

--on récupère la date et l'heure de fin de vente
select distinct Produit1.temps
From Enchere1, Produit1
Where Enchere1.id_vente=Produit1.id_vente
And Enchere1.id_vente= idVente
Group by temps
Having temps = MAX(temps)
