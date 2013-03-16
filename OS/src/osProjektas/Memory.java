package osProjektas;

public class Memory {

    static public int BLOCK_SIZE = 16; // Bloką sudaro 16 žodžių
    static int CELL_SIZE = 4; //Žodį sudaro 4 baitai
    static String FILL_SYMBOL = ""; //užpido atminti tuščiu stringu
    

    
   
    public static void checkIfFree(int block){
    	
    	for (int i=0; i<16; i++){
    		System.out.println(i);
    	}
    	
    	
    }
    
    
    
    public static String[][] getMemory() {
		return memory;
	}


	public static void setMemory(String[][] memory) {
		Memory.memory = memory;
	}


	private static String[][] memory = new String[4095][16]; //atmintis tai dvimatis masyvas
   
    

}
