package osProjektas;

public class Memory {

	static public int[][] buffer;
	static public int blocks = 100;
	static public int blockSize = 10; // Bloką sudaro 16 žodžių
	static int CELL_SIZE = 4; // Žodį sudaro 4 baitai
	static String FILL_SYMBOL = ""; // užpido atminti tuščiu stringu
	public static String[][] memory; // atmintis tai
	public static String stack[]; // stekui išskiriame vieną bloką atminties
	public static String modesStack[];
	public static String rmRegistersStack[];

	public static String[] pageTable;

	public static void checkIfFree(int block) {

		for (int i = 0; i < 16; i++) {
			System.out.println(i);
		}
	}

	public static String getMemoryAtIs() {
		if ((~~(Processor.is / 10) >= 10 || (~~(Processor.is / 10) < 0))
				&& (Processor.is % 10 >= 10 || (Processor.is % 10 < 0))) {
			Processor.AP(1);
		}
		//Processor.test();		
		return (VMMemory.VMMemory[~~(Processor.is / 10)][Processor.is % 10]);
	}

	public static void createPageTable() {
		if (Memory.memory == null) {
			Processor.createMemory();
		}
		pageTable = Memory.memory[9];
		pageTable[0] = "9";
		pageTable[1] = "10";
		pageTable[2] = "20";
		pageTable[3] = "30";
		pageTable[4] = "40";
		pageTable[5] = "50";
		pageTable[6] = "60";
		pageTable[7] = "70";
		pageTable[8] = "80";
		pageTable[9] = "90";
	}

}
