package osProjektas;

public class RM {
    static public int MEMORY_SIZE = 4095;
    static public int PTR_MEMORY_RANGE = 5;
    
    private Memory memory = new Memory( RM.MEMORY_SIZE );
    public Processor processor = new Processor( memory );
    public Reader reader = new Reader();
    //private VMView[] virtualMachines = new VMView[2];
    //private Integer vmId = 0;

    
    RM () {
    }

    
    public VM createVM ( ) {
        processor.setIC( 0 );
        processor.setIs( 0 );
        VM vm = new VM( processor, processor.getPlr() );
        VMView window = new VMView( vm );
        virtualMachines[ processor.getPlr() ] = window;
        VirtualMachineController vmc = new VirtualMachineController( window, vm );
        vmId++;
        vmId %= 2;
        //System.out.println( vmId );
        return vm;
    }
    
    /**
     * Virtuliai mašinai išskiriami laisvi realios atminties ruožai.
     */
    
    private void allocateMemory () {
        Integer i = PTR_MEMORY_RANGE;
        for ( int j = 0; j < Memory.BLOCK_SIZE; j++ ) {
            boolean foundEmpty = false;
            while ( !foundEmpty && i < ( MEMORY_SIZE / Memory.BLOCK_SIZE ) ) {
                if ( ! memory.getBlockUsed( i ) ) {
                    foundEmpty = true;
                    memory.setMemory( processor.getPlr() * Memory.BLOCK_SIZE + j, i.toString() );
                    memory.setBlockUsed( i, true );
                }
                i++;
            }
        }
    }
    
    /**
     * Programa iš išorinės atminties įkeliama į realią atmintį vykdymui.
     * 
     * @param externalMemory
     *      Masyvas, emuliuojantis išorinę atmintį.
     */
    
    public void loadProgram ( Memory externalMemory ) {
        processor.setPTR( vmId );
        allocateMemory();
        for ( int i = 0; i < Memory.BLOCK_SIZE; i++ ) {
            int dec = Integer.parseInt( memory.getMemory( processor.getPlr() * 10 + i ) );
            for ( int j = 0; j < Memory.BLOCK_SIZE; j++ ) {
                memory.setMemory( 10 * dec + j, externalMemory.getMemory( 10 * i + j ) );
            }
        }
    }
    
    public String getMemoryContent ( int cellNumber ) {
        return this.memory.getMemory( cellNumber );
    }
    
    public VMView getActiveVM () {
        return this.virtualMachines[ processor.getPlr() ];
    }
    
    public void destroyActiveVM () {
        //freeing up memory
        for ( int i = 0; i < Memory.BLOCK_SIZE; i++ ) {
            int dec = Integer.parseInt( memory.getMemory( processor.getPlr() * 10 + i ) );
                memory.setBlockUsed( dec, false );
        }
        this.virtualMachines[ processor.getPlr() ] = null;
    };
    
    public boolean hasActiveVM () {
        return this.virtualMachines[ processor.getPlr() ] == null;
    }
    
    /**
     * Realios mašinos atmintis atspausdinama į standartinę išvestį.
     * Naudojama diagnostikai.
     */
    
    public void printMemory () {
        for ( int i = 0; i < MEMORY_SIZE; i++ ) {
            System.out.println( i + " " + memory.getMemory( i ) );
        }
    }
    
}