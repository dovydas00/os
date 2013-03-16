package osProjektas;

public class RM {
    static public int MEMORY_SIZE = 4095;
    static public int PTR_MEMORY_RANGE = 5;
    
    //private Memory memory = new Memory( RM.MEMORY_SIZE );
    private Memory memory = new Memory();
    public Processor processor = new Processor();
    //public Processor processor = new Processor( memory );
    public Reader reader = new Reader();
    //private VMView[] virtualMachines = new VMView[2];
    //private Integer vmId = 0;

    
    public RM () {
    }

    
}