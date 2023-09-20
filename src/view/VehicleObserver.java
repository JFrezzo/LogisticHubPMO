package view;

import java.io.File;
import java.io.IOException;

public interface VehicleObserver extends Observer {

	void addVehicle(String id, String description, String type);
	void removeVehicle(String id);
	void editVehicle(String id, String description, String type);
	void exportVehicle() throws IOException;
	int getSize();
	Object getValueAt(final int rowIndex, final int columnIndex);
	int getType(final int rowIndex);
	void backToMenu();
}
