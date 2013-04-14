package osProjektas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.WindowListener;
import javax.swing.JFileChooser;

public class RMController {
    public static RMView belekas;
    public static RMView rmView;
    RM rm;
    
    public static void interpretCommand() {
		String command = Processor.getCommand(); 
		switch (command.substring(0, 2)){
		case "AD": {
			Processor.push();
			System.out.println("Procesoriaus rodykle pries priskyrima" + Processor.is);
			Processor.is = Integer.parseInt(command.substring(2, 4));
			System.out.println("Procesoriaus rodykle" + Processor.is);
			Processor.r1 = Processor.r1 + Integer.parseInt(VMMemory.getMemoryAtIs());
			System.out.println("Po komandos getMemoryAtIs" + Processor.is);
			Processor.pop();
			System.out.println("Po popinimo" + Processor.is);
			Processor.is++;
			break;
		}

		case "PS": {
			Processor.BS(4);
		
			break;
		}
		
		case "GW": {
			
			Processor.A1(1);
			break;
		}

		default: {
			Processor.AP(2);
			
			break;
		}

		}
		Processor.test();

	}

    public static void  printString() {
		String line = "";
		
		System.out.println("Pertraukima issauke komanda PS");
		Processor.push();
		Processor.is = Integer.parseInt(Processor.getCommand().substring(2, 4));
		Processor.is = 60 + Processor.is;
		while (!VMMemory.getMemoryAtIs().equals("EOF$")) {
			line = line + VMMemory.getMemoryAtIs();
			Processor.is++;

		}
		Processor.pop();
		Processor.is++;
		Processor.BS(0);
		rmView.outputField.setText(line);
		//
	}
    public RMController ( RMView window, RM machine ) {
        this.rmView = window;
        this.rm = machine;
        
        rmView.executeButton.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent event ){
                interpretCommand();
                //update();
                if(Processor.sp == 4){
                	printString();
                }
                if( Processor.pp != 0 ) {
                    if( Processor.pp == 1 ) {
                        rmView.errorField.append( "Neteisingas adresas.\n" );
                    } else if ( Processor.pp == 2 ) {
                    	rmView.errorField.append( "Neegzistuojantis operacijos kodas\n" );
                    } else if ( Processor.pp == 3 ) {
                         rmView.errorField.append( "Neskaitinė atminties " + 
                                                  "ląstelės reikšmė nurodytu " +
                                                  " adresu.\n" );
                    }
                    rmView.outputField.append( "\nPrograma baigė darbą.\n" );
                    /*rm.getActiveVM().dispose();
                    rm.destroyActiveVM();*/
                    rmView.executeButton.setEnabled( false );
                } else {
                    if ( Processor.sp == 1 ) {     //Pertraukima issaukia GD komanda
                        rmView.inputField.setEnabled( true );
                        rmView.inputField.requestFocusInWindow();
                        rmView.reloadButton.setEnabled( false );
                        rmView.executeButton.setEnabled( false );
                        rmView.table.setEnabled( false );
                    }  else if ( Processor.sp == 6 ) { //Pertraukima issaukia HALT komanda
                        rmView.outputField.append( "\nPrograma baigė darbą.\n" );
                      /*  rm.getActiveVM().dispose();
                        rm.destroyActiveVM();*/
                        rmView.executeButton.setEnabled( false );
                    } /*else if ( rm.processor.getSI() == 4 ) {
                        int number = Integer.decode( rm.processor.getCommand().substring( 2 ) );
                        rmView.outputField.append( rm.processor.getPrintWord( number ) );
                        rm.processor.incIC();
                    }*/

                    
                    rmView.update();
                    //rm.processor.setSI( 0 );
                    //rm.processor.setPI( 0 );
                }
            }
        });
        
        rmView.reloadButton.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent event ){
                String filename = "";
                filename = rmView.filenameField.getText();
				rm.loadProgram();
				rmView.update();
                // įkelti į klasę
                //rm.createVirtualMachine();
                rmView.createVirtualMachine();
               
              
               /* rm.getActiveVM().addWindowListener( new WindowListener() {
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
                        //rm.getActiveVM().dispose();
                        //rm.destroyActiveVM();
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
                });*/
            }
        });
        
        rmView.fileLoadButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent event ) {
                
            	LoadCustomFile chooser = new LoadCustomFile();
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
                            /*rm.processor.setReadWord( rmView.inputField.
                                                getText().substring( offset ) );*/
                            rmView.inputField.append( "\n" );
                            offset = rmView.inputField.getText().length();
                            rmView.executeButton.setEnabled( true );
                            rmView.executeButton.requestFocusInWindow();
                            Processor.cx++;
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
        /*if ( !rm.hasActiveVM() ) {
            rm.getActiveVM().update();
        }*/
    }
}