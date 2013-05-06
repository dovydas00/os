package osProjektas;

import java.io.IOException;

public class Main {
	

	public static void startRM() {
		Processor.createMemory();
		Memory.createPageTable();
		Processor.assignStackToMemory();
		//Processor.test();
		
	}

	public static void main(String[] args) throws IOException {
		Processor.createMemory();
		Memory.createPageTable();
		Processor.assignStackToMemory();
		// RM.saveRegisters();
		RM rm = new RM();
		RMView rmWindow = new RMView(rm);
		RMController listener = new RMController(rmWindow, rm);
		/*
		 * for (int i=0; i<10;i++){ Processor.is = i; Processor.push();
		 * 
		 * Memory.memory[Memory.blocks-1][i] = i + ""; }
		 * Processor.assignStackToMemory(); // Nustato stekui atminties vietas
		 * 
		 * for (int i=0; i<10;i++){ Processor.pop();
		 * System.out.println(Processor.is); }
		 * 
		 * Memory.memory[0][0] = "AD09"; Memory.memory[0][1] = "AD09";
		 * Memory.memory[0][9] = "900"; Processor.is=0;
		 * //Processor.interpretCommand();
		 * 
		 * Processor.loop(3); //Processor.interpretCommand();
		 * System.out.println(Processor.r1);
		 */

		boolean debug = false;
		Reader reader = new Reader();
		// reader.setFile( "C:\\Users\\Lukas\\Desktop\\OS\\os.git\\trunk\\OS" );
		// reader.parseFile();

		/*
		 * if ( debug ) { reader.printDataArray(); }
		 */

		// Memory memory = reader.getDataArray();

		// rm.loadProgram( memory );

		try {
			// machine.interpretProgram();
		} catch (NumberFormatException e) {
			System.out.println("Klaidingai Ä¯vestas skaiÄ�ius");
		}
	}
}
