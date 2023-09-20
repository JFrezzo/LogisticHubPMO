package model;

/**
 * 
 * @author jacopo
 * Concrete class of Product.
 * this class allows to represent products.
 */
public class ConcreteProduct implements Product {

	
	
	private String id;
	private String description;
	private double price;
	private ProductType type;
	private double weight;
	
	
	public ConcreteProduct(
			final String id,
			final String description,
			final ProductType type,
			final double weight,
			final double price) {
		
		this.id = id;
		this.description = description;
		this.price = price;
		this.type = type;
		this.weight = weight;

		
	}
	
	
	@Override
	public ProductType getType() {
		return this.type;

	}

	@Override
	public double getWeight() {
		return this.weight;

	}

	@Override
	public String getDescription() {
		return this.description;

	}

	@Override
	public String getId() {
		return this.id;

	}

	@Override
	public double getPrice() {
		return this.price;
	}
	
	public void setDescription(final String description) {
		this.description = description;
	}


	public void setPrice(final double price) {
		this.price = price;
	}


	public void setType(final ProductType type) {
		this.type = type;
	}


	public void setWeight(final double weight) {
		this.weight = weight;
	}


	public String[] toArray() {
		return new String[] {id, description, type+"", weight+"", price+""};
	}


	@Override
	public String toString() {
		return "id=" + id + ", description=" + description + ", price=" + price + ", type=" + type
				+ ", weight=" + weight + "";
	}

}
