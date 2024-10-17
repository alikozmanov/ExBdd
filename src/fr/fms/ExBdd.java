package fr.fms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class ExBdd {

    public static void main(String[] args) throws Exception {
        
        // Liste pour stocker les articles récupérés 
        ArrayList<Article> articles = new ArrayList<Article>();
        
        try {
            // Chargement du driver JDBC de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Si le driver n'est pas trouvé, on affiche une erreur
            e.printStackTrace();
        }
        
        // Informations de connexion à la base de données
        String url = "jdbc:mariadb://localhost:3306/shop"; // URL de la base de données
        String login = "root"; // Nom d'utilisateur pour la connexion
        String password = "fms2024"; // Mot de passe pour la connexion
        
        // Tentative de connexion à la base de données avec les informations (url, login, password)
        try (Connection connection = DriverManager.getConnection(url, login, password)) { 
            
            // Requête SQL pour sélectionner tous les articles de la table T_Articles
            String strSql = "SELECT * FROM T_Articles";
            
            // Création Statement pour exécuter la requête SQL
            try (Statement statement = connection.createStatement()) {
                
                // Exécution de la requête SQL et récupération du résultat dans un ResultSet
                try (ResultSet resultSet = statement.executeQuery(strSql)) {
                    
                    // Parcours des résultats retournés par la requête
                    while (resultSet.next()) {
                        // Récupération des données de chaque colonne du ResultSet
                        int IdCategory = resultSet.getInt(1); // Récupère l'ID de l'article (colonne 1)
                        String Description = resultSet.getString(2); // Récupère la description (colonne 2)
                        String Brand = resultSet.getString(3); // Récupère la marque (colonne 3)
                        double UnitaryPrice = resultSet.getDouble(4); // Récupère le prix unitaire (colonne 4)
                        
                        // Création d'un objet Article et ajout à la liste 'articles'
                        articles.add(new Article(IdCategory, Description, Brand, UnitaryPrice));
                    }
                }
            }
            
            // Affichage des articles récupérés depuis la base de données
            for (Article a : articles) {
                System.out.println(a.getIdArticle() + " - " + a.getBrand() + " - " + a.getUnitaryPrice());
            }
        } catch (SQLException e) {
            // Gestion des exceptions liées à la base de données
            e.printStackTrace();
        }
    }
}
