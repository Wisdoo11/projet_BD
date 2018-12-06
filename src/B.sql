-- peuplage de la table utilisateur
insert into Utilisateur1 values ('seb@gmail.com', 'Herbreteau', 'Sebastien', '8 place Jean Moulin, 38000 Grenoble');
insert into Utilisateur1 values ('alex@gmail.com', 'Donne', 'Alex', '9 place Jean Moulin, 38000 Grenoble');
insert into Utilisateur1 values ('vilayvos@gmail.com', 'Vilayvong', 'Sophia', '6 Place Pasteur, 38000 Grenoble');
insert into Utilisateur1 values ('lucasv@gmail.com', 'Vilayvong', 'Lucas', '7 Rue du Havre, 75009 Paris');
insert into Utilisateur1 values ('etienne.hurez@boulanger.fr', 'Hurez', 'Etienne', '18 Avenue de la Motte, 59810 Lesquin');
insert into Utilisateur1 values ('josephine@gmail.com', 'De La Marre', 'Joséphine', '12 Esplanade des Droits de l Homme, 77185 Lognes');
insert into Utilisateur1 values ('rick@gmail.com', 'Grimmes', 'Rick', '9 rue des Rôdeurs, 78000 Versailles');
insert into Utilisateur1 values ('soleil@gmail.com', 'Galaxie', 'Soleil', '58 Avenue des Batignolles, 75008 Paris');
insert into Utilisateur1 values ('ballesteros.enrique@fnac-darty.fr', 'Ballesteros', 'Enrique', '9 Rue des Bateaux-Lavoirs, 94200 Ivry-sur-Seine');
select * from Utilisateur1;

--peuplage de la table catégorie
insert into Categorie1 values ('Vetements', 'Des vetements de toutes les sortes...');
insert into Categorie1 values ('Jouets', 'Des jouets de toutes les sortes...');
insert into Categorie1 values ('Electromenager', 'Appareils et outils électriques domestiques');
insert into Categorie1 values ('Meuble', 'Grandes variétés de meubles ...');
insert into Categorie1 values ('Informatique', 'Ordinateurs, matériel informatique, connectiques informatiques, accessoires informatiques, etc.');
insert into Categorie1 values ('Telephonie', 'Téléphones et accessoires téléphoniques (écouteurs, kits mains-libres, etc) ');
insert into Categorie1 values ('Tv_son_photo', 'Télévisions, enceintes, appareils photo et accesoires ...');
insert into Categorie1 values ('Gaming', 'Ordinateurs, souris, clavier, écrans et casque ...');
insert into Categorie1 values ('Autre', 'autre');
select * from Categorie1;

--puplage de la table Produit1
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Informatique', 'vilayvos@gmail.com', 'Souris', 10.50, 10);
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Informatique', 'vilayvos@gmail.com', 'Ordinateur portable', 399, 1);
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Vetements', 'seb@gmail.com', 'Jeans', 50.2, 2);
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Vetements', 'vilayvos@gmail.com', 'Jeans', 35, 5);
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Vetements', 'josephine@gmail.com', 'Chemise', 20, 6);
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Electromenager', '‎etienne.hurez@boulanger.fr', 'Boulloire', 20.99, 25);
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Electromenager', '‎etienne.hurez@boulanger.fr', 'Cafetière', 200.5, 15);
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Electromenager', 'vilayvos@gmail.com', 'Four', 599, 1);
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Electromenager', 'rick@gmail.com', 'Micro-onde', 22, 8);
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Informatique', 'vilayvos@gmail.com', 'Clavier', 9.50, 5);
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Gaming', 'ballesteros.enrique@fnac-darty.fr', 'Ordinateur bureau', 3499, 20);
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Gaming', 'ballesteros.enrique@fnac-darty.fr', 'Souris', 60, 20);
insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Gaming', '‎etienne.hurez@boulanger.fr', 'Clavier', 159, 16);
select * from Produit1;

--peuplage de la table Salle1
insert into Salle1(nom_categorie, type_vente, est_libre, est_revocable, enchere_multiple) values ('Vetements', 0, 1, 1, 1);
insert into Salle1(nom_categorie, type_vente, est_libre, est_revocable, enchere_multiple) values ('Informatique', 1, 1, 0, 1);
select * from Salle1;

--peuplage de la table Vente1
insert into Vente1(id_vente, id_produit, id_salle, prix_depart, temps) values (1, 3, 1, 80, '2018-12-06 10:02:08.0'); --pour la Salle 1
insert into Vente1(id_vente, id_produit, id_salle, prix_depart, temps) values (1, 4, 1, 50, '2018-12-06 10:02:08.0');
insert into Vente1(id_vente, id_produit, id_salle, prix_depart, temps) values (1, 5, 1, 30, '2018-12-06 10:02:08.0');
select * from Vente1;


insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values ('alex@gmail.com', 1, 8.2, '2018-12-06 10:15:00.0' , 2);
select * from Enchere1;

--peuplage de la table Caracteristique1
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 1, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Liaison', 1, 'Filaire');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 1, 'Logitech');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 2, 'HP');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 2, 'Blanc');
insert into Caracteristique1(nom, id_produit, valeur) values ('Taille de lécran', 2, '15.4 pouces');
insert into Caracteristique1(nom, id_produit, valeur) values ('RAM', 2, '4 Go');
insert into Caracteristique1(nom, id_produit, valeur) values ('Stockage', 2, 'Disque dur 1 To');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 3, 'Bleu');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 4, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Coupe', 4, 'Skinny');
insert into Caracteristique1(nom, id_produit, valeur) values ('Taille', 4, '36');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 5, 'Blanche');
insert into Caracteristique1(nom, id_produit, valeur) values ('Coupe des manches', 5, 'Longues');
insert into Caracteristique1(nom, id_produit, valeur) values ('Taille', 4, '38/40');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 6, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 6, 'Russell Hobbs');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 7, 'Delonghi');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 7, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Capcité', 7, '1.8 litres');
insert into Caracteristique1(nom, id_produit, valeur) values ('Numéro du modèle', 7, 'ECAM 22.110.B');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 8, 'Bosh');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 8, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Capcité', 8, '45 litres');
insert into Caracteristique1(nom, id_produit, valeur) values ('Numéro du modèle', 8, ' CMG636BW1');
insert into Caracteristique1(nom, id_produit, valeur) values ('Type', 8, 'Four encastrable');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 9, 'Samsung');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 9, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Capcité', 9, '25 litres');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 10, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Liaison', 10, 'Filaire');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 10, 'Logitech');
insert into Caracteristique1(nom, id_produit, valeur) values ('Rétroéclairage', 10, 'Non');
insert into Caracteristique1(nom, id_produit, valeur) values ('Type de produit', 10, 'Unité centrale uniquement');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 11, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 11, 'Asus ROG');
insert into Caracteristique1(nom, id_produit, valeur) values ('Processeur', 11, 'Intel Core i7 8700');
insert into Caracteristique1(nom, id_produit, valeur) values ('Mémoire', 11, '32');
insert into Caracteristique1(nom, id_produit, valeur) values ('Stockage', 11, '1 To SATA + 512 Go SSD');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 12, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Liaison', 12, 'Filaire');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 12, 'SteelSeries Rival 110');
insert into Caracteristique1(nom, id_produit, valeur) values ('Résolution', 12, '7200 cpi');
insert into Caracteristique1(nom, id_produit, valeur) values ('Boutons programmables', 12, '6');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 13, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Liaison', 13, 'Filaire');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 13, 'Clavier Gaming Razer BlackWidow Chroma V2');
insert into Caracteristique1(nom, id_produit, valeur) values ('Rétroéclairage', 13, 'Oui : multicouleur');
insert into Caracteristique1(nom, id_produit, valeur) values ('Boutons programmables', 13, 'Oui');
insert into Caracteristique1(nom, id_produit, valeur) values ('Type', 13, 'Azerty');
insert into Caracteristique1(nom, id_produit, valeur) values ('Mécanisme', 13, 'Clavier mécanique avec pavé numérique');
select * from Caracteristique1;
