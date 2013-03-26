package osProjektas;


public class Processor {
	// Pagrindiniai registrai
	static Integer r1 = 0;
	static int r2 = 0;
	static int plr = 9;
	static int bus = 0;
	static int sv = 0;
	static int cx = 0;
	static int pr = 0;
	static Integer is = 0;

	// Pertraukimo registrai
	static int pp = 0;
	static int sp = 0;
	static int ap = 0;
	static int k1 = 0;
	static int k2 = 0;
	static int k3 = 0;
	static int k4 = 0;
	static int lk = 9;

	static Byte[] asd = new Byte[4];

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

/*	public static void interpretCommand() {
		switch (getCommand().substring(0, 2)) {
		case "AD": {
			push();
			is = Integer.parseInt(getCommand().substring(2, 4));
			r1 = r1 + Integer.parseInt(Memory.getMemoryAtIs());
			pop();
			is++;
		}

		}

	}*/

	/**
	 * Jump If Equal if PR == 0 then IS:= [x*10+y]
	 * 
	 */
	public static void JIE(int value) {
		if (pr == 0) {
			//is = x * 10 + y;
			is = value;
		}
	}

	/**
	 * Jump If Greater if PR == 1 then IS:= [x*10+y]
	 * 
	 */
	public static void JIG(int value) {
		if (pr == 1) {
			//is = x * 10 + y;
			is = value;
		}
	}

	/**
	 * Jump If Less if PR == -1 then IS:= [x*10+y]
	 * 
	 */
	public static void JIL(int value) {
		if (pr == -1) {
			//is = x * 10 + y;
			is = value;
		}
	}

	/**
	 * Just jump... jump where?
	 * 
	 */
	// push adresas
	public static int test() {

		if ((pp + sp + ap + k1 + k2 + k3 + k4 + lk) > 0) {
			return 1;
		} else
			return 0;
		
	}

	public static void JMP(int value) {
		push();
		//is = x * 10 + y;
		is = value;
	}

	public static void getWord() { //GW
		sp = 3;
		
		//
	}

	public static void getMemoryBlock() { //GD
		
		//
	}

	public static void printWord() {
		//
	}

	public static void printString() {
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
		if (cx > 0){
			push();
			//interpretCommand(); ????? how to fix it? it is in VM, but is this shit good?
			pop();
			loop(cx-1);
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
		//plr = Integer.parseInt(Memory.getMemoryAtIs());
		plr = value;
	}

	public static void assignBus(int value) {
		// is++;
		//bus = Integer.parseInt(Memory.getMemoryAtIs());
		bus = value;
	}

	public static void assignSv(int value) {
		// is++;
		//sv = Integer.parseInt(Memory.getMemoryAtIs());
		sv = value;
	}

	public static void assignPp(int value) {
		// is++;
		//pp = Integer.parseInt(Memory.getMemoryAtIs());
		pp = value;
	}

	public static void assignAp(int value) {
		// is++;
		//ap = Integer.parseInt(Memory.getMemoryAtIs());
		ap = value;
	}

	public static void assignK1(int value) {
		// is++;
		//k1 = Integer.parseInt(Memory.getMemoryAtIs());
		k1 = value;
	}

	public static void assignK2(int value) {
		// is++;
		//k2 = Integer.parseInt(Memory.getMemoryAtIs());
		k2 = value;
	}

	public static void assignK3(int value) {
		// is++;
		//k3 = Integer.parseInt(Memory.getMemoryAtIs());
		k3 = value;
	}

	public static void assignK4(int value) {
		// is++;
		//k4 = Integer.parseInt(Memory.getMemoryAtIs());
		k4 = value;
	}

	public static void assignLk(int value) {
		// is++;
		//lk = Integer.parseInt(Memory.getMemoryAtIs());
		lk = value;
	}

	public static void assignSp(int value) {
		// is++;
		sp = Integer.parseInt(Memory.getMemoryAtIs());
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
	
	public static void createBuffer(){
		Memory.buffer = new int[Memory.blockSize][Memory.CELL_SIZE];
	}
	
	
}
