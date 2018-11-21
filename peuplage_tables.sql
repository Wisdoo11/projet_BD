insert into Utilisateur1 values ('seb@gmail.com', 'Herbreteau', 'Sebastien', '8 place Jean Moulin');
insert into Utilisateur1 values ('alex@gmail.com', 'Donne', 'Alex', '9 place Jean Moulin');
select * from Utilisateur1;



insert into Categorie1 values ('Vetements', 'Des vetements de toutes les sortes...');
insert into Categorie1 values ('Jouets', 'Des jouets de toutes les sortes...');
select * from Categorie1;


insert into Produit1(nom_categorie, email, nom, prix_revient, stock) values ('Vetements', 'seb@gmail.com', 'Jeans', 50.2, 2);
select * from Produit1;

insert into Salle1(nom_categorie) values ('Vetements');
insert into Salle1(nom_categorie) values ('Jouets');
select * from Salle1;


insert into Vente1(id_produit, id_salle, prix_depart, temps) values (1, 1, 5.1, CURRENT_TIMESTAMP);
select * from Vente1;


insert into Enchere1(email, id_vente, prix_propose, temps, quantite) values ('alex@gmail.com', 1, 8.2, CURRENT_TIMESTAMP, 2);
select * from Enchere1;


insert into Caracteristique1(nom, id_produit, valeur) values ('Couleur', 1, 'Bleu');
select * from Caracteristique1;
