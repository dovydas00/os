package osProjektas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

public class VMView extends JFrame{
    
    Processor processor;
    VM machine;
    JPanel registerPanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JButton exitButton;
    int registerWidth = 3;
    private CustomTableCellRenderer renderer;
    
    RegisterTextField r1 = new RegisterTextField( registerWidth, "R1" );
    RegisterTextField r2 = new RegisterTextField( registerWidth, "R2" );
    RegisterTextField cx = new RegisterTextField( registerWidth, "CX" );
    RegisterTextField sv = new RegisterTextField( registerWidth, "SV" );
    RegisterTextField pr = new RegisterTextField( registerWidth, "PR" );
    RegisterTextField is = new RegisterTextField( registerWidth, "IS" );
    
    JTable table = new JTable( ( VM.MEMORY_SIZE / Memory.BLOCK_SIZE ), 
                              Memory.BLOCK_SIZE );
	
    public VMView( VM machine ){
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
        
        registerPanel.add( sv.label );
        registerPanel.add( sv );
        registerPanel.add( r1.label );
        registerPanel.add( r1 );
        registerPanel.add( cx.label );
        registerPanel.add( cx );
        registerPanel.add( pr.label );
        registerPanel.add( pr );
        registerPanel.add( r2.label );
        registerPanel.add( r2 );
        registerPanel.add( is.label );
        registerPanel.add( is );

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
        for ( int i = 0; i < VM.MEMORY_SIZE / Memory.BLOCK_SIZE; i ++ ) {
            for ( int j = 0; j < Memory.BLOCK_SIZE; j++ ) {
                table.setValueAt( machine.getMemoryContent( i * Memory.BLOCK_SIZE + j ), i, j );
            }
        }
        
        renderer.setIs( machine.processor.getIs() );
        table.setEnabled( false );
    }
    
    private void setRegisters() {
    	pr.setText( processor.getPr().toString() );
        is.setText( processor.getIs().toString() );
        r1.setText( processor.getR1() );
        r2.setText( processor.getR2() );
        cx.setText( processor.getCx().toString() );
        sv.setText( processor.getSv().toString() );
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
        
        for ( int j = 0; j < RM.MEMORY_SIZE / Memory.BLOCK_SIZE; j++ ) {
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