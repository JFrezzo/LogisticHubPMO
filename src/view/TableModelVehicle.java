package view;

import javax.swing.table.AbstractTableModel;

/* table model for the JTable in the VehicleViewImpl Frame */
public class TableModelVehicle extends AbstractTableModel {
	
	private final String[] columnName = {"ID",
			"DESCRIPTION",
			"TYPE"};
	
	private VehicleObserver obs;
	
	
	public TableModelVehicle(final VehicleObserver obs) {
		this.obs = obs;
	}


	@Override
	public int getRowCount() {
		
		return this.obs.getSize();
	}


	@Override
	public int getColumnCount() {
		return this.columnName.length;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.obs.getValueAt(rowIndex, columnIndex);
	}
	
	@Override
	 public String getColumnName(final int column) {
		 return this.columnName[column];
	 }
	
	
	public int getType(final int rowIndex) {
		return this.obs.getType(rowIndex);
	}

}
