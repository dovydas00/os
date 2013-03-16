

/**
 * KlasÄ—, emuliuojanti procesoriaus darbÄ….
 *
 * @author Paulius, SaulÄ—, Antanas
 */
//yuju
public class Processor {
    private Memory memory;
    private int[] PTR = { 0, 0, 0, 0 };
    private String[] R = { "0", "0" } ;
    private Integer IC = 0;
    private Integer RN = 0;
    private Integer MODE = 0;
    private Integer C = 0;
    private Integer SI = 0;
    private Integer PI = 0;
    private Integer TI = 0;
    private boolean debug = false;

    /**
     * Konstruktorius.
     *
     * @param memory
     *      Realios maÅ¡inos atmintis.
     */

    Processor ( Memory memory ) {
        this.memory = memory;
    }

    /**
     * GrÄ…Å¾inama IC registro reikÅ¡mÄ—.
     *
     * @return
     *      IC registro reikÅ¡mÄ—.
     */

    public Integer getIC () {
        return this.IC;
    }

    /**
     * Nustatoma IC registro reikÅ¡mÄ—.
     *
     * @param value
     *      ReikÅ¡mÄ—, suteikiama IC registrui.
     */

    public void setIC ( int value ) {
        this.IC = value;
    }

    /**
     * IC reikÅ¡mÄ— padidinama vienetu.
     */

    public void incIC () {
        this.IC += 1;
    }

    /**
     * GrÄ…Å¾ina komandÄ…, nurodytÄ… IC registru.
     *
     * @return
     *      Komanda.
     */

    public String getCommand () {
        return memory.getMemory( page( IC ) );
    }

    /**
     * Nustatoma PTR registro reikÅ¡mÄ—.
     *
     * @param value
     *      ReikÅ¡mÄ—, kuriÄ… norima nustatyti.
     */

    public void setPTR( int value ) {
        value = value % 10000;
        int divisor = 1000;
        for ( int i = 0; i < PTR.length; i++ ) {
            this.PTR[ i ] = value/divisor;
            divisor /= 10;
        }
    }

    /**
     * GrÄ…Å¾inama PTR registro reikÅ¡mÄ— skaitiniu formatu.
     *
     * @return
     *      PTR registro reikÅ¡mÄ—.
     */

    public Integer getPTR () {
        return PTR[ 2 ] * 10 + PTR[ 3 ];
    }

    /**
     * Pagal virtualÅ³ adresÄ… sufomuojamas realus adresas.
     *
     * @param virtualAddress
     *      Virtualios maÅ¡inos atminties adresas.
     * @return
     *      Realios maÅ¡inos atminties adresas.
     */

    public int page ( int virtualAddress ) {
        int x = virtualAddress / 10;
        int y = virtualAddress % 10;
        int realAddress;
        if ( !(memory.getMemory( ( PTR[ 2 ] * 10 + PTR[ 3 ] ) * 10 + x )).equals( "" ) ) {
            realAddress = Integer.parseInt( memory.getMemory( 
                    ( PTR[ 2 ] * 10 + PTR[ 3 ] ) * 10 + x ) ) * 10 + y;
        } else {
            realAddress = 0;
        }


        if( debug ) {
            System.err.println( "Realus adresas: " + realAddress );
        }
        return realAddress;
    }
    
    String getVirtualMemoryValue ( int cellNumber ) {
        return memory.getMemory( page( cellNumber ) );
    }
    
    public String getR1 () {
        return this.R[ 0 ];
    }
    
    public String getR2 () {
        return this.R[ 1 ];
    }
    
    public Integer getRN () {
        return this.RN + 1;
    }
    
    public Integer getC () {
        return this.C;
    }
    
    public Integer getMode () {
        return this.MODE;
    }
    
    public Integer getSI () {
        return this.SI;
    }
    
    public void setSI ( int value ) {
        this.SI = value;
    }
    
        
    public Integer getPI () {
        return this.PI;
    }
    
    public void setPI ( int value ) {
        this.PI = value;
    }
       
    public Integer getTI () {
        return this.TI;
    }
    
    public void setTI ( int value ) {
        this.TI = value;
    }

//------------------------------------------------------------------------------
// Å½emiau Å¡ios linijos uÅ¾raÅ¡yti metodai skirti procesoriaus komandÅ³ emuliacijai.
//------------------------------------------------------------------------------

    /**
     * Ä® registrÄ… R kopijuojamas atminties lÄ…stelÄ—s,
     * kurios adresas x*10 + y, turinys.
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */

    public void loadRegister ( int xy ) {
        R[ RN ] = memory.getMemory( page( xy ) );
    }

    /**
     * Ä® atminties lÄ…stelÄ™, kurios adresas x*10 + y,
     * Ä¯raÅ¡oma registro R reikÅ¡mÄ—.
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */

    public void storeRegister ( int xy ) {
        memory.setMemory( page( xy ), R[ RN ] );
    }

    /**
     * PakeiÄ�ia dabartinÄ¯ registro numerÄ¯ Ä¯ kitÄ….
     */

    public void changeRN () {
        if (RN == 0) {
            RN = 1;
        } else {
            RN = 0;
        }
    }

    /**
     * Prie registro R reikÅ¡mÄ—s pridedama atminties lÄ…stelÄ—s,
     * kurios adresas x*10 + y reikÅ¡mÄ—.
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */

    public void addToRegister ( int xy ) {
        try {
            int memoryValue = Integer.decode( memory.getMemory( page( xy ) ) );
            int registerValue = Integer.decode( R[ RN ] );
            int sumValue = ( memoryValue + registerValue ) % 10000;
            R[ RN ] = Integer.toString( sumValue );
        } catch ( NumberFormatException e ) {
            PI = 3;
        }
    }

    /**
     * IÅ¡ registro R reikÅ¡mÄ—s atimama atminties lÄ…stelÄ—s,
     * kurios adresas x*10 + y reikÅ¡mÄ—.
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */

    public void substractFromRegister ( int xy ) {
        try {
            int memoryValue = Integer.decode( memory.getMemory( page( xy ) ) );
            int registerValue = Integer.decode( R[ RN ] );
            int divValue = ( registerValue - memoryValue ) % 10000;
            R[ RN ] = Integer.toString( divValue );
        } catch ( NumberFormatException e ) {
            PI = 3;
        }
    }

    /**
     * Palyginamos registro R ir atminties lÄ…stelÄ—s, kurios adresas x*10 + y
     * reikÅ¡mÄ—s ir pagal jas nustatomas C registras.
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */

    public void compareToRegister ( int xy ) {
        try {
            int memoryValue = Integer.decode( memory.getMemory( page( xy ) ) );
            int registerValue = Integer.decode( R[ RN ] );

            if ( debug ) {
                System.err.println( "Lyginamos lÄ…stelÄ—s reikÅ¡mÄ—: " + memoryValue );
                System.out.println( "Lyginamo registro reikÅ¡mÄ—: " + registerValue );
            }

            if ( memoryValue < registerValue ) {
                C = 1;
            } else if ( memoryValue == registerValue ) {
                C = 0;
            } else {
                C = -1;
            }

            if ( debug ) {
                System.err.println( "Palyginimo reikÅ¡mÄ—: " + C );
            }

        } catch ( NumberFormatException e ) {
            PI = 3;
        }
    }

    /**
     * Jei registro C reikÅ¡mÄ— lygi â€ž1â€œ, tai registrui IC
     * priskiriama reikÅ¡mÄ— [x*10 + y].
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */

    public void jumpIfGreater ( int xy ) {
        if ( C == 1 ) {
            IC = xy;
        } else {
            incIC();
        }
    }

    /**
     * Jei registro C reikÅ¡mÄ— lygi â€ž0â€œ, tai registrui IC
     * priskiriama reikÅ¡mÄ— [x*10 + y].
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */

    public void jumpIfEqual ( int xy ) {
        if ( C == 0 ) {
            IC = xy;
        } else {
            incIC();
        }
    }

    /**
     * Jei registro C reikÅ¡mÄ— lygi â€ž-1â€œ, tai registrui IC
     * priskiriama reikÅ¡mÄ— [x*10 + y].
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */

    public void jumpIfLess ( int xy ) {
        if ( C == -1 ) {
            IC = xy;
        } else {
            incIC();
        }
    }

    /**
     * BesÄ…lyginis valdymo perdavimas.
     * Registrui IC priskiriama reikÅ¡mÄ— [x*10 + y].
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */

    public void jump ( int xy ) {
        IC = xy;
    }

    /**
     * IÅ¡ Ä¯vedimo srauto perskaitomas 1 Å¾odis ir Ä¯raÅ¡omas Ä¯ lÄ…stelÄ™ adresu xy.
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */

    public void getWord ( int xy ) {
        SI = 1;
      //  memory[ page( xy ) ].setContent( scanner.nextLine() );
    }
    
    public void setReadWord ( String word ){
        int number = Integer.decode( getCommand().substring( 2 ) );
        memory.setMemory( page( number ), word );
    }
    
    /**
     * IÅ¡ Ä¯vedimo srauto perskaito 10 Å¾odÅ¾iÅ³ ir Ä¯raÅ¡o juos
     * Ä¯ lÄ…steles [x *10 + i], kur i = 0.. 9. Operandas y reikÅ¡mÄ—s neturi.
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */

    public void getData ( int xy ) {
        int blockNumber = xy / 10;
        for ( int i = 0; i < 10; i++ ) {
            getWord( blockNumber * 10 + i );
        }
    }

    /**
     * IÅ¡siunÄ�ia iÅ¡vedimui Å¾odÄ¯, kurio adreasas [x *10 + y ].
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */

    public void printWord ( int xy ) {
        SI = 4;
    }
    
    public String getPrintWord ( int xy ) {
        return memory.getMemory( page( xy ) );
    }

    /**
     * IÅ¡siunÄ�ia iÅ¡vedimui Å¾odÅ¾iÅ³ srautÄ… prasidedanti lÄ…stele [x*10 + y]
     * ir pasibaigiantÄ¯ sutikus specialÅ³ simbolÄ¯ EOF$.
     *
     * @param xy
     *      Atminties lÄ…stelÄ—s adresas.
     */
    
    public void printString ( int xy ) {
        SI = 2;
    }
    
    public String getPrintString ( int xy ) {
        boolean print = true;
        String fullString = "";
        do{
            String cellText = memory.getMemory( page( xy ) );
            xy++;
            if( cellText.equals( "EOF$" ) ){
                print = false;
            }else if( cellText.equals( "nnnn" ) ){
                fullString += "\n";
            }else{
                fullString += cellText;
            }
        }while( print );
    return fullString;
    }
    
    public void Halt() {
        SI = 3;
    }
}
