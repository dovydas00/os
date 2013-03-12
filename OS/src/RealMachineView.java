
/**
 *
 * @author Antanas
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

public class RealMachineView extends JFrame {
    
    JPanel mainPanel = new JPanel();
    private RealMachine realMachine;
    
    JButton reloadButton = new JButton( "Perkrauti" );
    JButton executeButton = new JButton( "Kita komanda" );
    JButton fileLoadButton = new JButton( "Pasirinkti failą" );
    private int registerWidth = 3;
    private CustomTableCellRenderer renderer;
    private RegisterTextField ic = new RegisterTextField( registerWidth, "IC" );
    private RegisterTextField rn = new RegisterTextField( registerWidth, "RN" );
    private RegisterTextField r1 = new RegisterTextField( registerWidth, "R1" );
    private RegisterTextField r2 = new RegisterTextField( registerWidth, "R2" );
    private RegisterTextField c = new RegisterTextField( registerWidth, "C" );
    private RegisterTextField ptr = new RegisterTextField( registerWidth, "PTR" );
    private RegisterTextField mode = new RegisterTextField( registerWidth, "MODE" );
    private RegisterTextField si = new RegisterTextField( registerWidth, "SI" );
    private RegisterTextField pi = new RegisterTextField( registerWidth, "PI" );
    private RegisterTextField ti = new RegisterTextField( registerWidth, "TI" );
    JTextField filenameField = new JTextField( 20 );
    JTextArea inputField = new JTextArea();
    JTextArea outputField = new JTextArea();
    JTextArea errorField = new JTextArea();
    JTable table = new JTable( ( RealMachine.MEMORY_SIZE / Memory.BLOCK_SIZE ), 
                              Memory.BLOCK_SIZE );
    
    public RealMachineView( RealMachine realMachine ){
        this.setResizable( false );
        this.setLocation( 5, 5 );
        this.realMachine = realMachine;
        
        setSize( 800, 690 ); 
        setTitle( "Reali mašina" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        executeButton.setEnabled( false );
        
        inputField.setLineWrap( true );
        inputField.setWrapStyleWord( true );
        inputField.setEnabled( false );
        JScrollPane inputScrollPane = new JScrollPane( inputField, 
                               ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                               ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER 
                               );
        inputScrollPane.setPreferredSize( new Dimension( 250, 150 ) );
        inputScrollPane.setBorder( BorderFactory.createTitledBorder( 
                                   "Standartinis įvesties kanalas" )
                                 );
        
        outputField.setLineWrap( true );
        outputField.setWrapStyleWord( true );
        outputField.setEditable( false );
        JScrollPane outputScrollPane = new JScrollPane( outputField, 
                               ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                               ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER 
                               );
        outputScrollPane.setPreferredSize( new Dimension( 250, 150 ) );
        outputScrollPane.setBorder( BorderFactory.createTitledBorder( 
                                    "Standartinis išvesties kanalas" )
                                  );
        
        errorField.setLineWrap( true );
        errorField.setWrapStyleWord( true );
        errorField.setEditable( false );
        JScrollPane errorScrollpane = new JScrollPane( errorField, 
                               ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                               ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        errorScrollpane.setPreferredSize( new Dimension( 250, 150 ) );
        errorScrollpane.setBorder( BorderFactory.createTitledBorder(
                                   "Standartinis klaidų kanalas" )
                                 );
        
        JPanel channelPanel = new JPanel( new GridLayout( 0, 1, 10, 10 ) );
        channelPanel.setBorder( BorderFactory.createTitledBorder( 
                                BorderFactory.createLineBorder( Color.BLACK ),
                                "Kanalai" )
                              );
        channelPanel.setPreferredSize( 
                      new Dimension( 250, table.getRowHeight() * 
                          ( RealMachine.MEMORY_SIZE / Memory.BLOCK_SIZE + 1 ) ) );
        
        channelPanel.add( inputScrollPane );
        channelPanel.add( outputScrollPane );
        channelPanel.add( errorScrollpane );
        
        JPanel registerPanel = new JPanel( new GridLayout( 0, 6, 10, 1 ) );
        registerPanel.setBorder( BorderFactory.createCompoundBorder( 
                                 BorderFactory.createTitledBorder( "Registrai" ), 
                                 BorderFactory.createEmptyBorder( 10, 10, 10, 10) )
                                );
        
        registerPanel.add( ic.label );
        registerPanel.add( ic );
        registerPanel.add( c.label );
        registerPanel.add( c );
        registerPanel.add( si.label );
        registerPanel.add( si );
        registerPanel.add( rn.label );
        registerPanel.add( rn );
        registerPanel.add( ptr.label );
        registerPanel.add( ptr );
        registerPanel.add( pi.label );
        registerPanel.add( pi );
        registerPanel.add( r1.label );
        registerPanel.add( r1 );
        registerPanel.add( mode.label );
        registerPanel.add( mode );
        registerPanel.add( ti.label );
        registerPanel.add( ti );
        registerPanel.add( r2.label );
        registerPanel.add( r2 );
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new GridLayout( 2, 2, 10, 10 ) );
        buttonPanel.add( reloadButton );
        buttonPanel.add( executeButton );
        
        JPanel fileInput = new JPanel();
        fileInput.add( fileLoadButton );
        fileInput.add( filenameField );
        
        mainPanel.setLayout( new FlowLayout( FlowLayout.LEFT ) );
        mainPanel.add( registerPanel );
        mainPanel.add( buttonPanel );
        mainPanel.add( fileInput );
        mainPanel.add( getTablePanel() );
        mainPanel.add( channelPanel );
        
        add( mainPanel );
        
        update();
        setVisible( true );
    
        }
    
    /**
     * Sukuria virtualią mašiną bei jos interfeisą.
     */
    
    public void createVirtualMachine(){
        executeButton.setEnabled( true );
//        inputField.setText( "" );
//        outputField.setText( "" );
//        errorField.setText( "" );
        update();
    }
    
    /**
    * Atnaujinamas realios mašinos langas.
    */
    
    public final void update() {
        setRegisters();
        fillTable();
    }
    
    private void setRegisters() {
        si.setText( realMachine.processor.getSI().toString() );
        pi.setText( realMachine.processor.getPI().toString() );
        ti.setText( realMachine.processor.getTI().toString() );           
        ptr.setText( realMachine.processor.getPTR().toString() );
        rn.setText( realMachine.processor.getRN().toString() );
        r1.setText( realMachine.processor.getR1() );
        r2.setText( realMachine.processor.getR2() );
        c.setText( realMachine.processor.getC().toString() );
        ic.setText( realMachine.processor.getIC().toString() );
        mode.setText( realMachine.processor.getMode().toString() );        
    }
    
    /**
     * Užpildo interfeiso lentelę virtualios mašinos atminties masyvo duomenimis.
     */
        
    public void fillTable() {
        for( int i = 0; i <  RealMachine.MEMORY_SIZE / Memory.BLOCK_SIZE ; i++ ){
            for( int j = 0; j <  Memory.BLOCK_SIZE; j++ ){
                this.table.setValueAt( realMachine.getMemoryContent( i * Memory.BLOCK_SIZE + j ), i, j );
            }
        }
        
        renderer.setIC( realMachine.processor.page( realMachine.processor.getIC() ) );
        table.setEnabled( false );
    }
    
    private JPanel getTablePanel(){
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
                                      ( RealMachine.MEMORY_SIZE / Memory.BLOCK_SIZE + 1 ) + 2 ) );
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