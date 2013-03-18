package osProjektas;

public class Memory {

	static public int blocks = 1000;
	static public int blockSize = 10; // Bloką sudaro 16 žodžių
	static int CELL_SIZE = 4; // Žodį sudaro 4 baitai
	static String FILL_SYMBOL = ""; // užpido atminti tuščiu stringu
	public static String[][] memory; // atmintis tai
	public static String stack[]; // stekui išskiriame vieną bloką atminties 

	public static void checkIfFree(int block) {

		for (int i = 0; i < 16; i++) {
			System.out.println(i);
		}
	}



	public static String getMemoryAtIs() {

		return (memory[~~(Processor.is / 10)][Processor.is % 10]);
	}



	

}
