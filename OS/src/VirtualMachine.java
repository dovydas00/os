package mos;

/**
 * Klasė, emuliuojanti virtualios mašinos darbą.
 * 
 * @author Paulius, Saulė, Antanas
 */

public class VirtualMachine {
    static public int MEMORY_SIZE = 100;
    static public int DATA_SEGMENT_START = 40;
    public Processor processor;
    private boolean debug = false;
    private int id;
    private boolean instructionStep = false;
    
    /**
     * Konstruktorius.
     * 
     * @param processor
     *      Procesorius, gautas iš realios mašinos.
     * @param id
     *      Virtualios mašinos intentifikatorius.
     */
    
    VirtualMachine ( Processor processor, Integer id ) {
        this.processor = processor;
        this.id = id;
       // this.vmWindow = new VMWindow( processor );
    }
    
    /**
     * Grąžinamas virtualios mašinos ID.
     * 
     * @return
     *      Virtualios mašinos ID.
     */
    
    public int getID () {
        return this.id;
    }
    
    /**
     * Interpertuojamas virtualios mašinos programos kodas.
     */
    
    public void interpretProgram () {
    

        boolean interpretNext = true;
        
        while ( interpretNext ) {
            interpretCommand ();
        }
    }
    
    public boolean interpretCommand () {
        int number = 0;
        boolean interpretNext = true;
        String command = processor.getCommand();

        if ( debug ) {
            System.err.println( "IC registro reikšmė: " + processor.getIC() );
            System.err.println( "Atliekama komanda: " + command );
        }
        
        try {
            if ( ! ( "CGRN".equals( command ) || "HALT".equals( command ) ) ) {
                number = Integer.decode( command.substring( 2 ) );
            }
        } catch ( NumberFormatException e ) {
            processor.setPI( 1 );
        }
        
        if ( command.startsWith( "HALT" ) || processor.getSI() == 3 ) {
            processor.Halt();
            interpretNext = false;
        } else if ( command.startsWith( "SR" ) ) {
            processor.storeRegister( number );
            processor.incIC();
        } else if ( command.startsWith( "CGRN" ) ) {
            processor.changeRN();
            processor.incIC();
        } else if ( command.startsWith( "AD" ) ) {
            processor.addToRegister( number );
            processor.incIC();
        } else if ( command.startsWith( "SB" ) ) {
            processor.substractFromRegister( number );
            processor.incIC();
        } else if ( command.startsWith( "CR" ) ) {
            processor.compareToRegister( number );
            processor.incIC();
        } else if ( command.startsWith( "JG" ) ) {
            processor.jumpIfGreater( number );
        } else if ( command.startsWith( "JE" ) ) {
            processor.jumpIfEqual( number );
        } else if ( command.startsWith( "JL" ) ) {
            processor.jumpIfLess( number );
        } else if ( command.startsWith( "JP" ) ) {
            processor.jump( number );
        } else if ( command.startsWith( "GW" ) ) {
            processor.getWord( number );
        } else if ( command.startsWith( "GD" ) ) {
            processor.getData( number );
        } else if ( command.startsWith( "PW" ) ) {
            processor.printWord( number );
        } else if ( command.startsWith( "PS" ) ) {
            processor.printString( number );
        } else if ( command.startsWith( "LR" ) ) {
            processor.loadRegister( number );
            processor.incIC();
        } else {
            processor.setPI( 2 );
        }

        return interpretNext;
    }
    
    public String getMemoryContent ( int cellNumber ) {
        return processor.getVirtualMemoryValue( cellNumber );
    }
}