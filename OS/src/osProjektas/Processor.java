package osProjektas;

import javax.sound.sampled.Line;

public class Processor {
	// Pagrindiniai registrai
	static Integer r1 = 0;
	static Integer r2 = 0;
	static Integer plr = 9;
	static Integer bus = 0;
	static Integer sv = 0;
	static Integer cx = 0;
	static Integer pr = 0;
	public static Integer is = 0;

	// Pertraukimo registrai
	static int pp = 0;
	static int sp = 0;
	static int ap = 0;
	static int k1 = 0;
	static int k2 = 0;
	static int k3 = 0;
	static int k4 = 0;
	static int lk = 0;

	static Byte[] asd = new Byte[4];

	// Sutvarkyti kanalus, kad ka nors darytu


	private static void BL(int value) {
		lk = value;

	}

	public static void assignR1(int value) {
		is++;
		r1 = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignR2(int value) {
		is++;
		r2 = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignCx(int value) {
		is++;
		cx = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void loadRegister() {
		cx++;
		// r1 =
	}

	public static void saveRegisterToMemory() {
		// [x*10+y]=r1
	}

	public static void halt() {

		//
	}

	public static void AA(int value) {
		Processor.ap = value;
	}

	public static void addToRegister() {
		r1 = r1 + Integer.parseInt(Memory.getMemoryAtIs());
		cx++;
	}

	public static void substractFromRegister() {
		r1 = r1 - Integer.parseInt(Memory.getMemoryAtIs());
		cx++;
	}

	public static void multiplyRegister() {
		r1 = r1 * Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void divideRegister() {
		// r1 = r1 + Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void compare() {
		//
		cx++;
	}

	public static String SEOF() {
		return "EOF$";
	}

	/**
	 * Jump If Equal if PR == 0 then IS:= [x*10+y]
	 * 
	 */
	public static void JIE(int value) {
		if (pr == 0) {
			// is = x * 10 + y;
			is = value;
		}
	}

	/**
	 * Jump If Greater if PR == 1 then IS:= [x*10+y]
	 * 
	 */
	public static void JIG(int value) {
		if (pr == 1) {
			// is = x * 10 + y;
			is = value;
		}
	}

	/**
	 * Jump If Less if PR == -1 then IS:= [x*10+y]
	 * 
	 */
	public static void JIL(int value) {
		if (pr == -1) {
			// is = x * 10 + y;
			is = value;
		}
	}

	/**
	 * Just jump... jump where?
	 * 
	 */
	// push adresas
	public static void test() {
		
		if ((pp + sp + ap + k1 + k2 + k3 + k4 + lk) > 0) {
			AB(1); // Bus reiksme priskiriama vienetui
			RMController.checkInterupt();
			System.out.println(pp +" " + sp +" "+ ap  );
			System.out.println(k1 +" "+ k2 +" "+ k3 +" "+k4 );
				
			
			AB(0);
							// Turetu perduoti valdyma kitai VM darba, jei tokia yra sukurta
		
		} else
			Processor.bus = 0;// VM mašinos rėžimas
	

	}

	public static void cleanMemory() {
		System.out.println("Isvaloma atmintis");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				VMMemory.VMMemory[i][j] = null;
			}

		}

	}

	public static void assignRMRegistersStack() {
		Memory.rmRegistersStack = Memory.memory[0];
	}

	public static void JMP(int value) {
		push();
		// is = x * 10 + y;
		is = value;
	}

	public static void getWord() { // GW
		sp = 3;

		//
	}

	public static void getMemoryBlock() { // GD

		//
	}

	public static void printWord() {
		//
	}

	

	public static void call() {
		//
	}

	public static void back() {

		pop();
		//
	}

	public static void loop(int cx) {
		if (cx > 0) {
			push();
			// interpretCommand(); ????? how to fix it? it is in VM, but is this
			// shit good?
			pop();
			loop(cx - 1);
		}
	}

	public static void assign() {
		//
	}

	public static void assignIs() {
		// is++;
		is = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignPr() {
		// is++;
		pr = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignPlr(int value) {
		// is++;
		// plr = Integer.parseInt(Memory.getMemoryAtIs());
		plr = value;
	}

	public static void AB(int value) {
		// is++;
		// bus = Integer.parseInt(Memory.getMemoryAtIs());
		bus = value;
	}

	public static void assignSv(int value) {
		// is++;
		// sv = Integer.parseInt(Memory.getMemoryAtIs());
		sv = value;
	}

	public static void assignPp(int value) {
		// is++;
		// pp = Integer.parseInt(Memory.getMemoryAtIs());
		pp = value;
	}

	public static void AP(int value) {
		// is++;
		// ap = Integer.parseInt(Memory.getMemoryAtIs());
		ap = value;
	}

	public static void A1(int value) {
		// is++;
		// k1 = Integer.parseInt(Memory.getMemoryAtIs());
		k1 = value;
	}

	public static void A2(int value) {
		// is++;
		// k2 = Integer.parseInt(Memory.getMemoryAtIs());
		k2 = value;
	}

	public static void A3(int value) {
		// is++;
		// k3 = Integer.parseInt(Memory.getMemoryAtIs());
		k3 = value;
	}

	public static void A4(int value) {
		// is++;
		// k4 = Integer.parseInt(Memory.getMemoryAtIs());
		k4 = value;
	}

	public static void assignLk(int value) {
		// is++;
		// lk = Integer.parseInt(Memory.getMemoryAtIs());
		lk = value;
	}

	public static void BS(int value) {
		// is++;
		// sp = Integer.parseInt(Memory.getMemoryAtIs());
		sp = value;
	}

	public static int reloadRegister(int x) {
		// is++;
		return x;
	}

	// gauti komanda nurodyta registru
	public static String getCommand() {

		return Memory.getMemoryAtIs();
	}

	public static void assignStackToMemory() {

		Memory.stack = Memory.memory[Memory.blocks - 1];
		// Processor.sv= 9;

	}

	public static void push() {
		if (Memory.stack == null) {
			assignStackToMemory();
		}

		Memory.stack[Processor.sv] = Processor.is.toString();
		Processor.sv++;
		// Processor.sv = stack.length;
	}

	public static void pop() {
		Processor.sv--;
		Processor.is = Integer.parseInt(Memory.stack[Processor.sv]);
		Memory.stack[Processor.sv] = "";

		// Memory.stack[Processor.sv] = "0";

	}

	public static void createMemory() {
		Memory.memory = new String[Memory.blocks][Memory.blockSize];
	}

	public static void createBuffer() {
		Memory.buffer = new int[Memory.blockSize][Memory.CELL_SIZE];
	}

}
