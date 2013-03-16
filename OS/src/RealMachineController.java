package GUI;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import mos.RealMachine;

/**
 *
 * @author Antanas
 */
public class RealMachineController {
    
    RealMachineView rmView;
    RealMachine rm;
    
    public RealMachineController ( RealMachineView window, RealMachine machine ) {
        this.rmView = window;
        this.rm = machine;
        
        rmView.executeButton.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent event ){
                rm.getActiveVM().machine.interpretCommand();
                update();
                
                if( rm.processor.getPI() != 0 ) {
                    if( rm.processor.getPI() == 1 ) {
                        rmView.errorField.append( "Neteisingas adresas.\n" );
                    } else if ( rm.processor.getPI() == 2 ) {
                        rmView.errorField.append( "Neatpažinta komanda.\n" );
                    } else if ( rm.processor.getPI() == 3 ) {
                         rmView.errorField.append( "Neskaitinė atminties " + 
                                                  "ląstelės reikšmė nurodytu " +
                                                  " adresu.\n" );
                    }
                    rmView.outputField.append( "\nPrograma baigė darbą.\n" );
                    rm.getActiveVM().dispose();
                    rm.destroyActiveVM();
                    rmView.executeButton.setEnabled( false );
                } else {
                    if ( rm.processor.getSI() == 1 ) {
                        rmView.inputField.setEnabled( true );
                        rmView.inputField.requestFocusInWindow();
                        rmView.reloadButton.setEnabled( false );
                        rmView.executeButton.setEnabled( false );
                        rmView.table.setEnabled( false );
                    } else if ( rm.processor.getSI() == 2 ) {
                        int number = Integer.decode( rm.processor.getCommand().substring( 2 ) );
                        rmView.outputField.append( rm.processor.getPrintString( number ) );
                        rm.processor.incIC();
                    }  else if ( rm.processor.getSI() == 3 ) {
                        rmView.outputField.append( "\nPrograma baigė darbą.\n" );
                        rm.getActiveVM().dispose();
                        rm.destroyActiveVM();
                        rmView.executeButton.setEnabled( false );
                    } else if ( rm.processor.getSI() == 4 ) {
                        int number = Integer.decode( rm.processor.getCommand().substring( 2 ) );
                        rmView.outputField.append( rm.processor.getPrintWord( number ) );
                        rm.processor.incIC();
                    }

                    update();
                    rm.processor.setSI( 0 );
                    rm.processor.setPI( 0 );
                }
            }
        });
        
        rmView.reloadButton.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent event ){
                String filename = "";
                try {
                    filename = rmView.filenameField.getText();
                    rm.reader.setFile( filename );
                    rm.reader.parseFile();
                    rm.loadProgram( rm.reader.getDataArray() );
                    rmView.update();
                } catch ( IOException ioe ) {
                    rmView.errorField.append("Klaida atidarant failą " + filename + ".\n" );
                    System.out.println( ioe.getMessage() );
                }
                // įkelti į klasę
                rm.createVirtualMachine();
                rmView.createVirtualMachine();
                rm.getActiveVM().addWindowListener( new WindowListener() {
                    @Override
                    public void windowClosed(WindowEvent windowEvent) {
//                        rmView.outputField.append( "\nPrograma baigė darbą.\n" );
//                        rm.getActiveVM().dispose();
//                        rm.destroyActiveVM();
//                        rmView.executeButton.setEnabled( false );
                    }
                    
                    @Override
                    public void windowActivated(WindowEvent arg0) {
                    //    System.out.println("Window Activated");
                    }
                    
                    @Override
                    public void windowClosing(WindowEvent arg0) {
                        rmView.outputField.append( "\nPrograma baigė darbą.\n" );
                        rm.getActiveVM().dispose();
                        rm.destroyActiveVM();
                        rmView.executeButton.setEnabled( false );
                        rmView.inputField.setEnabled( false );
                        rmView.reloadButton.setEnabled( true );
                        update();
                    }
                    
                    @Override
                    public void windowDeactivated(WindowEvent arg0) {
                    //    System.out.println("Window Deactivated");
                    }
                    
                    @Override
                    public void windowDeiconified(WindowEvent arg0) {
                    //    System.out.println("Window Deiconified");
                    }
                    
                    @Override
                    public void windowIconified(WindowEvent arg0) {
                    //    System.out.println("Window Iconified");
                    }
                    
                    @Override
                    public void windowOpened(WindowEvent arg0) {
                    //    System.out.println("Window Opened");
                    }
                });
            }
        });
        
        rmView.fileLoadButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent event ) {
                
                CustomJFileChooser chooser = new CustomJFileChooser();
                chooser.customizeAppearance();
                int ret = chooser.showDialog( null, "Pasirinkti" );

                if ( ret == JFileChooser.APPROVE_OPTION ) {
                    File file = chooser.getSelectedFile();
                    rmView.filenameField.setText( file.getAbsolutePath() );
                }
            }
        });
  
        rmView.inputField.addKeyListener( new KeyListener() {
            int offset = 0;
            
            @Override
	    public void keyPressed( KeyEvent event ) {
                    if( event.getKeyCode() == KeyEvent.VK_ENTER ) {
                        if ( ! event.isShiftDown() ) {
                            rm.processor.setReadWord( rmView.inputField.
                                                getText().substring( offset ) );
                            rmView.inputField.append( "\n" );
                            offset = rmView.inputField.getText().length();
                            rmView.executeButton.setEnabled( true );
                            rmView.executeButton.requestFocusInWindow();
                            rm.processor.incIC();
                            rmView.inputField.setEnabled( false );
                            rmView.reloadButton.setEnabled( true );
                            update();
                        } else {
                            rmView.inputField.append( "\n" );
                        }
                    }
                
	    }
            
            @Override
            public void keyReleased( KeyEvent event ) {
            }
            
            @Override
            public void keyTyped( KeyEvent event ) {
            }
        });
    }
    
    public void update(){
        rmView.update();
        if ( !rm.hasActiveVM() ) {
            rm.getActiveVM().update();
        }
    }
}