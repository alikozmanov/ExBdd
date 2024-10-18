
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class CreateConfigFile {

    public static void main(String[] args) {
        ArrayList<Article> articles = new ArrayList<>();
        String strSql = "SELECT * FROM T_Articles";
        
        try {
            // Crée le fichier de config
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

                // Parcours des résultats de la requête
                while (resultSet.next()) {
                    int rsIdUser = resultSet.getInt(1);
                    String rsDescription = resultSet.getString(2);
                    String rsBrand = resultSet.getString(3);
                    double rsPrice = resultSet.getDouble(4);
                    articles.add(new Article(rsIdUser, rsDescription, rsBrand, rsPrice));
                }

                // Affiche les articles
                articles.forEach(a -> System.out.println(
                        a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand() + " - " + a.getUnitaryPrice()));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour charger les propriétés depuis un fichier
    private static Properties config(String filePath) throws IOException {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            prop.load(fis);
        }
        return prop;
    }

    // Méthode pour créer un fichier de configuration
    private static void createFile() throws IOException {
        Properties prop = new Properties();
        try (OutputStream output = new FileOutputStream("lib/config.properties")) {
            // Configuration des propriétés de la base de données
            prop.setProperty("db.driver.class", "org.mariadb.jdbc.Driver");
            prop.setProperty("db.url", "jdbc:mariadb://localhost:3306/shop");
            prop.setProperty("db.login", "root");
            prop.setProperty("db.password", "fms2024");

            // Sauvegarde dans le fichier
            prop.store(output, null);
            System.out.println("Fichier de configuration créé avec succès :)");
        }
    }
}
