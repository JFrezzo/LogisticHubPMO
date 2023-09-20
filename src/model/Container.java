package model;

import java.util.List;

/**
 * 
 * @author jacopo
 *this interface abstracts the functions that each container must make available for the users
 */
public interface Container {
	ProductType getTypeProduct(); /* restituisce la tipologia di prodotti che contiene */
	VehicleType getTypeVehicle(); /* restituisce la tipologia di veicolo in cui potr� essere inserito */
	void add(Product p, int qta); /* si aggiunge il prodotto con la relativa quantit�*/
	List<String> getContainer(); /* restituisce una lista contenente prodotto con relativa quantit� trasportata nel container */
	double getQuantity(); /* restituisce la quantit� in grammi che il contenitore contiene */
}
