package osProjektas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Savepoint;
import java.awt.event.WindowListener;
import javax.swing.JFileChooser;

public class RMController {
	public static RMView belekas;
	public static RMView rmView;
	RM rm;

	public static boolean checkInterupt() {
		VMMemory.saveVMRegisters();

		switch (Processor.pp) {
		case 1: {
			System.out.println("Neteisingas adresas");
			Processor.AP(0);
			return true;
		}
		case 2: {
			rmView.outputField.setText("Neegzistuojantis operacijos kodas");
			rmView.outputField.setText("Programa baigia darba");
			Processor.AP(0);
			return true;
		}
		case 3: {
			System.out
					.println("Neskaitinė atminties ląstelės reikšmė nurodytu adresu");
			Processor.AP(0);
			return true;
		}
		}
		switch (Processor.sp) {
		case 1: {
			System.out.println("Pertraukima issauke komanda GD");
			Processor.BS(0);
			return false;
		}
		case 2: {
			System.out.println("Pertraukima issauke komanda DV");
			Processor.BS(0);
			return false;
		}
		case 3: {

			return false;
		}
		case 4: {
			Processor.is = 0;
			// JMP.

			RMController.printString();
			Processor.BS(0);
			VMMemory.popVMRegisters();
			Processor.is++;
			break; // veliau istrinti

		}
		case 5: {
			System.out.println("Pertraukima issauke komanda PW");
			Processor.BS(0);
			return false;
		}
		case 6: {
			System.out.println("Pertraukima issauke komanda HALT");
			Processor.BS(0);
			return true;
		}
		}
		switch (Processor.ap) {
		case 1: {
			System.out.println("Ivyko perpildymas");
			Processor.AA(0);
			return true;

		}
		}
		switch (Processor.lk) {
		case 1: {
			System.out.println("Laikmačio pertraukimas");
			// Veliau paziureti //Processor.BL(1);
			return true;
		}

		}

		switch (Processor.k1) {
		case 1: {
			System.out.println("Pertraukimą iššaukė išorinė atmintis");
			Processor.A1(0);
			return false;
		}
		}
		switch (Processor.k2) {
		case 1: {
			// if (Processor.sp == 3){
			Processor.push();
			System.out.println("Pertraukimą iššaukė įvedimo kanalas");
			rmView.inputField.setEnabled(true);
			rmView.inputField.requestFocusInWindow();
			rmView.reloadButton.setEnabled(false);
			rmView.executeButton.setEnabled(false);
			rmView.table.setEnabled(false);
			System.out.println("Procesoriaus rodykle pries priskyrima"
					+ Processor.is);
			Processor.is = Integer.parseInt(Processor.getCommand().substring(2,
					4));
			System.out.println("Procesoriaus rodykle" + Processor.is);
			VMMemory.VMMemory[~~(Processor.is / 10)][Processor.is % 10] = rmView.inputField
					.getText();
			System.out.println("Po komandos getMemoryAtIs" + Processor.is);
			Processor.pop();
			System.out.println("Po popinimo" + Processor.is);
			Processor.is++;

			// }
			Processor.A2(0);

			return false;
		}
		}
		switch (Processor.k3) {
		case 1: {
			System.out.println("Pertraukimą iššaukė įšvedimo kanalas");
			Processor.push();
			rmView.outputField.setEnabled(true);
			rmView.outputField.requestFocusInWindow();
			rmView.table.setEnabled(false);
			Processor.is = Integer.parseInt(Processor.getCommand().substring(2,
					4));
			rmView.outputField.setText(VMMemory.getMemoryAtIs());
			rmView.update();
			Processor.pop();
			System.out.println("Po popinimo" + Processor.is);
			Processor.is++;
			Processor.A3(0);
			return false;
		}
		}
		switch (Processor.k4) {
		case 1: {
			System.out.println("Pertraukimą iššaukė vartotojo atmintis");
			Processor.A4(0);
			return false;
		}
		}

		return false;
	}

	public static void interpretCommand() {
		String command = Processor.getCommand();
		switch (command.substring(0, 2)) {
		case "AD": {
			Processor.push();
			System.out.println("Procesoriaus rodykle pries priskyrima"
					+ Processor.is);
			Processor.is = Integer.parseInt(command.substring(2, 4));
			System.out.println("Procesoriaus rodykle" + Processor.is);
			Processor.r1 = Processor.r1
					+ Integer.parseInt(VMMemory.getMemoryAtIs());
			System.out.println("Po komandos getMemoryAtIs" + Processor.is);
			Processor.pop();
			System.out.println("Po popinimo" + Processor.is);
			Processor.is++;
			break;
		}

		case "PS": {
			Processor.BS(4);
			String line = "";

			System.out.println("Pertraukima issauke komanda PS");
			Processor.push();
			Processor.r2 = Integer.parseInt(Processor.getCommand().substring(2,
					4));
			Processor.r2 += Integer.parseInt(Memory.pageTable[0]) * 100 + 60;
			Processor.is = 0;
			Processor.AB(1);
			rmView.update();
			break;
		}

		case "GW": {
			Processor.BS(3);
			Processor.A2(1); // K2 registras
			Processor.push();

			Processor.r2 = Integer.parseInt(Processor.getCommand().substring(2,
					4));
			Processor.r2 += Integer.parseInt(Memory.pageTable[0]) * 100;
			// Processor.test();
			Processor.is = 4;
			Processor.AB(1);
			rmView.update();
			break;
		}

		case "LR": {
			Processor.push();
			Processor.is = Integer.parseInt(command.substring(2, 4));
			System.out.println(VMMemory.getMemoryAtIs());
			Processor.r1 = Integer.parseInt(VMMemory.getMemoryAtIs());
		//	Processor.test();
			Processor.pop();
			Processor.is++;
			break;
		}

		case "SR": {
			Processor.push();
			Processor.is = Integer.parseInt(command.substring(2, 4));
			VMMemory.setMemoryAtIs(Processor.r1.toString());
			Processor.pop();
			Processor.is++;
			break;
		}

		case "PW": {
			Processor.A3(1);
			Processor.BS(5);
			String line = "";

			System.out.println("Pertraukima issauke komanda PW");
			Processor.push();
			Processor.r2 = Integer.parseInt(Processor.getCommand().substring(2,
					4));
			Processor.r2 += Integer.parseInt(Memory.pageTable[0]) * 100;
			Processor.is = 8;
			Processor.AB(1);
			rmView.update();
			break;

		}

		case "JP": {
			Processor.push();
			Processor.is = Integer.parseInt(command.substring(2, 4));

			break;

		}

		default: {
			Processor.AP(2);

			break;
		}

		}

		// Processor.test();

	}

	public static void printString() {
		String line = "";

		Processor.is = Processor.r2;
		Processor.pop();
		Processor.is++;
		Processor.BS(0);
		rmView.outputField.setText(line);
		//
	}

	public RMController(RMView window, RM machine) {
		this.rmView = window;
		this.rm = machine;

		rmView.executeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (Processor.bus == 0) {
					interpretCommand();
				} else {
					interrpretInterrupt();
				}

				if (Processor.sp == 4) {
					// printString();
				}
				if (Processor.pp != 0) {
					if (Processor.pp == 1) {
						rmView.errorField.append("Neteisingas adresas.\n");
					} else if (Processor.pp == 2) {
						rmView.errorField
								.append("Neegzistuojantis operacijos kodas\n");
					} else if (Processor.pp == 3) {
						rmView.errorField.append("Neskaitinė atminties "
								+ "ląstelės reikšmė nurodytu " + " adresu.\n");
					}
					rmView.outputField.append("\nPrograma baigė darbą.\n");
					/*
					 * rm.getActiveVM().dispose(); rm.destroyActiveVM();
					 */
					rmView.executeButton.setEnabled(false);
				} else {
					if (Processor.sp == 1) { // Pertraukima issaukia GD komanda
						// rmView.inputField.setEnabled(true);
						// rmView.inputField.requestFocusInWindow();
						// rmView.reloadButton.setEnabled(false);
						// rmView.executeButton.setEnabled(false);
						// rmView.table.setEnabled(false);
					} else if (Processor.sp == 6) { // Pertraukima issaukia HALT
													// komanda
						rmView.outputField.append("\nPrograma baigė darbą.\n");
						/*
						 * rm.getActiveVM().dispose(); rm.destroyActiveVM();
						 */
						rmView.executeButton.setEnabled(false);
					} /*
					 * else if ( rm.processor.getSI() == 4 ) { int number =
					 * Integer.decode( rm.processor.getCommand().substring( 2 )
					 * ); rmView.outputField.append( rm.processor.getPrintWord(
					 * number ) ); rm.processor.incIC(); }
					 */

					rmView.update();
					if (Processor.bus == 0) {
						VMView.update();
					}
					// rm.processor.setSI( 0 );
					// rm.processor.setPI( 0 );
				}
			}
		});

		rmView.reloadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String filename = "";
				filename = rmView.filenameField.getText();
				rm.loadProgram();
				rmView.update();
				// įkelti į klasę
				// rm.createVirtualMachine();
				rmView.createVirtualMachine();

				/*
				 * rm.getActiveVM().addWindowListener( new WindowListener() {
				 * 
				 * @Override public void windowClosed(WindowEvent windowEvent) {
				 * // rmView.outputField.append( "\nPrograma baigė darbą.\n" );
				 * // rm.getActiveVM().dispose(); // rm.destroyActiveVM(); //
				 * rmView.executeButton.setEnabled( false ); }
				 * 
				 * @Override public void windowActivated(WindowEvent arg0) { //
				 * System.out.println("Window Activated"); }
				 * 
				 * @Override public void windowClosing(WindowEvent arg0) {
				 * rmView.outputField.append( "\nPrograma baigė darbą.\n" );
				 * //rm.getActiveVM().dispose(); //rm.destroyActiveVM();
				 * rmView.executeButton.setEnabled( false );
				 * rmView.inputField.setEnabled( false );
				 * rmView.reloadButton.setEnabled( true ); update(); }
				 * 
				 * @Override public void windowDeactivated(WindowEvent arg0) {
				 * // System.out.println("Window Deactivated"); }
				 * 
				 * @Override public void windowDeiconified(WindowEvent arg0) {
				 * // System.out.println("Window Deiconified"); }
				 * 
				 * @Override public void windowIconified(WindowEvent arg0) { //
				 * System.out.println("Window Iconified"); }
				 * 
				 * @Override public void windowOpened(WindowEvent arg0) { //
				 * System.out.println("Window Opened"); } });
				 */
			}
		});

		rmView.fileLoadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				LoadCustomFile chooser = new LoadCustomFile();
				chooser.customizeAppearance();
				int ret = chooser.showDialog(null, "Pasirinkti");

				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					rmView.filenameField.setText(file.getAbsolutePath());
				}
			}
		});

		rmView.inputField.addKeyListener(new KeyListener() {
			int offset = 0;

			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!event.isShiftDown()) {
						/*
						 * rm.processor.setReadWord( rmView.inputField.
						 * getText().substring( offset ) );
						 */
						// rmView.inputField.append("\n");
						/*
						 * offset = rmView.inputField.getText().length();
						 * 
						 * 
						 * if (Processor.is >= 90 || Processor.is < 60) {
						 * Processor.AP(1); Processor.test(); }
						 */

						// VMMemory.setMemoryAtIs(rmView.inputField.getText());
						// rmView.inputField.setText("");
						// Processor.pop();
						// VMMemory.popVMRegisters();
						rmView.executeButton.setEnabled(true);
						Processor.is++;
						rmView.executeButton.requestFocusInWindow();
						// Processor.cx++; //Kam sitas? :/
						rmView.inputField.setEnabled(false);
						rmView.reloadButton.setEnabled(true);
						update();
					} else {
						rmView.inputField.append("\n");
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent event) {
			}

			@Override
			public void keyTyped(KeyEvent event) {
			}
		});
	}

	public static void interrpretInterrupt() {
		System.out.println(RM.getMemoryAtIS());
		switch (RM.getMemoryAtIS()) {

		case "SAVE": {

			if (Processor.bus == 1) {
				VMMemory.saveVMRegisters();
				Processor.is++;
				rmView.update();
				break;
			} else {
				rmView.errorField
						.setText("Klaida: Komanda virtualiu rėžimu nepasiekiama");

				break;
			}

		}
		case "OPOC": {

			rmView.outputField.setEnabled(true);
			Processor.is++;
			rmView.update();
			break;
		}

		case "STOR": {
			Processor.push();
			Processor.is = Processor.r2;
			Memory.setMemoryAtIs(rmView.inputField.getText());
			Processor.pop();
			Processor.is++;
			Processor.k2 = 0;
			break;
		}

		case "PRIN": {

			Processor.push();
			Processor.is = Processor.r2;
			while (!RM.getMemoryAtIS().equals("EOF$")) {
				rmView.outputField.setText(rmView.outputField.getText()
						+ Memory.getMemoryAtIs());
				Processor.is++;
			}
			Processor.pop();
			Processor.is++;
			break;
		}

		case "RET": {
			Processor.pop();
			Processor.AB(0);
			rmView.outputField.setEnabled(false);
			Processor.BS(0);
			Processor.is++;
			Processor.r2 = 0;
			break;
		}
		case "OPIC": {

			System.out.println("Pertraukima issauke komanda GW");
			System.out.println("Pertraukimą iššaukė įvedimo kanalas");
			rmView.inputField.setEnabled(true);
			rmView.inputField.requestFocusInWindow();
			rmView.reloadButton.setEnabled(false);
			rmView.executeButton.setEnabled(false);
			rmView.table.setEnabled(false);
			rmView.update();
			break;
		}

		case "GET": {
			Processor.push();
			Processor.is = Processor.r2;
			rmView.outputField.setText(rmView.outputField.getText()+ Memory.getMemoryAtIs());
			Processor.is++;
			Processor.pop();
			Processor.is++;

		}
		}

	}

	public static void update() {
		rmView.update();
		/*
		 * if ( !rm.hasActiveVM() ) { rm.getActiveVM().update(); }
		 */
	}
}