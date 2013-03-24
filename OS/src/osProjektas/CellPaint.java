package osProjektas;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellPaint extends DefaultTableCellRenderer {
    
    int cx = 0;
   
    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                     Object value,
                                                     boolean isSelected,
                                                     boolean hasFocus,
                                                     int row,
                                                     int column) {
        Component c = super.getTableCellRendererComponent( table, value,
                                                       isSelected, hasFocus,
                                                       row, column);
        
        if ( row == cx / 10 && column == cx % 10 ) {
            c.setBackground( Color.GREEN );
        } else {
            c.setBackground( Color.WHITE );
        }
        return c;
    }
    
    public void setIC( int cx ) {
        this.cx = cx;
    }
}