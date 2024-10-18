import java.util.ArrayList;

public interface Dao<T> {
	public Connection connection = BddConnection.getConnection(),
	public void create(T obj); // Ajout d'une nouvelle occurence en base
	public T read(int id); // Renvoi un l'objet correspondant à l'id en base
	public boolean update(T obj); // Met à jour l'objet en base, renvoi vrai si c'est fait
	public boolean delete(T obj); // Supprime un objet en base, renvoi vrai si c'est fait
	public ArrayList<T> readAll(); // Renvoi tous les objets de la table correspondante

}
