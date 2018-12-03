-- peuplage de la table utilisateur
insert into Utilisateur1 values ('seb@gmail.com', 'Herbreteau', 'Sebastien', '8 place Jean Moulin, 38000 Grenoble');
insert into Utilisateur1 values ('alex@gmail.com', 'Donne', 'Alex', '9 place Jean Moulin, 38000 Grenoble');
insert into Utilisateur1 values ('vilayvos@gmail.com', 'Vilayvong', 'Sophia', '6 Place Pasteur, 38000 Grenoble');
insert into Utilisateur1 values ('lucasv@gmail.com', 'Vilayvong', 'Lucas', '7 Rue du Havre, 75009 Paris');
insert into Utilisateur1 values ('‎etienne.hurez@boulanger.fr', 'Hurez', '‎Etienne', '18 Avenue de la Motte, 59810 Lesquin');
insert into Utilisateur1 values ('josephine@gmail.com', 'De La Marre', 'Joséphine', '12 Esplanade des Droits de l Homme, 77185 Lognes');
insert into Utilisateur1 values ('‎rick@gmail.com', 'Grimmes', 'Rick', '9 rue des Rôdeurs, 78000 Versailles');
insert into Utilisateur1 values ('‎soleil@gmail.com', 'Galaxie', 'Soleil', '58 Avenue des Batignolles, 75008 Paris');
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
insert into Categorie1 values ('Gaming', 'Ordinateurs, souris, clavier, écrans et casque');
insert into Categorie1 values ('Autre', 'autre');
select * from Categorie1;

--puplage de la table Produit1
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (1, 'Informatique', 'vilayvos@gmail.com', 'Souris', 10.50, 10);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (2, 'Informatique', 'vilayvos@gmail.com', 'Ordinateur portable', 399, 1);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (3, 'Vetements', 'seb@gmail.com', 'Jeans', 50.2, 2);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (4, 'Vetements', 'vilayvos@gmail.com', 'Jeans', 35, 5);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (5, 'Vetements', 'josephine@gmail.com', 'Chemise', 20, 6);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (6, 'Electromenager', '‎etienne.hurez@boulanger.fr', 'Boulloire', 20.99, 25);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (7, 'Electromenager', '‎etienne.hurez@boulanger.fr', 'Cafetière', 45.5, 15);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (8, 'Electromenager', 'vilayvos@gmail.com', 'Four', 26, 3);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (9, 'Electromenager', 'rick@gmail.com', 'Micro-onde', 22, 8);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (10, 'Informatique', 'vilayvos@gmail.com', 'Clavier', 9.50, 5);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (11, 'Gaming', 'ballesteros.enrique@fnac-darty.fr', 'Ordinateur bureau', 1099, 20);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (12, 'Gaming', 'ballesteros.enrique@fnac-darty.fr', 'Souris', 99, 20);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (13, 'Gaming', '‎etienne.hurez@boulanger.fr', 'Clavier', 159, 16);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (14, 'Meuble', '‎etienne.hurez@boulanger.fr', 'Commode', 99, 8);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (15, 'Meuble', 'vilayvos@gmail.com', 'Table basse', 32, 3);
insert into Produit1(id_produit, nom_categorie, email, nom, prix_revient, stock) values (16, 'Meuble', 'lucasv@gmail.com', 'Table basse', 20, 2);
select * from Produit1;

insert into Salle1(nom_categorie) values ('Vetements');
insert into Salle1(nom_categorie) values ('Jouets');
select * from Salle1;


insert into Vente1(id_produit, id_salle, prix_depart, temps) values (1, 1, 5.1, '03-NOV-18 03.02.08 PM');
select * from Vente1;


insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values ('alex@gmail.com', 1, 8.2, CURRENT_TIMESTAMP, 2);
select * from Enchere1;

insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 1, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Liaison', 1, 'Filaire');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 1, 'Logitech');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 2, 'HP');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 2, 'Blanc');
insert into Caracteristique1(nom, id_produit, valeur) values ('Taille de lécran', 2, '15.4 pouces');
insert into Caracteristique1(nom, id_produit, valeur) values ('RAM', 2, '4 Go');
insert into Caracteristique1(nom, id_produit, valeur) values ('Disque dur', 2, '1 To');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 3, 'Bleu');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 4, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Coupe', 4, 'Skinny');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 5, 'Blanche');
insert into Caracteristique1(nom, id_produit, valeur) values ('Coupe des manches', 5, 'Longues');
insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 6, 'Noir');
insert into Caracteristique1(nom, id_produit, valeur) values ('Marque', 6, 'Russells Hobbs');
--a compléter

select * from Caracteristique1;
