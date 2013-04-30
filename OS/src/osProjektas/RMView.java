package osProjektas;

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

public class RMView extends JFrame {
    
	   JPanel mainPanel = new JPanel();
	    //private RM RM;
	    
	    JButton reloadButton = new JButton( "Perkrauti" );
	    static JButton executeButton = new JButton( "Kita komanda" );
	    JButton fileLoadButton = new JButton( "Pasirinkti failą" );
	    private static int registerWidth = 3;
	    private static CustomTableCellRenderer renderer;
	    private static RegisterTextField cxV = new RegisterTextField( registerWidth, "cx" );
	    private static RegisterTextField isV = new RegisterTextField( registerWidth, "is" );
	    private static RegisterTextField r1V = new RegisterTextField( registerWidth, "R1" );
	    private static RegisterTextField r2V = new RegisterTextField( registerWidth, "R2" );
	    private static RegisterTextField prV = new RegisterTextField( registerWidth, "pr" );
	    private static RegisterTextField plrV = new RegisterTextField( registerWidth, "plr" );
	    private static RegisterTextField busV = new RegisterTextField( registerWidth, "bus" );
	    private static RegisterTextField svV = new RegisterTextField( registerWidth, "sv" );
	    private static RegisterTextField ppV = new RegisterTextField( registerWidth, "pp" );
	    private static RegisterTextField apV = new RegisterTextField( registerWidth, "ap" );
	
	    private static RegisterTextField k1V = new RegisterTextField( registerWidth, "k1" );
	    private static RegisterTextField k2V = new RegisterTextField( registerWidth, "k2" );
	    private static RegisterTextField k3V = new RegisterTextField( registerWidth, "k3" );
	    private static RegisterTextField k4V = new RegisterTextField( registerWidth, "k4" );
	    private static RegisterTextField lkV = new RegisterTextField( registerWidth, "lk" );
	    private static RegisterTextField spV = new RegisterTextField( registerWidth, "sp" );    
	    
	    JTextField filenameField = new JTextField( 20 );
	    JTextArea inputField = new JTextArea();
	    public JTextArea outputField = new JTextArea();
	    JTextArea errorField = new JTextArea();
	    static JTable table = new JTable( ( 4095 / Memory.blockSize ), 
	                              Memory.blockSize );
	    
	    public RMView( RM RM ){
	        this.setResizable( false );
	        this.setLocation( 5, 5 );
	        //this.RM = RM;
	        
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
	        /*channelPanel.setPreferredSize( 
	                      new Dimension( 250, table.getRowHeight() * 
	                          ( RM.MEMORY_SIZE / Memory.BLOCK_SIZE + 1 ) ) );*/
	        
	        channelPanel.add( inputScrollPane );
	        channelPanel.add( outputScrollPane );
	        channelPanel.add( errorScrollpane );
	        
	        JPanel registerPanel = new JPanel( new GridLayout( 0, 6, 10, 1 ) );
	        registerPanel.setBorder( BorderFactory.createCompoundBorder( 
	                                 BorderFactory.createTitledBorder( "Registrai" ), 
	                                 BorderFactory.createEmptyBorder( 10, 10, 10, 10) )
	                                );
	        
	        registerPanel.add( cxV.label );
	        registerPanel.add( cxV );
	        registerPanel.add( prV.label );
	        registerPanel.add( prV );
	        registerPanel.add( svV.label );
	        registerPanel.add( svV );
	        registerPanel.add( isV.label );
	        registerPanel.add( isV );
	        registerPanel.add( plrV.label );
	        registerPanel.add( plrV );
	        registerPanel.add( ppV.label );
	        registerPanel.add( ppV );
	        registerPanel.add( r1V.label );
	        registerPanel.add( r1V );
	        registerPanel.add( busV.label );
	        registerPanel.add( busV );
	        registerPanel.add( apV.label );
	        registerPanel.add( apV );
	        registerPanel.add( r2V.label );
	        registerPanel.add( r2V );
	        
	        registerPanel.add( k1V.label );
	        registerPanel.add( k1V );
	        registerPanel.add( k2V.label );
	        registerPanel.add( k2V );
	        registerPanel.add( k3V.label );
	        registerPanel.add( k3V );
	        registerPanel.add( k4V.label );
	        registerPanel.add( k4V );
	        registerPanel.add( lkV.label );
	        registerPanel.add( lkV );
	        registerPanel.add( spV.label );
	        registerPanel.add( spV );
	        
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
	    
	    public static void createVirtualMachine(){
	        executeButton.setEnabled( true );
	        //inputField.setText( "" );
	        //outputField.setText( "" );
	        //errorField.setText( "" );
	        update();
	    }
	    
	    /**
	    * Atnaujinamas realios mašinos langas.
	    */
	    
	    public final static void update() {
	        setRegisters();
	        fillTable();
	      
	    }
	    
	    private static void setRegisters() {
	        svV.setText( Integer.toString(Processor.sv) );
	        
	        ppV.setText( Integer.toString(Processor.pp));
	        apV.setText( Integer.toString(Processor.ap)); 
	        plrV.setText( Integer.toString(Processor.plr));
	        isV.setText( Integer.toString(Processor.is));
	        r1V.setText( Integer.toString(Processor.r1));
	        r2V.setText( Integer.toString(Processor.r2));
	        prV.setText( Integer.toString(Processor.pr));
	        cxV.setText( Integer.toString(Processor.cx));
	        busV.setText( Integer.toString(Processor.bus));     
	        
	        k1V.setText( Integer.toString(Processor.k1)); 
	        k2V.setText( Integer.toString(Processor.k2)); 
	        k3V.setText( Integer.toString(Processor.k3)); 
	        k4V.setText( Integer.toString(Processor.k4)); 
	        lkV.setText( Integer.toString(Processor.lk)); 
	        spV.setText( Integer.toString(Processor.sp)); 
	    }
	    
	    /**
	     * Užpildo interfeiso lentelę virtualios mašinos atminties masyvo duomenimis.
	     */
	        
	    public static void fillTable() {
	        for( int i = 0; i < 100; i++ ){
	            for( int j = 0; j < 10; j++ ){
	            	table.setValueAt( Memory.memory[i][j], i, j );
	            }
	        }
	        
	        renderer.setRMIS(Processor.is );
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
	        
	        for ( int i = 0; i < Memory.blockSize; i++ ) {
	            columnHeader.getColumnModel().getColumn( i ).setHeaderValue( i );
	        }
	        
	        JPanel rowHeader = new JPanel( new GridLayout( 0, 1, 0, 0 ) );
	        
	        for ( int j = 0; j < 4095 / Memory.blockSize; j++ ) {
	            JLabel rowLabel = new JLabel( Integer.toString( j ) );
	            rowLabel.setOpaque( true );
	            rowLabel.setHorizontalAlignment( JLabel.CENTER );
	          //RM bloku nr plotis
	            rowLabel.setPreferredSize( new Dimension( 
	                                            500 / ( Memory.blockSize + 1 ), 
	                                            table.getRowHeight() 
	                                           ) 
	                                     );
	            rowLabel.setBackground( columnHeader.getBackground() );
	            rowLabel.setForeground( columnHeader.getForeground() );
	            rowLabel.setFont( columnHeader.getFont() );
	            rowLabel.setBorder( UIManager.getBorder( "TableHeader.cellBorder" ) );
	            rowHeader.add( rowLabel );
	        }
	        
	        JScrollPane tableCellPane = new JScrollPane( table, 
	        		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	        
	      //RM duomenu stulpeliu plotis
	        
	        tableCellPane.setPreferredSize( new Dimension( 500, 450 ) );
	        tableCellPane.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
	        tableCellPane.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	        tableCellPane.setRowHeaderView( rowHeader );
	        
	        JLabel cornerLabel = new JLabel( "APS" );
	        cornerLabel.setOpaque( true );
	        cornerLabel.setHorizontalAlignment( JLabel.CENTER );
	      //RM ?????
	        cornerLabel.setPreferredSize( new Dimension( 
	                                        400 / ( Memory.blockSize + 1 ), 
	                                        table.getRowHeight() 
	                                        ) 
	                                    );
	        cornerLabel.setBackground( columnHeader.getBackground() );
	        cornerLabel.setForeground( columnHeader.getForeground() );
	        cornerLabel.setFont( columnHeader.getFont() );
	        cornerLabel.setBorder( UIManager.getBorder( "TableHeader.cellBorder" ));
	        
	        tableCellPane.setCorner( JScrollPane.UPPER_LEFT_CORNER, cornerLabel );
	        
	       
	        JPanel tablePanel = new JPanel();

	        tablePanel.add( tableCellPane );
	        
	        tablePanel.setBorder( BorderFactory.createTitledBorder(
	                                 BorderFactory.createLineBorder( Color.GREEN ),
	                                 "Reali atmintis" )
	                               );
	        
	        return tablePanel;
	    }
}