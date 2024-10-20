// Puis dans votre programme princial tester votre implémentation ArticleDao (5.3)

package fr.fms.dao;

import fr.fms.dao.ArticleDao;
import fr.fms.entities.Article;

import java.util.ArrayList;

public class ArticleDaoTest {
    public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDao();

        // Test de la création d'un article
        Article newArticle = new Article("Clavier", "Logitech", 79.99);
        articleDao.create(newArticle);
        System.out.println("Article créé : " + newArticle);

        // Test de la lecture d'un article par ID
        Article readArticle = articleDao.read(newArticle.getIdArticle());
        System.out.println("Article lu : " + readArticle);

        // Test de la mise à jour d'un article
        readArticle.setUnitaryPrice(69.99);
        boolean isUpdated = articleDao.update(readArticle);
        System.out.println("Mise à jour réussie : " + isUpdated);
        System.out.println("Article mis à jour : " + articleDao.read(readArticle.getIdArticle()));

        // Test de la lecture de tous les articles
        ArrayList<Article> articles = articleDao.readAll();
        System.out.println("Tous les articles : ");
        articles.forEach(article -> System.out.println(article));

        // Test de la suppression d'un article
        boolean isDeleted = articleDao.delete(readArticle);
        System.out.println("Suppression réussie : " + isDeleted);

        // Vérifier que l'article a été supprimé
        Article deletedArticle = articleDao.read(readArticle.getIdArticle());
        System.out.println("Article supprimé : " + deletedArticle); // Devrait être null
    }
}

