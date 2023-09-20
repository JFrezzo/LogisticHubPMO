package model;

/**
 * 
 * @author jacopo
 * * this interface abstracts the functions that each product must make available for the users
 */
public interface Product {
	ProductType getType(); //tipologia prodotto
	double getWeight(); // peso prodotto
	String getDescription(); // descrizione prodotto
	String getId(); // Id univoco prodotto
	double getPrice(); // prezzo
	void setDescription(String description);
	void setWeight(double weight);
	void setPrice(double price);
	void setType(ProductType type);
}
