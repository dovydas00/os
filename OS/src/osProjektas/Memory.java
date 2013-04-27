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

	public static void setMemoryAtIs(String text) {
		 System.out.println("IS'as paduodant atminciai" + Processor.is);
		System.out.println(memory[~~(Processor.is / 10)][Processor.is % 10]);
		Processor.is = Processor.r2; 
		// System.out.println("Procesoriaus IS" + Processor.is);
		// System.out.println("stulpelis" + ~~(Processor.is / 10));
		// System.out.println("eilute" + Processor.is % 10);
		// VMMemory[~~(Processor.is / 10)][Processor.is % 10] = "10";
		int i = 4;
		while (text.length() <i){
			i--;
		}
		memory[~~(Processor.is / 10)][Processor.is % 10] ="";
		memory[~~(Processor.is / 10)][Processor.is % 10] = text.substring(0, i) ;

	}

	public static String getMemoryAtIs() {
		if ((~~(Processor.is / 10) >= 10 || (~~(Processor.is / 10) < 0))
				&& (Processor.is % 10 >= 10 || (Processor.is % 10 < 0))) {
			Processor.AP(1);
		}
		//Processor.test();		
		if (Processor.bus == 1){
			return memory[~~(Processor.is / 10)][Processor.is % 10]; 
		} else 
		return (VMMemory.VMMemory[~~(Processor.is / 10)][Processor.is % 10]);
	}

	public static void createPageTable() {
		if (Memory.memory == null) {
			Processor.createMemory();
		}
		pageTable = Memory.memory[9];
		pageTable[0] = "9";
		pageTable[1] = "  ";
		pageTable[2] = "  ";
		pageTable[3] = "  ";
		pageTable[4] = "  ";
		pageTable[5] = "  ";
		pageTable[6] = "  ";
		pageTable[7] = "  ";
		pageTable[8] = "  ";
		pageTable[9] = "  ";
	}

}
