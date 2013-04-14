package osProjektas;

/**
 *
 * @author Antanas
 */
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

	int IC = 0;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		if (row == IC / 10 && column == IC % 10) {
			c.setBackground(Color.GREEN);
		} else {
			c.setBackground(Color.WHITE);
		}
		return c;
	}

	public void setRMIS( int IS ) {
		
		for (int i =1; i<10;i++){
			System.out.println(Memory.pageTable[i].substring(2));
			if (Memory.pageTable[i].substring(2).equals("A")){
				this.IC = i*100+IS;;
			}
		}
    }
}