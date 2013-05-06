package osProjektas;

public class VMMemory {

	static public String VMMemory[][] = new String[10][10];
	static public int blocks = 10;
	static public int blockSize = 10;
	private int dataSegmentStart = 0;
	private int codeSegmentStart = 5;
	public static String[] stack;

	public VMMemory(int VMMemoryCount) {
		VMMemory = createVMMemory(VMMemoryCount);

	}

	public static String[][] createVMMemory(int VMMemoryCount) {
		
		while(!Memory.pageTable[VMMemoryCount].equals("  ")){
			VMMemoryCount++;
		}
		
		Memory.pageTable[VMMemoryCount] = VMMemoryCount * 10 + "";
		int vm = Integer.parseInt(Memory.pageTable[VMMemoryCount]);
		//Memory.pageTable[VMMemoryCount] += "A";
		for (int i = 0; i < 10; i++) {
			VMMemory[i] = Memory.memory[vm];
			vm++;
		}
		Memory.pageTable[0] = "" +  VMMemoryCount;
		return VMMemory;
	}

	public static void saveVMRegisters() {
		if (stack == null) {
			stack = VMMemory[9];
		}
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

	public static String getMemoryAtIs() {
		// System.out.println("IS'as paduodant atminciai" + Processor.is);
		System.out.println(VMMemory[~~(Processor.is / 10)][Processor.is % 10]);

		// System.out.println("Procesoriaus IS" + Processor.is);
		// System.out.println("stulpelis" + ~~(Processor.is / 10));
		// System.out.println("eilute" + Processor.is % 10);
		// VMMemory[~~(Processor.is / 10)][Processor.is % 10] = "10";

		return (VMMemory[~~(Processor.is / 10)][Processor.is % 10]);

	}

	public static void setMemoryAtIs(String text) {
		// System.out.println("IS'as paduodant atminciai" + Processor.is);
		System.out.println(VMMemory[~~(Processor.is / 10)][Processor.is % 10]);

		// System.out.println("Procesoriaus IS" + Processor.is);
		// System.out.println("stulpelis" + ~~(Processor.is / 10));
		// System.out.println("eilute" + Processor.is % 10);
		// VMMemory[~~(Processor.is / 10)][Processor.is % 10] = "10";
		int i = 4;
		while (text.length() <i){
			i--;
		}
		VMMemory[~~(Processor.is / 10)][Processor.is % 10] ="";
		VMMemory[~~(Processor.is / 10)][Processor.is % 10] = text.substring(0, i) ;

	}

	public static void popVMRegisters() {

		Processor.r1 = Integer.parseInt(VMMemory[9][0]);
		Processor.r2 = Integer.parseInt(VMMemory[9][1]);
		Processor.cx = Integer.parseInt(VMMemory[9][2]);
		Processor.sv = Integer.parseInt(VMMemory[9][3]);
		Processor.pr = Integer.parseInt(VMMemory[9][4]);
		Processor.is = Integer.parseInt(VMMemory[9][5]);
		for (int i = 0; i < 10; i++) {
			VMMemory[9][i] = null;
		}
		System.out.println("Registrai ispopinti");
		// Processor.cleanMemory();

	}
}
