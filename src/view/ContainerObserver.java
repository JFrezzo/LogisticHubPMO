package view;

import java.util.List;

public interface ContainerObserver extends Observer {
	String[] getVehicles();
	String[] getProducts();
	void add(String prod, String qta);
	void setVehicle(String id);
	List<String> getContainer();
	void send();
}
