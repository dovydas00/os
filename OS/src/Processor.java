
/**
 * Klasė, emuliuojanti procesoriaus darbą.
 *
 * @author Paulius, Saulė, Antanas
 */

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
     *      Realios mašinos atmintis.
     */

    Processor ( Memory memory ) {
        this.memory = memory;
    }

    /**
     * Grąžinama IC registro reikšmė.
     *
     * @return
     *      IC registro reikšmė.
     */

    public Integer getIC () {
        return this.IC;
    }

    /**
     * Nustatoma IC registro reikšmė.
     *
     * @param value
     *      Reikšmė, suteikiama IC registrui.
     */

    public void setIC ( int value ) {
        this.IC = value;
    }

    /**
     * IC reikšmė padidinama vienetu.
     */

    public void incIC () {
        this.IC += 1;
    }

    /**
     * Grąžina komandą, nurodytą IC registru.
     *
     * @return
     *      Komanda.
     */

    public String getCommand () {
        return memory.getMemory( page( IC ) );
    }

    /**
     * Nustatoma PTR registro reikšmė.
     *
     * @param value
     *      Reikšmė, kurią norima nustatyti.
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
     * Grąžinama PTR registro reikšmė skaitiniu formatu.
     *
     * @return
     *      PTR registro reikšmė.
     */

    public Integer getPTR () {
        return PTR[ 2 ] * 10 + PTR[ 3 ];
    }

    /**
     * Pagal virtualų adresą sufomuojamas realus adresas.
     *
     * @param virtualAddress
     *      Virtualios mašinos atminties adresas.
     * @return
     *      Realios mašinos atminties adresas.
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
// Žemiau šios linijos užrašyti metodai skirti procesoriaus komandų emuliacijai.
//------------------------------------------------------------------------------

    /**
     * Į registrą R kopijuojamas atminties ląstelės,
     * kurios adresas x*10 + y, turinys.
     *
     * @param xy
     *      Atminties ląstelės adresas.
     */

    public void loadRegister ( int xy ) {
        R[ RN ] = memory.getMemory( page( xy ) );
    }

    /**
     * Į atminties ląstelę, kurios adresas x*10 + y,
     * įrašoma registro R reikšmė.
     *
     * @param xy
     *      Atminties ląstelės adresas.
     */

    public void storeRegister ( int xy ) {
        memory.setMemory( page( xy ), R[ RN ] );
    }

    /**
     * Pakeičia dabartinį registro numerį į kitą.
     */

    public void changeRN () {
        if (RN == 0) {
            RN = 1;
        } else {
            RN = 0;
        }
    }

    /**
     * Prie registro R reikšmės pridedama atminties ląstelės,
     * kurios adresas x*10 + y reikšmė.
     *
     * @param xy
     *      Atminties ląstelės adresas.
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
     * Iš registro R reikšmės atimama atminties ląstelės,
     * kurios adresas x*10 + y reikšmė.
     *
     * @param xy
     *      Atminties ląstelės adresas.
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
     * Palyginamos registro R ir atminties ląstelės, kurios adresas x*10 + y
     * reikšmės ir pagal jas nustatomas C registras.
     *
     * @param xy
     *      Atminties ląstelės adresas.
     */

    public void compareToRegister ( int xy ) {
        try {
            int memoryValue = Integer.decode( memory.getMemory( page( xy ) ) );
            int registerValue = Integer.decode( R[ RN ] );

            if ( debug ) {
                System.err.println( "Lyginamos ląstelės reikšmė: " + memoryValue );
                System.out.println( "Lyginamo registro reikšmė: " + registerValue );
            }

            if ( memoryValue < registerValue ) {
                C = 1;
            } else if ( memoryValue == registerValue ) {
                C = 0;
            } else {
                C = -1;
            }

            if ( debug ) {
                System.err.println( "Palyginimo reikšmė: " + C );
            }

        } catch ( NumberFormatException e ) {
            PI = 3;
        }
    }

    /**
     * Jei registro C reikšmė lygi „1“, tai registrui IC
     * priskiriama reikšmė [x*10 + y].
     *
     * @param xy
     *      Atminties ląstelės adresas.
     */

    public void jumpIfGreater ( int xy ) {
        if ( C == 1 ) {
            IC = xy;
        } else {
            incIC();
        }
    }

    /**
     * Jei registro C reikšmė lygi „0“, tai registrui IC
     * priskiriama reikšmė [x*10 + y].
     *
     * @param xy
     *      Atminties ląstelės adresas.
     */

    public void jumpIfEqual ( int xy ) {
        if ( C == 0 ) {
            IC = xy;
        } else {
            incIC();
        }
    }

    /**
     * Jei registro C reikšmė lygi „-1“, tai registrui IC
     * priskiriama reikšmė [x*10 + y].
     *
     * @param xy
     *      Atminties ląstelės adresas.
     */

    public void jumpIfLess ( int xy ) {
        if ( C == -1 ) {
            IC = xy;
        } else {
            incIC();
        }
    }

    /**
     * Besąlyginis valdymo perdavimas.
     * Registrui IC priskiriama reikšmė [x*10 + y].
     *
     * @param xy
     *      Atminties ląstelės adresas.
     */

    public void jump ( int xy ) {
        IC = xy;
    }

    /**
     * Iš įvedimo srauto perskaitomas 1 žodis ir įrašomas į ląstelę adresu xy.
     *
     * @param xy
     *      Atminties ląstelės adresas.
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
     * Iš įvedimo srauto perskaito 10 žodžių ir įrašo juos
     * į ląsteles [x *10 + i], kur i = 0.. 9. Operandas y reikšmės neturi.
     *
     * @param xy
     *      Atminties ląstelės adresas.
     */

    public void getData ( int xy ) {
        int blockNumber = xy / 10;
        for ( int i = 0; i < 10; i++ ) {
            getWord( blockNumber * 10 + i );
        }
    }

    /**
     * Išsiunčia išvedimui žodį, kurio adreasas [x *10 + y ].
     *
     * @param xy
     *      Atminties ląstelės adresas.
     */

    public void printWord ( int xy ) {
        SI = 4;
    }
    
    public String getPrintWord ( int xy ) {
        return memory.getMemory( page( xy ) );
    }

    /**
     * Išsiunčia išvedimui žodžių srautą prasidedanti ląstele [x*10 + y]
     * ir pasibaigiantį sutikus specialų simbolį EOF$.
     *
     * @param xy
     *      Atminties ląstelės adresas.
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
