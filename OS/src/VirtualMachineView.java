package GUI;

/**
 *
 * @author Antanas
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import mos.Memory;
import mos.Processor;
import mos.RealMachine;
import mos.VirtualMachine;

public class VirtualMachineView extends JFrame {
    
    Processor processor;
    VirtualMachine machine;
    JPanel registerPanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JButton exitButton;
    int registerWidth = 3;
    private CustomTableCellRenderer renderer;
    
    RegisterTextField ic = new RegisterTextField( registerWidth, "IC" );
    RegisterTextField rn = new RegisterTextField( registerWidth, "RN" );
    RegisterTextField r1 = new RegisterTextField( registerWidth, "R1" );
    RegisterTextField r2 = new RegisterTextField( registerWidth, "R2" );
    RegisterTextField c = new RegisterTextField( registerWidth, "C" );
    RegisterTextField ptr = new RegisterTextField( registerWidth, "PTR" );
    
    JTable table = new JTable( ( VirtualMachine.MEMORY_SIZE / Memory.BLOCK_SIZE ), 
                              Memory.BLOCK_SIZE );
	
    public VirtualMachineView( VirtualMachine machine ){
        setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
        setResizable( false );
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        this.setLocation( width - 550, 350 * ( machine.getID() ) );
        this.machine = machine;
        this.processor = machine.processor;
        this.setSize( new Dimension( 550, 350 ) );
        setTitle( "Virtuali mašina" );
        
        registerPanel.setLayout( new GridLayout( 2, 0, 10, 1 ) );
        registerPanel.setBorder( BorderFactory.createCompoundBorder( 
                                 BorderFactory.createTitledBorder( "Registrai" ), 
                                 BorderFactory.createEmptyBorder( 10, 10, 10, 10) 
                                 ) 
                               );
        
        registerPanel.add( ic.label );
        registerPanel.add( ic );
        registerPanel.add( r1.label );
        registerPanel.add( r1 );
        registerPanel.add( c.label );
        registerPanel.add( c );
        registerPanel.add( rn.label );
        registerPanel.add( rn );
        registerPanel.add( r2.label );
        registerPanel.add( r2 );
        registerPanel.add( ptr.label );
        registerPanel.add( ptr );

        exitButton = new JButton( "Baigti darbą" );
        
        mainPanel.add( registerPanel );
        mainPanel.add( exitButton );
        mainPanel.add( getTablePanel() );
        add( mainPanel );
        
        setVisible( true );
        update();
    }
    
    public final void update(){
        setRegisters();
        fillTable();
    }
    
    private void fillTable() {
        for ( int i = 0; i < VirtualMachine.MEMORY_SIZE / Memory.BLOCK_SIZE; i ++ ) {
            for ( int j = 0; j < Memory.BLOCK_SIZE; j++ ) {
                table.setValueAt( machine.getMemoryContent( i * Memory.BLOCK_SIZE + j ), i, j );
            }
        }
        
        renderer.setIC( machine.processor.getIC() );
        table.setEnabled( false );
    }
    
    private void setRegisters() {
        ptr.setText( processor.getPTR().toString() );
        rn.setText( processor.getRN().toString() );
        r1.setText( processor.getR1() );
        r2.setText( processor.getR2() );
        c.setText( processor.getC().toString() );
        ic.setText( processor.getIC().toString() );
    }
    
    private JPanel getTablePanel() {
        table.setPreferredSize( new Dimension( 500, 180 ) );
        table.setCellSelectionEnabled( false );
        table.setEnabled( false );
        renderer = new CustomTableCellRenderer();
        table.setDefaultRenderer( table.getColumnClass( 0 ), renderer );
        
        JTableHeader columnHeader = table.getTableHeader();
        columnHeader.setResizingAllowed( false );
        columnHeader.setReorderingAllowed( false );
        columnHeader.setPreferredSize( new Dimension( table.getHeight(), 
                                                      table.getRowHeight() 
                                                    ) 
                                     );
        
        for ( int i = 0; i < Memory.BLOCK_SIZE; i++ ) {
            columnHeader.getColumnModel().getColumn( i ).setHeaderValue( i );
        }
        
        JPanel rowHeader = new JPanel( new GridLayout( 0, 1, 0, 0 ) );
        
        for ( int j = 0; j < RealMachine.MEMORY_SIZE / Memory.BLOCK_SIZE; j++ ) {
            JLabel rowLabel = new JLabel( Integer.toString( j ) );
            rowLabel.setOpaque( true );
            rowLabel.setHorizontalAlignment( JLabel.CENTER );
            rowLabel.setPreferredSize( new Dimension( 
                                            500 / ( Memory.BLOCK_SIZE + 1 ), 
                                            table.getRowHeight() 
                                           ) 
                                     );
            rowLabel.setBackground( columnHeader.getBackground() );
            rowLabel.setForeground( columnHeader.getForeground() );
            rowLabel.setFont( columnHeader.getFont() );
            rowLabel.setBorder( UIManager.getBorder( "TableHeader.cellBorder" ) );
            rowHeader.add( rowLabel );
        }
        
        JScrollPane tableCellPane = new JScrollPane( table );
        tableCellPane.setPreferredSize( new Dimension( 500, table.getRowHeight() * 
                                      ( Memory.BLOCK_SIZE + 1 ) + 2 ) );
        tableCellPane.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        tableCellPane.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
        tableCellPane.setRowHeaderView( rowHeader );
        
        JLabel cornerLabel = new JLabel( "APS" );
        cornerLabel.setOpaque( true );
        cornerLabel.setHorizontalAlignment( JLabel.CENTER );
        cornerLabel.setPreferredSize( new Dimension( 
                                        500 / ( Memory.BLOCK_SIZE + 1 ), 
                                        table.getRowHeight() 
                                        ) 
                                    );
        cornerLabel.setBackground( columnHeader.getBackground() );
        cornerLabel.setForeground( columnHeader.getForeground() );
        cornerLabel.setFont( columnHeader.getFont() );
        cornerLabel.setBorder( UIManager.getBorder( "TableHeader.cellBorder" ) );
        tableCellPane.setCorner( JScrollPane.UPPER_LEFT_CORNER, cornerLabel );
        
        JPanel tablePanel = new JPanel();

        tablePanel.add( tableCellPane );
        tablePanel.setBorder( BorderFactory.createTitledBorder(
                                 BorderFactory.createLineBorder( Color.BLACK ),
                                 "Reali atmintis" )
                               );
        return tablePanel;
    }
}