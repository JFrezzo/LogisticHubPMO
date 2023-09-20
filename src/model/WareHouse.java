package model;

import java.util.List;
/**
 * 
 * @author jacopo
 * this interface abstracts the functions that each warehouse must make available for the users
 */
public interface WareHouse {
	List<Product> getAllProducts();
	Product getProduct(final int index) throws IllegalArgumentException;
	Product getProductById(final String id) throws IllegalArgumentException;
	boolean find(final String id);
	void add(final Product newProduct) throws IllegalArgumentException;
	void remove(final String id)throws IllegalArgumentException ;
	void edit(final Product newProduct) throws IllegalArgumentException;
	int getSize();
	void setNew(final List<Product> products);
}
