package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 
 * @author jacopo
 *
 * this class allows to represent containers
 */

public class ConcreteContainer implements Container {

	private Map<String, Pair<Product, Integer>> idQta;
	private ProductType typeP;
	private VehicleType typeV; 
	private double quantity; /* the quantity in grams of the container */
	
	public ConcreteContainer(final ProductType typeP, final VehicleType typeV) {
		
		this.typeP = typeP;
		this.typeV = typeV;
		this.idQta = new HashMap<>();
		this.quantity = 0;
	}
	
	
	@Override
	public ProductType getTypeProduct() {
		return this.typeP;
	}
	
	@Override
	public VehicleType getTypeVehicle() {
		return this.typeV;
	}

	@Override
	public void add(Product p, int qta) {
		
		if(p == null) throw new IllegalArgumentException("THIS PRODUCT NOT EXIST IN THE SYSTEM");
		if(qta < 1) throw new IllegalArgumentException("QTA < 1");
		if((quantity + (qta * p.getWeight()))> this.typeV.getMaxQ()) throw new IllegalArgumentException("THE CONTAINER CAN NOT CONTAIN TOO MUCH WEIGHT: MAX WEIGHT IN GRAMS "+ this.typeV.getMaxQ());
		
		final String id = p.getId();
		
		if(p.getType() == this.typeP){
			
			this.quantity += (qta * p.getWeight());
			
			if(idQta.containsKey(id)) {
				
				idQta.get(id).setSecondValue(idQta.get(id).getSecond() + qta);
				
			}
			else {
				idQta.putIfAbsent(id, new Pair<Product, Integer>(p, qta));
			}
		}else throw new IllegalArgumentException("TYPE NOT CORRECT");
		
		
	}

	@Override
	public List<String> getContainer() {
		return idQta.values().stream().map( pair -> "\n"+ pair.getFirst().toString()+" Qta: "+pair.getSecond().toString()+"\n").toList();

	}


	@Override
	public double getQuantity() {
		return this.quantity;
	}

}
