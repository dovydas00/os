package osProjektas;



public class RM {

	// private Memory memory = new Memory( RM.MEMORY_SIZE );
	private Memory memory = new Memory();
	public Processor processor = new Processor();
	// public Processor processor = new Processor( memory );
	public Reader reader = new Reader();

	// private VMView[] virtualMachines = new VMView[2];
	// private Integer vmId = 0;

	public RM() {
	}

	public void loadProgram() {
		VM vm = new VM(1);
		Reader.readFromFile();
		
		for (int i=0; i<100;i++){
			System.out.println("");
			for (int j=0;j<10;j++){		
				System.out.print(Memory.memory[i][j]);
			}
		}
		new VMView(vm);
	}

	private int checkFreeVM(int i) {
		if (Memory.memory[Integer.parseInt(Memory.pageTable[i])] == null) {
			if (i == 10) {
				Processor.ap = 2; // interuptas - neuztenka atminties virtualiai
									// masinai
			}
			return i;
		} else
			checkFreeVM(i + 1);
		return i;

	}

	public static void saveRegisters() {
	
		if (Memory.rmRegistersStack == null){
			Processor.assignRMRegistersStack();
		}
		
			Memory.rmRegistersStack[0] = Processor.r1.toString();
			Memory.rmRegistersStack[1] = Processor.r2.toString();
			Memory.rmRegistersStack[2] = Processor.cx.toString();
			Memory.rmRegistersStack[3] = Processor.sv.toString();
			Memory.rmRegistersStack[4] = Processor.pr.toString();
			Memory.rmRegistersStack[5] = Processor.is.toString();                      
		
	}

}
