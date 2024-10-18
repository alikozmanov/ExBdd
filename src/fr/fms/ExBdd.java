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
		ArrayList<Article> articles = new ArrayList<Article>();
		Article obj1 = new Article("Manette", "Dualshock", 59.99);
		Article obj2 = new Article(22, 700); // Mettre à jour l'article avec l'Id 13 et changer son prix à 500
		Article obj3 = new Article(22);
		String strSql = "SELECT * FROM T_Articles";
		String str1 = "INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( '"+obj1.getDescription()+"' , '"+obj1.getBrand()+"', "+obj1.getUnitaryPrice()+")";
		String str2 = "UPDATE T_Articles SET UnitaryPrice = "+obj2.getUnitaryPrice()+" WHERE IdArticle = "+obj2.getIdArticle()+"";
		String str3 = "DELETE FROM T_Articles WHERE IdArticle = "+obj3.getIdArticle()+"";
		String str4 = "SELECT * FROM T_Articles WHERE IdArticle =4";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mariadb://localhost:3306/shop";
		String login = "root";
		String password = "fms2024";
		try(Connection connection = DriverManager.getConnection(url, login, password)){
			Statement statement = connection.createStatement();
				// Requete d'insertion
				statement.execute(str1);
				ResultSet resultSet = statement.executeQuery(strSql);				
					
					while(resultSet.next()) {
						int rsIdUser = resultSet.getInt(1);
						String rsDescription = resultSet.getString(2);
						String rsBrand = resultSet.getString(3);
						double rsPrice = resultSet.getDouble(4);
						articles.add((new Article(rsIdUser, rsDescription, rsBrand, rsPrice)));
					}
					
					select(obj2, str4, statement);
			articles.forEach((a) -> {System.out.println(a.getIdArticle()+ " - " +a.getDescription()+ " - " +a.getBrand()+ " - " +a.getUnitaryPrice());});
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	public static void request(Article obj, String str, Statement statement) throws SQLException {
		int row1 = statement.executeUpdate(str);
		 if(row1 == 1) System.out.println("réussi");
	}
	public static ResultSet select(Article obj, String str, Statement statement)  throws SQLException{
		ResultSet resultSet = statement.executeQuery(str);
		return resultSet;
	}

}