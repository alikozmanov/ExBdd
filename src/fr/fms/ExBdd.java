package fr.fms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;


public class ExBdd {
	public static void main(String[] args){
		ArrayList<Article> articles = new ArrayList<Article>(); // Création d'une liste pour stocker les articles
		Article obj1 = new Article("Manette", "Dualshock", 59.99); // Création d'un article
		Article obj2 = new Article(26, 700); // Mettre à jour l'article avec l'Id et son prix
		Article obj3 = new Article(26); // La suppression en fonction de l'ID
		String strSql = "SELECT * FROM T_Articles";
		String str1 = "INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( '"+obj1.getDescription()+"' , '"+obj1.getBrand()+"', "+obj1.getUnitaryPrice()+")";
		String str2 = "UPDATE T_Articles SET UnitaryPrice = "+obj2.getUnitaryPrice()+" WHERE IdArticle = "+obj2.getIdArticle()+"";
		String str3 = "DELETE FROM T_Articles WHERE IdArticle = "+obj3.getIdArticle()+"";
		String str4 = "SELECT * FROM T_Articles WHERE IdArticle =4";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver"); // Chargement du driver de la BDD
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Paramètres de connexion à la BDD
		String url = "jdbc:mariadb://localhost:3306/shop";
		String login = "root";
		String password = "fms2024";
		
		try(Connection connection = DriverManager.getConnection(url, login, password)){
			Statement statement = connection.createStatement();
			
				// Requete d'insertion ici
				statement.execute(str1);
				ResultSet resultSet = statement.executeQuery(strSql);				
					
				// Parcours des résultats de la requete et ajout des articles dans la liste
					while(resultSet.next()) {
						int rsIdUser = resultSet.getInt(1);
						String rsDescription = resultSet.getString(2);
						String rsBrand = resultSet.getString(3);
						double rsPrice = resultSet.getDouble(4);
						articles.add((new Article(rsIdUser, rsDescription, rsBrand, rsPrice)));
					}
					
					// Execution de la requete de sélection pour un article spécifique
					select(obj2, str4, statement);
					
					// Affichage des articles dans la console
					articles.forEach((a) -> {System.out.println(a.getIdArticle()+ " - " +a.getDescription()+ " - " +a.getBrand()+ " - " +a.getUnitaryPrice());});
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	// Méthode pour executer des requetes sql d'insertion, mise à jour ou suppression
	public static void request(Article obj, String str, Statement statement) throws SQLException {
		int row1 = statement.executeUpdate(str);
		 if(row1 == 1) System.out.println("réussi");
	}
	// MMéthode pour executer une requete de selection et retourner un ResulSet
	public static ResultSet select(Article obj, String str, Statement statement)  throws SQLException{
		ResultSet resultSet = statement.executeQuery(str);
		return resultSet;
	}

}