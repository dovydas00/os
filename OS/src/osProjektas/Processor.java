package osProjektas;

public class Processor {
	// Pagrindiniai registrai
	static Integer r1 = 0;
	static int r2 = 0;
	static int[] plr = { '0', '0', '0', '9' };
	static int bus = 0;
	static int sv = 0;
	static int cx = 0;
	static int pr = 0;
	static int is = 0;

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

	public static void priskirtiR1RegistruiReikšmę(int value) {
		is++;
		r1 = Integer.parseInt(Memory.getMemoryAtIs());

	}

	public static void JMP(int x, int y) {

		is = x * 10 + y;
	}

	public static void main(String args[]) {

	}

	public static void šokti(int x, int y) {
		// is = x * 16 + y;
	}

}
