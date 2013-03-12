
import java.io.*;
import java.util.regex.Pattern;

/**
 * KlasÄ—, imituojanti iÅ¡orinÄ¯ nuskaitymo Ä¯renginÄ¯.
 * 
 * @author SaulÄ—, Antanas, Paulius,
 */

public class Reader {
    
    private Memory dataArray = new Memory( VirtualMachine.MEMORY_SIZE );
    private BufferedReader reader;
    private int n;
    
    /**
     * TuÅ¡Ä�ias konstruktorius.
     */
    
    Reader() {      
    }
    
    /**
     * Konstruktorius, priskiriantis failo pavadinimÄ… ir atidarantis srautÄ… 
     * skaitymui.
     * 
     * @param filename
     *      Failo, iÅ¡ kurio norima skaityti, pavadinimas.
     * @throws IOException 
     *      Neradus nurodyto failo.
     */
    
    Reader( String filename ) throws IOException{
        setFile( filename );
    }
    
    /**
     * Priskiriamas naujas failas ir atidaromas skaitymo srautas.
     * @param filename
     *      Failo, iÅ¡ kurio norima skaityti, pavadinimas.
     * @throws IOException 
     *      Neradus nurodyto failo.
     */
    
    public final void setFile( String filename ) throws IOException {
        FileInputStream fstream = new FileInputStream( filename );
        DataInputStream in = new DataInputStream( fstream );
        this.reader = new BufferedReader( new InputStreamReader( in ) );
    }
    
    /**
     * Nuskaito, apdoroja ir suraÅ¡o Ä¯ masyvÄ… nustatyto failo eilutes.
     * 
     * @throws IOException 
     *      Kilus klaidoms Ä¯vesties sraute.
     */
    
    public void parseFile() throws IOException {
        
        String fileLine;
        n = 0;
        
        while ( !reader.readLine().startsWith( "CS:" ) ){
        }
    
        while( !( ( fileLine = reader.readLine() ).startsWith( "DS:" ) ) ){
            parseCodeSegment( fileLine );
        }
    
        n = VirtualMachine.DATA_SEGMENT_START;
        while( ( fileLine = reader.readLine() ) != null ){
            parseDataSegment( fileLine );
        }
        
        reader.close();
    }
    
    /**
     * Apdorojamos kodo segmento eilutÄ— ir Ä¯raÅ¡oma Ä¯ masyvÄ….
     * 
     * @param codeLine
     *      Kodo segmento eilutÄ—.
     */
    
    private void parseCodeSegment ( String codeLine ) {
        dataArray.setMemory( n, codeLine );
        n++;
    }
    
    /**
     * Apdorojama duomenÅ³ segmento eilutÄ— ir Ä¯raÅ¡oma Ä¯ masyvÄ….
     * 
     * @param dataLine 
     *      DuomenÅ³ segmento eilutÄ—.
     */
    
    private void parseDataSegment( String dataLine ) {
        
        String subSeq;

        if( Pattern.matches( "^SW\\d\\d.*", dataLine ) ){
            n = VirtualMachine.DATA_SEGMENT_START + Integer.parseInt( dataLine.substring( 2, 4 ) );
            dataLine = dataLine.substring( 5 );   //SW komanda, 2 skaitmenys bei tarpo simbolis
        } else if ( dataLine.startsWith( "SW " ) ) {
            dataLine = dataLine.substring( 3 );   //SW komanda bei tarpo simbolis
        }
        
        int residueLength = dataLine.length() % Memory.CELL_SIZE;
        int residueStart = dataLine.length() - residueLength;
        int k = 0;
        while( k < residueStart ){
            subSeq = dataLine.substring( k, k + Memory.CELL_SIZE );
            k += Memory.CELL_SIZE;
            dataArray.setMemory( n, subSeq );
            n++;
        }
        
        subSeq = dataLine.substring( residueStart );
        if ( !subSeq.equals( "" ) ) {
            dataArray.setMemory( n, subSeq );
            n++;
        }
    }
    
    /**
     * GrÄ…Å¾inamas duomenÅ³ masyvas.
     * 
     * @return 
     *      DuomenÅ³ masyvas.
     */
    
    public Memory getDataArray() {
        return this.dataArray;
    }
    
    /**
     * DuomenÅ³ masyvas iÅ¡vedamas Ä¯ standartinÄ™ iÅ¡vestÄ¯. Naudojama diagnostikai.
     */
    
    public void printDataArray() {       
        for( int i = 0; i < dataArray.length; i++ ){
            System.out.println( i + " " + dataArray.getMemory( i ) );
        }
    }
}