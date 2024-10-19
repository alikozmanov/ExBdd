package fr.fms.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class BddConnection {
    // Variable statique pour stocker l'unique instance de la connexion
    private static Connection connection = null;

    // Méthode pour récupérer la connexion à la base de données
    public static Connection getConnection() {
        // Vérifie si la connexion n'a pas déjà été établie
        if (connection == null) {
            try (FileInputStream fis = new FileInputStream("lib/config.properties")) {
                // Chargement des propriétés de configuration depuis le fichier
                Properties prop = new Properties();
                prop.load(fis);

                // Récupération des informations de connexion depuis les propriétés
                String url = prop.getProperty("db.url");
                String user = prop.getProperty("db.user");
                String password = prop.getProperty("db.password");

                // Établissement de la connexion à la base de données
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                // Gestion des exceptions en cas d'erreur
                e.printStackTrace();
            }
        }
        // Retourne l'instance (connexion)
        return connection;
    }
}
