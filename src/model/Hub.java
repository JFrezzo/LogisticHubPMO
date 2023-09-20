package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author jacopo
 * Concrete class of WareHouse, use the singleton pattern.
 * this class allows to represent the only one Hub of the project.
 */
public final class Hub implements WareHouse {

	private static Hub instance;
	private List<Product> wareHouse;

	private Hub() {
		this.wareHouse = new ArrayList<>();
	}
	
	
	public static Hub getInstance() {
		if(instance == null) {
			instance = new Hub();
		}
		
		return instance;
	}
	
	
	
	/**@param list of products
	 * inserts the product list into the hub
	 */
	@Override
	public void setNew(final List<Product> products) {
		this.wareHouse = new ArrayList<>();
		for(Product p : products) {
			this.add(p);
		}
	}
	
	
	
	/**
	 * @return the list of all products in the hub
	 */
	@Override
	public List<Product> getAllProducts(){
		return  Collections.unmodifiableList(new ArrayList<>(this.wareHouse));
	}

	
	
	/**
	 * @param position
	 * @return the product in that position
	 */
	@Override
	public Product getProduct(final int index) {
		return wareHouse.get(index);
	}
	
	
	
	/**
	 * @param id
	 * @return the product with that id
	 */
	@Override
	public Product getProductById(final String id)  {
		
		for(Product p: this.wareHouse) {
			if(p.getId().equals(id+"")) return p;
		}
		
		throw new IllegalArgumentException("ID NOT FOUND");
	}
	
	
	
	/**
	 *@param id
	 *@return true if the product with that id is present 
	 *@return false otherwise
	 */
	@Override
	public boolean find(final String id) {
		for(Product p: this.wareHouse) {
			if(p.getId().equals(id)) return true;
		}
		return false;
	}
	
	
	
	/**
	 * @param new product to add
	 * add the product with that id if isn't present yet
	 */
	@Override	
	public void add(final Product newProduct) {
		
		if(this.find(newProduct.getId())) { 	throw new IllegalArgumentException("ID ALREADY USE");} /* Id already use */
		check(newProduct);
		this.wareHouse.add(newProduct); /* adding product to the warehouse */
		
	}
	
	
	
	/**
	 * @param id
	 * remove the product if present with that id
	 */
	@Override
	public void remove(final String id) {
			
		
		for(Product p : this.wareHouse) {
			if(p.getId().equals(id)) { 
				
				this.wareHouse.remove(this.wareHouse.indexOf(p));

				return;
			}
		}
		
		throw new IllegalArgumentException("ID NOT FOUND");
		
		
	}
	
	
	
	/**
	 * @param Product to edit
	 * edit the product if exist in the hub
	 */
	@Override
    public void edit(final Product newProduct) {
    	
		if(!this.find(newProduct.getId())) { 	throw new IllegalArgumentException("ID NOT FOUND");} /* Id not found */
    	check(newProduct);
        for(Product p : this.wareHouse){

            if(p.getId().equals(newProduct.getId())){

                p.setDescription(newProduct.getDescription());
                p.setWeight(newProduct.getWeight());
                p.setPrice(newProduct.getPrice());
                p.setType(newProduct.getType());

                return;
            }
        }
    }
    
	
	
	/**
	 * @return the number of products present in the hub
	 */
	@Override
    public int getSize() {
    	return this.wareHouse.size();
    }
    
	
	
    /*
     * Control if data insert by users is correct or not
     */
    private void check(final Product newProduct)  {
    	
		if(	(newProduct.getType() != ProductType.CLOTHES) && (newProduct.getType() != ProductType.FOOD) && (newProduct.getType() != ProductType.TECH)) {
			throw new IllegalArgumentException("TYPE NOT CORRECT"); /* Type incorrect */
		}
			
		if(newProduct.getWeight() < 1) { throw new IllegalArgumentException("WEIGHT NOT CORRECT");} /* weight incorrect */
		if(newProduct.getPrice() < 1) { throw new IllegalArgumentException("PRICE NOT CORRECT");} /* price incorrect */
    	
    }
    

}
