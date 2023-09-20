package view;

import java.io.File;
import java.io.IOException;

public interface ProductObserver extends Observer {

	void addProduct(String id, String description, String type, String weight, String price);
	void removeProduct(String id);
	void editProduct(String id, String description, String type, String weight, String price);
	void exportProduct() throws IOException;
	int getSize();
	Object getValueAt(final int rowIndex, final int columnIndex);
	int getType(final int rowIndex);
	void backToMenu();
}
