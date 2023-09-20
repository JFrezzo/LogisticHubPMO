package view;

import javax.swing.table.AbstractTableModel;


/* table model for the JTable in the ProductViewImpl Frame */
public class TableModelProduct extends AbstractTableModel {

	private final  String[] columnName = {"ID",
			"DESCRIPTION",
			"TYPE",
			"WEIGHT",
			"PRICE"};
	private ProductObserver obs;
	
	public TableModelProduct(final ProductObserver obs) {
		this.obs = obs;
	}
	
	
	@Override
	 public String getColumnName(final int column) {
		 return this.columnName[column];
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
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		return this.obs.getValueAt(rowIndex, columnIndex);
	}
	
	public int getType(final int rowIndex) {
		return this.obs.getType(rowIndex);
	}

}
