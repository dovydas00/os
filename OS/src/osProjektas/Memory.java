package osProjektas;

public class Memory {

	static public int BLOCK_SIZE = 16; // Bloką sudaro 16 žodžių
	static int CELL_SIZE = 4; // Žodį sudaro 4 baitai
	static String FILL_SYMBOL = ""; // užpido atminti tuščiu stringu
	public static String[][] memory;  // atmintis tai
	public static String stack[]; 															// dvimatis
																// masyvas

	public static void checkIfFree(int block) {

		for (int i = 0; i < 16; i++) {
			System.out.println(i);
		}
	}
	
	public static void createMemory(){
		memory = new String[100][16];
	}

	public static String getMemoryAtIs() {

		return (memory[~~(Processor.is / 10)][Processor.is % 10]);
	}
	public static void assignStackToMemory(){
		
		for (int i=0; i<10;i++){
			stack[i] = memory[10][i]; 
			 
		}
	}
	


}
