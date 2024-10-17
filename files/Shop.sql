-- Reconstruction de la base de données
DROP DATABASE IF EXISTS Shop;

CREATE DATABASE Shop; 

USE Shop;

-- Construction de la table des catégories
CREATE TABLE T_Categories (
    IdCategory     int(4)       PRIMARY KEY AUTO_INCREMENT,
    CategoryName   varchar(30)  NOT NULL
) ENGINE = InnoDB; -- Utilisation du moteur de stockage InnoDB

-- Insertion des catégories dans la table T_Categories
INSERT INTO T_Categories (CategoryName) VALUES ('Jeux vidéo');
INSERT INTO T_Categories (CategoryName) VALUES ('Consoles');
INSERT INTO T_Categories (CategoryName) VALUES ('Accessoires gaming');
INSERT INTO T_Categories (CategoryName) VALUES ('PC gamer');
INSERT INTO T_Categories (CategoryName) VALUES ('Cartes graphiques');
INSERT INTO T_Categories (CategoryName) VALUES ('Casques VR');

-- Construction de la table des articles en vente
CREATE TABLE T_Articles (
    IdArticle      int(4)       PRIMARY KEY AUTO_INCREMENT,
    Description    varchar(30)  NOT NULL,
    Brand          varchar(30)  NOT NULL,
    UnitaryPrice   float(8)     NOT NULL,
    IdCategory     int(4),
    FOREIGN KEY (IdCategory) REFERENCES T_Categories(IdCategory) -- Clé étrangére sur T_Categories
) ENGINE = InnoDB;

-- Insertion des articles avec référence à leur catégorie
INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('Souris', 'Logitoch', 65, 1);

INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('Clavier', 'Microhard', 49.5, 1);

INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('Systeme d''exploitation', 'Fenetres Vistouille', 150, 2);

INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('Tapis souris', 'Chapeau Bleu', 5, 3);

INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('Cle USB 8 To', 'Syno', 8, 3);

INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('Laptop', 'PH', 1199, 4);

INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('CD x 500', 'CETHE', 250, 5);

INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('DVD-R x 100', 'CETHE', 99, 5);

INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('DVD+R x 100', 'CETHE', 105, 5);

INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('Batterie Laptop', 'PH', 80, 6);

INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('Casque Audio', 'Syno', 105, 3);

INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('WebCam', 'Logitoch', 755, 3);

-- Sélection des articles et des catégories associées
-- Jointure pour récupérer le nom de la catégorie associée à chaque article
SELECT A.IdArticle, A.Description, A.Brand, A.UnitaryPrice, C.CategoryName FROM T_Articles A JOIN T_Categories C ON A.IdCategory = C.IdCategory; 
