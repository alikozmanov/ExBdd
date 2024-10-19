// Définit une classe Java appelée Article qui modélise un article avec des attributs tels que l'identifiant, la description, 
// la marque et le prix unitaire, offrant des méthodes pour les gérer et une représentation textuelle de l'objet.

package fr.fms.entities;

public class Article {
    private int idArticle;
    private String description;
    private String brand;
    private double unitaryPrice;

    public Article(int idArticle, String description, String brand, double unitaryPrice) {
        this.idArticle = idArticle;
        this.description = description;
        this.brand = brand;
        this.unitaryPrice = unitaryPrice;
    }

    public Article(int idArticle) {
		this.idArticle = idArticle;
	}

	public Article(int idArticle, double unitaryPrice) {
		this.idArticle = idArticle;
		this.unitaryPrice = unitaryPrice;
	}

	public Article(String description, String brand, double unitaryPrice) {
		this.description = description;
		this.brand = brand;
		this.unitaryPrice = unitaryPrice;
	}

	public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setUnitaryPrice(double unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }

    @Override
    public String toString() {
        return "Article [idArticle=" + idArticle + ", description=" + description + ", brand=" + brand + ", unitaryPrice=" + unitaryPrice + "]";
    }
}
