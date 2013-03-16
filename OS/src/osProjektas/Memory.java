package osProjektas;

public class Memory {
	
	static public int blocks = 100;
	static public int blockSize = 10; // Bloką sudaro 16 žodžių
	static int CELL_SIZE = 4; // Žodį sudaro 4 baitai
	static String FILL_SYMBOL = ""; // užpido atminti tuščiu stringu
	public static String[][] memory; // atmintis tai
	public static String stack[]; // vienamtis
	// masyvas

	public static void checkIfFree(int block) {

		for (int i = 0; i < 16; i++) {
			System.out.println(i);
		}
	}

	public static void createMemory() {
		memory = new String[blocks][10];
	}

	public static String getMemoryAtIs() {

		return (memory[~~(Processor.is / 10)][Processor.is % 10]);
	}

	public static void assignStackToMemory() {

		stack = memory[blocks-1];

	}
	
	public static void push(){
	
	}

}
