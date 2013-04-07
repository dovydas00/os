package osProjektas;

public class VM {

	private boolean debug = false;
	private int id;
	private boolean instructionStep = false;

	public VM(int block) {
		VMMemory.VMMemory = VMMemory.createVMMemory(block);
		
		Processor.r1 = 0;
		Processor.r2 = 0;
		Processor.cx = 0;
		Processor.sv= 0;
		Processor.pr = 0;
		Processor.is = 0;
		
	}



/*	*//**
	 * Konstruktorius.
	 * 
	 * @param processor
	 *            Procesorius, gautas iš realios mašinos.
	 * @param id
	 *            Virtualios mašinos intentifikatorius.
	 *//*

	VM(Processor processor, Integer id) {
		this.processor = processor;
		this.id = id;
		// this.vmWindow = new VMWindow( processor );
	}

	*//**
	 * Grąžinamas virtualios mašinos ID.
	 * 
	 * @return Virtualios mašinos ID.
	 *//*

	public int getID() {
		return this.id;
	}

	*//**
	 * Interpertuojamas virtualios mašinos programos kodas.
	 */

	   public void interpretProgram () {
		    

	        boolean interpretNext = true;
	        
	        while ( interpretNext ) {
	            interpretCommand ();
	        }
	    }
	    
	    public boolean interpretCommand () {
	        int number = 0; //ju buvo, paduodama i komandas integer didelis ir tikriausiai viduj 
	        //dalindavo i X ir Y (TIKRIAUSIAI!!!!!)
	        
	        
	        //done by this (look above)
	        
	        //reiktu ir mum imt i viena integer ir sudet, juk komanda x*10+y, tai lygiai kas xy=a
	       // int x,y =0; //musu
	        boolean interpretNext = true;
	        String command = Processor.getCommand();

	        if ( debug ) {
	            System.err.println( "CX registro reikšmė: " + Processor.cx );
	            System.err.println( "Atliekama komanda: " + command );
	        }
	        
	        try {
	            if ( ! ( "CGRN".equals( command ) || "HALT".equals( command ) ) ) {
	                number = Integer.decode( command.substring( 2 ) );
	            }
	        } catch ( NumberFormatException e ) {
	            Processor.pp = 1;
	        }
	        
	        if(command.startsWith("R1")){
	        	Processor.assignR1(number);
	        } else if (command.startsWith("R2")){
	        	Processor.assignR2(number);
	        } else if (command.startsWith("CX")){
	        	Processor.assignCx(number);
	        } else if (command.startsWith("LR")){
	        	Processor.loadRegister();
	        } else if (command.startsWith("SR")){
	        	Processor.saveRegisterToMemory();
	        } else if (command.startsWith("HALT")){
	        	Processor.halt();
	        } else if (command.startsWith("AD")){
	        	Processor.addToRegister();
	        } else if (command.startsWith("SB")){
	        	Processor.substractFromRegister();
	        } else if (command.startsWith("ML")){
	        	Processor.multiplyRegister();
	        } else if (command.startsWith("DV")){
	        	Processor.divideRegister();
	        } else if (command.startsWith("CP")){
	        	Processor.compare();
	        } else if (command.startsWith("JE")){
	        	Processor.JIE(number);
	        } else if (command.startsWith("JG")){
	        	Processor.JIG(number);
	        } else if (command.startsWith("JL")){
	        	Processor.JIL(number);
	        } else if (command.startsWith("JP")){
	        	Processor.JMP(number);
	        } else if (command.startsWith("GW")){
	        	Processor.getWord();
	        } else if (command.startsWith("GD")){
	        	Processor.getMemoryBlock();
	        } else if (command.startsWith("PW")){
	        	Processor.printWord();
	        } else if (command.startsWith("PS")){
	        	Processor.printString();
	        } else if (command.startsWith("CA")){
	        	Processor.call();
	        } else if (command.startsWith("RETR")){
	        	Processor.back();
	        } else if (command.startsWith("LP")){
	        	Processor.loop(Processor.cx);
	        } else if (command.startsWith("PUSH")){
	        	Processor.push();
	        } else if (command.startsWith("POPS")){
	        	Processor.pop();
	        } else if (command.startsWith("AI")){
	        	Processor.assignIs();
	        } else if (command.startsWith("AR")){
	        	Processor.assignPr();
	        } else if (command.startsWith("AL")){
	        	Processor.assignPlr(number);
	        } else if (command.startsWith("AB")){
	        	Processor.AB(number);
	        } else if (command.startsWith("AS")){
	        	Processor.assignSv(number);
	        } else if (command.startsWith("AP")){
	        	Processor.assignPp(number);
	        } else if (command.startsWith("AA")){
	        	Processor.AP(number);
	        } else if (command.startsWith("A1")){
	        	Processor.A1(number);
	        } else if (command.startsWith("A2")){
	        	Processor.A2(number);
	        } else if (command.startsWith("A3")){
	        	Processor.A3(number);
	        } else if (command.startsWith("A4")){
	        	Processor.A4(number);
	        } else if (command.startsWith("BL")){
	        	Processor.assignLk(number);
	        } else if (command.startsWith("BS")){
	        	Processor.BS(number);
	        } else if (command.startsWith("LD")){
	        	Processor.reloadRegister(number);
	        }
	        /*if ( command.startsWith( "HALT" ) || processor.sp == 3 ) {
	            processor.halt();
	            interpretNext = false;
	        } else if ( command.startsWith( "SR" ) ) {
	            processor.storeRegister( number );
	            processor.cx++;
	        } else if ( command.startsWith( "CGRN" ) ) {
	            processor.changeRN();
	            processor.incIC();
	        } else if ( command.startsWith( "AD" ) ) {
	            processor.addToRegister( );
	        } else if ( command.startsWith( "SB" ) ) {
	            processor.substractFromRegister();
	        } else if ( command.startsWith( "CP" ) ) {
	            processor.compare();
	        } else if ( command.startsWith( "JG" ) ) {
	            processor.JIG(x,y);
	        } else if ( command.startsWith( "JE" ) ) {
	            processor.JIE(x,y);
	        } else if ( command.startsWith( "JL" ) ) {
	            processor.JIL(x,y);
	        } else if ( command.startsWith( "JP" ) ) {
	            processor.JMP(x,y);
	        } else if ( command.startsWith( "GW" ) ) {
	            processor.getWord();
	        } else if ( command.startsWith( "GD" ) ) {
	            processor.getData( number );
	        } else if ( command.startsWith( "PW" ) ) {
	            processor.printWord();
	        } else if ( command.startsWith( "PS" ) ) {
	            processor.printString();
	        } else if ( command.startsWith( "LR" ) ) {
	            processor.loadRegister();
	        } else {
	            processor.pp = 2;
	        }*/

	        return interpretNext;
	    }
	    
	   /* public String getMemoryContent ( int cellNumber ) {
	        return processor.getVirtualMemoryValue( cellNumber );
	    }*/
	}