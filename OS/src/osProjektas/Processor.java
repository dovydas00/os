package osProjektas;

public class Processor {
	static int[] r1 = { 0, 0, 0, 0 };
	int[] r2 = { 0, 0, 0, 0 };
	int[] plr = { 0, 0, 0, 0 };
	int[] bus = { 1 };
	int[] sv = { 0, 0 };
	int[] cx = { 0, 0, 0, 0 };
	int[] pr = { 0 };
	int[] is = { 0 };

	public static int[] getR1() {
		return r1;
	}

	public static void setR1(int[] r1) {
		Processor.r1 = r1;
	}

	public int[] getR2() {
		return r2;
	}

	public void setR2(int[] r2) {
		this.r2 = r2;
	}

	public int[] getPlr() {
		return plr;
	}

	public void setPlr(int[] plr) {
		this.plr = plr;
	}

	public int[] getBus() {
		return bus;
	}

	public void setBus(int[] bus) {
		this.bus = bus;
	}

	public int[] getSv() {
		return sv;
	}

	public void setSv(int[] sv) {
		this.sv = sv;
	}

	public int[] getCx() {
		return cx;
	}

	public void setCx(int[] cx) {
		this.cx = cx;
	}

	public int[] getPr() {
		return pr;
	}

	public void setPr(int[] pr) {
		this.pr = pr;
	}

	public int[] getIs() {
		return is;
	}

	public void setIs(int[] is) {
		this.is = is;
	}

	public static void main(String args[]) {

	}

}
