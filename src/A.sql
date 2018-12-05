DROP TABLE Caracteristique1;
DROP TABLE Enchere1;
DROP TABLE Vente1;
DROP TABLE Salle1;
DROP TABLE Produit1;
DROP TABLE Utilisateur1;
DROP TABLE Categorie1;

-- on crée la table Utilisateur
CREATE TABLE Utilisateur1
(
  email VARCHAR(100) PRIMARY KEY NOT NULL,
  nom VARCHAR(50),
  prenom VARCHAR(50),
  adresse_postale VARCHAR(250)
);


CREATE TABLE Categorie1
(
  nom VARCHAR(50) PRIMARY KEY NOT NULL,
  description VARCHAR(200) NOT NULL
);


CREATE TABLE Produit1
(
  id_produit INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
  nom_categorie VARCHAR(50) REFERENCES Categorie1(nom),
  email VARCHAR(100) REFERENCES Utilisateur1(email),
  nom VARCHAR(50),
  prix_revient FLOAT CHECK(prix_revient >= 0),
  stock INT CHECK(stock >= 0)
);


CREATE TABLE Salle1
(
  id_salle INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
  nom_categorie VARCHAR(50) REFERENCES Categorie1(nom),
  type_vente NUMBER(1, 0) DEFAULT 1, -- montante par DEFAULT
  est_libre NUMBER(1, 0) DEFAULT 1, -- libre par default (ie sans limite de temps)
  est_revocable NUMBER(1, 0) DEFAULT 0, -- non revocable par default
  enchere_multiple NUMBER(1, 0) DEFAULT 1 -- un même utilisateur peut enchérir plusieurs fois par default
);


CREATE TABLE Vente1
(
  id_vente INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
  id_produit INT REFERENCES Produit1(id_produit),
  id_salle INT REFERENCES Salle1(id_salle),
  prix_depart FLOAT CHECK(prix_depart >= 0),
  temps DATETIME -- date et heure de fin
);


CREATE TABLE Enchere1
(
  id_enchere INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
  email VARCHAR(100) REFERENCES Utilisateur1(email),
  id_vente INT REFERENCES Vente1(id_vente),
  prix_propose FLOAT CHECK(prix_propose >= 0),
  temps TIMESTAMP,
  quantite INT CHECK(quantite >= 0)
);


CREATE TABLE Caracteristique1
(
  nom VARCHAR(50) NOT NULL,
  id_produit INT REFERENCES Produit1(id_produit),
  valeur VARCHAR(250),
  PRIMARY KEY(nom, id_produit)
);
