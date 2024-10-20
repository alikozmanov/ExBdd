
// Permet de gérer la connexion à une base de données en récupérant les informations d'un fichier de configuration, 
// puis de lire les données d'une table (T_Articles) pour les afficher dans la console.

// Créer un fichier de configuration

package fr.fms.entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class CreateConfigFile {

    public static void main(String[] args) {
        ArrayList<Article> articles = new ArrayList<>(); // Liste pour stocker les articles depuis la BDD
        String strSql = "SELECT * FROM T_Articles";
        
        try {
            // Crée le fichier de configuration
            CreateConfigFile.createFile();

            // Charge les propriétés depuis le fichier de config
            Properties prop = CreateConfigFile.config("lib/config.properties");

            // Charge la classe du driver JDBC
            Class.forName(prop.getProperty("db.driver.class"));
            String url = prop.getProperty("db.url");
            String login = prop.getProperty("db.login");
            String password = prop.getProperty("db.password");

            // Connexion à la base de données
            try (Connection connection = DriverManager.getConnection(url, login, password);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(strSql)) {

                // Autentification de l'utilisateur
            	if (authenticateUser(connection)) {
            		// Parcours des résultats de la requête
                while (resultSet.next()) {
                    int rsIdUser = resultSet.getInt(1); // Récupère l'ID de l'article
                    String rsDescription = resultSet.getString(2); // Récupère la description
                    String rsBrand = resultSet.getString(3); // Récupère la marque
                    double rsPrice = resultSet.getDouble(4); // Récupère le prix
                    // Ajoute un nouvel article à la liste
                    articles.add(new Article(rsIdUser, rsDescription, rsBrand, rsPrice));
                }

                // Affiche les articles
                articles.forEach(a -> System.out.println(
                        a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - " + a.getUnitaryPrice()));
            } else {
            	System.out.println("Accès refusé, identifiants incorrects"); 
            	}
            }
        } catch (IOException ioe) {
            ioe.printStackTrace(); // Gestion des erreurs à la lecture et écriture des fichier
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); 
        } catch (SQLException e) {
            e.printStackTrace(); // Gestion des erreurs à l'exécution des requetes SQL
        }
    }


	// Méthode pour charger les propriétés depuis un fichier
    private static Properties config(String filePath) throws IOException {
        Properties prop = new Properties();
        // Charge les propriétés à partir du fichier 
        try (FileInputStream fis = new FileInputStream(filePath)) {
            prop.load(fis); // Charge le contenu du fichier
        }
        return prop; // Retourne prop contenant les param 
    }

    
    // Méthode pour créer un fichier de configuration
    private static void createFile() throws IOException {
        Properties prop = new Properties();
        // Crée un fichier de configuration 
        try (OutputStream output = new FileOutputStream("lib/config.properties")) {
            // Ajoute les property de configuration
            prop.setProperty("db.driver.class", "org.mariadb.jdbc.Driver");
            prop.setProperty("db.url", "jdbc:mariadb://localhost:3306/shop");
            prop.setProperty("db.login", "root");
            prop.setProperty("db.password", "fms2024");

            // Sauvegarde dans le fichier
            prop.store(output, null);
            System.out.println("Fichier de configuration créé avec succès :)");
        }
    }
 // Méthode pour authentifier l'utilisateur
    private static boolean authenticateUser(Connection connection) throws SQLException {
    }
        Scanner scanner = new Scanner(System.in); // Scanner pour lire l'entreé utilisateur
        System.out.print("Entrez votre login : ");
        String userLogin = scanner.nextLine(); // Récupere le login de l'utilisateur
        System.out.print("Entrez votre mot de passe : ");
        String userPassword = scanner.nextLine(); // Récupere le mot de passe de l'utilisateur
        // Requete SQL pour verifier les infoirmation de connexion de la BDD
        String query = "SELECT * FROM T_Users WHERE Login = ? AND Password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userLogin); // Requete login
            preparedStatement.setString(2, userPassword); // Requete mdp
            // Exécute la requete et verifie si un utilisateur est trrouvé
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();  // Retourne vrai si un utilisateur est trouvé
            }
}
