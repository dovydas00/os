package osProjektas;

public class VMMemory {

	static public String VMMemory[][] = new String[10][10];
	static public int blocks = 10;
	static public int blockSize = 10;
	private int dataSegmentStart = 0;
	private int codeSegmentStart = 5;
	private static String[] stack;

	public VMMemory(int VMMemoryCount) {
		VMMemory = createVMMemory(VMMemoryCount);

	}

	public static String[][] createVMMemory(int VMMemoryCount) {
		int vm = Integer.parseInt(Memory.pageTable[VMMemoryCount]);
		for (int i = 0; i < 10; i++) {
			VMMemory[i] = Memory.memory[vm];
			vm++;

		}
		return VMMemory;
	}

	

	public static void saveVMRegisters() {
		
		VMMemory[9][0] = Processor.r1.toString();
		VMMemory[9][1] = Processor.r2.toString();
		VMMemory[9][2] = Processor.cx.toString();
		VMMemory[9][3] = Processor.sv.toString();
		VMMemory[9][4] = Processor.pr.toString();
		VMMemory[9][5] = Processor.is.toString();
	}

	public static void main(String[] args) {
		Processor.createMemory();
		Memory.createPageTable();
		new VMMemory(3);

		// Uzpildom visa atminti

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 10; j++) {
				Memory.memory[i][j] = "X";
			}
		}
		// Priskiriam VM atminti realiai

		// Pakeiciam virtualia atminti
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				VMMemory[i][j] = "i";
			}
		}
		// Atspausdinam Realia atminti
		for (int i = 0; i < 100; i++) {
			System.out.println("");
			for (int j = 0; j < 10; j++) {
				System.out.print(Memory.memory[i][j]);
			}
		}

	}

	public static void pushtoVmStack(String element) {

	}
}
