package osProjektas;

public class Memory {
	
	static public int stackSize = 100;
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
		memory = new String[stackSize][10];
	}

	public static String getMemoryAtIs() {

		return (memory[~~(Processor.is / 10)][Processor.is % 10]);
	}

	public static void assignStackToMemory() {

		stack = memory[stackSize];

	}
	
	public static void push(){
	
	}

}
