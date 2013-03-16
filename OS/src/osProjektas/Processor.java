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
	}

	public static void substractFromRegister() {
		r1 = r1 - Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void multiplyRegister() {
		r1 = r1 * Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void divideRegister() {
		// r1 = r1 + Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void compare() {
		//
	}

	public static void interpretCommand() {

		switch (getCommand()){
		case "testas": test();
		}
		
		
		
	}

	/**
	 * Jump If Equal if PR == 0 then IS:= [x*10+y]
	 * 
	 * @param x
	 * @param y
	 */
	public static void JIE(int x, int y) {
		if (pr == 0) {
			is = x * 10 + y;
		}
	}

	/**
	 * Jump If Greater if PR == 1 then IS:= [x*10+y]
	 * 
	 * @param x
	 * @param y
	 */
	public static void JIG(int x, int y) {
		if (pr == 1) {
			is = x * 10 + y;
		}
	}

	/**
	 * Jump If Less if PR == -1 then IS:= [x*10+y]
	 * 
	 * @param x
	 * @param y
	 */
	public static void JIL(int x, int y) {
		if (pr == -1) {
			is = x * 10 + y;
		}
	}

	/**
	 * Just jump... jump where?
	 * 
	 * @param x
	 * @param y
	 */
	// push adresas
	public static void test() {

		System.out.println("Testas suveike");
	}

	public static void JMP(int x, int y) {
		is = x * 10 + y;
	}

	public static void getWord() {
		//
	}

	public static void getMemoryBlock() {
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
		//
	}

	public static void push() {
		//
	}

	public static void pops() {
		//
	}

	public static void loop() {
		//
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

	public static void assignPlr() {
		// is++;
		plr = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignBus() {
		// is++;
		bus = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignSv() {
		// is++;
		sv = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignPp() {
		// is++;
		pp = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignAp() {
		// is++;
		ap = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignK1() {
		// is++;
		k1 = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignK2() {
		// is++;
		k2 = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignK3() {
		// is++;
		k3 = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignK4() {
		// is++;
		k4 = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignLk() {
		// is++;
		lk = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void assignSp() {
		// is++;
		sp = Integer.parseInt(Memory.getMemoryAtIs());
	}

	public static void reloadRegister() {
		// is++;
	}

	// gauti komanda nurodyta registru
	public static String getCommand() {

		return Memory.getMemoryAtIs();
	}
}
