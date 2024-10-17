
---------------- mysql -u root -p ----------------------------
-- 1.1 Génerer votre base de données à l'aide du script Shopsql

-- 1.2 Permettant d'afficher toutes les tables 
	show databases;

-- 1.3 DESCRIBE ou DESC pour décrire la structure d'une table : 
	DESC T_Articles;

-- 1.4 Ajouter à la table des articles des occurrences de votre choix :
	INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Chargeur Rapide USB-C', 'Belkin', 35);
-- Affichage du contenu mis à jour de la table :
	SELECT * FROM T_Articles;

-- 1.5 Modifier un article avant de vérifier si c'est pris en compte :
	UPDATE T_Articles SET UnitaryPrice = 1099 WHERE Description = 'Laptop';
-- Vérification de la mise à jour :
	SELECT * FROM T_Articles WHERE Description = 'Laptop';

-- 1.6 Supprimer un article puis vérifier :
	DELETE FROM T_Articles WHERE Description = 'Clavier';
-- Vérification de la suppression :
	SELECT * FROM T_Articles WHERE Description = 'Clavier';

-- 1.7 Sélectionner tous les articles dont le prix est supérieur à 100 :
	SELECT * FROM T_Articles WHERE UnitaryPrice > 100;
	
-- 1.8 Sélectionner les articles dont le prix est compris entre 50 et 150 :
	SELECT * FROM T_Articles WHERE UnitaryPrice BETWEEN 50 AND 150;

-- 1.9 Afficher les articles dans l'ordre croissant des prix :
	SELECT * FROM T_Articles ORDER BY UnitaryPrice ASC;

-- 1.10 Afficher uniquement la description des articles :
	SELECT Description FROM T_Articles;

-- 1.11 Choisissez une requête particulièrement intéressante à présenter / Affiche les articles les plus chers :
	SELECT * FROM T_Articles ORDER BY UnitaryPrice DESC LIMIT 5;

-- 1.12 Ajouter la table des catégories à votre base de données et insérez-en : 
-- Vérification de l'existence des tables :
	SHOW TABLES;
-- Vérification de la strucutre des tables
	DESCRIBE T_Categories;
-- Vérification des données insérées :
	SELECT * FROM T_Categories;

-- 1.13 Trouver la requête qui permet d'obtenir le résultat suivant : 
	SELECT IdArticle, T_Articles.Description, Brand, UnitaryPrice, CategoryName FROM T_Articles INNER JOIN T_Categories ON T_Articles.IdCategory = T_Categories.IdCategory WHERE IdArticle>=10 ORDER BY UnitaryPrice;
