// Permet de gérer les articles dans une base de données en fournissant des méthodes pour ajouter, lire, mettre à jour et supprimer des articles

package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class ArticleDao implements Dao<Article> {
    private Connection connection;

    // Constructeur pour initialiser la connexion à la base de données
    public ArticleDao() {
        this.connection = BddConnection.getConnection(); // Obtention de la connexion via la classe BddConnection
    }

    @Override
    public void create(Article article) {
        String sql = "INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Définition des paramètres de la requête
            ps.setString(1, article.getDescription());
            ps.setString(2, article.getBrand());
            ps.setDouble(3, article.getUnitaryPrice());
            // Exécution de la requête d'insertion
            ps.executeUpdate();
        } catch (SQLException e) {
            // Gestion des exceptions en cas d'erreur
            e.printStackTrace();
        }
    }

    @Override
    public Article read(int id) {
        String sql = "SELECT * FROM T_Articles WHERE IdArticle = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Définition du paramètre de la requête
            ps.setInt(1, id);
            // Exécution de la requête de sélection
            ResultSet rs = ps.executeQuery();
            // Si un résultat est trouvé, création et retour de l'objet Article
            if (rs.next()) {
                return new Article(rs.getInt("IdArticle"), rs.getString("Description"), rs.getString("Brand"), rs.getDouble("UnitaryPrice"));
            }
        } catch (SQLException e) {
            // Gestion des exceptions en cas d'erreur
            e.printStackTrace();
        }
        // Retourne null si aucun article n'est trouvé
        return null;
    }

    @Override
    public boolean update(Article article) {
        String sql = "UPDATE T_Articles SET Description = ?, Brand = ?, UnitaryPrice = ? WHERE IdArticle = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Définition des paramètres de la requête
            ps.setString(1, article.getDescription());
            ps.setString(2, article.getBrand());
            ps.setDouble(3, article.getUnitaryPrice());
            ps.setInt(4, article.getIdArticle());
            // Exécution de la requête de mise à jour et retour du résultat
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            // Gestion des exceptions en cas d'erreur
            e.printStackTrace();
        }
        // Retourne false si la mise à jour échoue
        return false;
    }

    @Override
    public boolean delete(Article article) {
        String sql = "DELETE FROM T_Articles WHERE IdArticle = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Définition du paramètre de la requête
            ps.setInt(1, article.getIdArticle());
            // Exécution de la requête de suppression et retour du résultat
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            // Gestion des exceptions en cas d'erreur
            e.printStackTrace();
        }
        // Retourne false si la suppression échoue
        return false;
    }

    @Override
    public ArrayList<Article> readAll() {
        ArrayList<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM T_Articles";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Exécution de la requête de sélection
            ResultSet rs = ps.executeQuery();
            // Parcours des résultats et ajout des articles à la liste
            while (rs.next()) {
                articles.add(new Article(rs.getInt("IdArticle"), rs.getString("Description"), rs.getString("Brand"), rs.getDouble("UnitaryPrice")));
            }
        } catch (SQLException e) {
            // Gestion des exceptions en cas d'erreur
            e.printStackTrace();
        }
        // Retourne la liste des articles
        return articles;
    }
}
