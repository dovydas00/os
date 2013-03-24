package osProjektas;

public class Memory {

	static public int[][] buffer;
	static public int blocks = 100;
	static public int blockSize = 10; // Bloką sudaro 16 žodžių
	static int CELL_SIZE = 4; // Žodį sudaro 4 baitai
	static String FILL_SYMBOL = ""; // užpido atminti tuščiu stringu
	public static String[][] memory; // atmintis tai
	public static String stack[]; // stekui išskiriame vieną bloką atminties
	public static String[] pageTable;

	public static void checkIfFree(int block) {

		for (int i = 0; i < 16; i++) {
			System.out.println(i);
		}
	}

	public static String getMemoryAtIs() {

		return (memory[~~(Processor.is / 10)][Processor.is % 10]);
	}

	public static void createPageTable() {
		if (Memory.memory == null){
			Processor.createMemory();
		}
		pageTable = Memory.memory[9];
		pageTable[0] = "10";
		pageTable[1] = "19";
		pageTable[2] = "28";
		pageTable[3] = "37";
		pageTable[4] = "46";
		pageTable[5] = "55";
		pageTable[6] = "64";
		pageTable[7] = "73";
		pageTable[8] = "81";
		pageTable[9] = "90";
	}

}
