package mos;

import java.io.*;
import java.util.regex.Pattern;

/**
 * Klasė, imituojanti išorinį nuskaitymo įrenginį.
 * 
 * @author Saulė, Antanas, Paulius,
 */

public class Reader {
    
    private Memory dataArray = new Memory( VirtualMachine.MEMORY_SIZE );
    private BufferedReader reader;
    private int n;
    
    /**
     * Tuščias konstruktorius.
     */
    
    Reader() {      
    }
    
    /**
     * Konstruktorius, priskiriantis failo pavadinimą ir atidarantis srautą 
     * skaitymui.
     * 
     * @param filename
     *      Failo, iš kurio norima skaityti, pavadinimas.
     * @throws IOException 
     *      Neradus nurodyto failo.
     */
    
    Reader( String filename ) throws IOException{
        setFile( filename );
    }
    
    /**
     * Priskiriamas naujas failas ir atidaromas skaitymo srautas.
     * @param filename
     *      Failo, iš kurio norima skaityti, pavadinimas.
     * @throws IOException 
     *      Neradus nurodyto failo.
     */
    
    public final void setFile( String filename ) throws IOException {
        FileInputStream fstream = new FileInputStream( filename );
        DataInputStream in = new DataInputStream( fstream );
        this.reader = new BufferedReader( new InputStreamReader( in ) );
    }
    
    /**
     * Nuskaito, apdoroja ir surašo į masyvą nustatyto failo eilutes.
     * 
     * @throws IOException 
     *      Kilus klaidoms įvesties sraute.
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
     * Apdorojamos kodo segmento eilutė ir įrašoma į masyvą.
     * 
     * @param codeLine
     *      Kodo segmento eilutė.
     */
    
    private void parseCodeSegment ( String codeLine ) {
        dataArray.setMemory( n, codeLine );
        n++;
    }
    
    /**
     * Apdorojama duomenų segmento eilutė ir įrašoma į masyvą.
     * 
     * @param dataLine 
     *      Duomenų segmento eilutė.
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
     * Grąžinamas duomenų masyvas.
     * 
     * @return 
     *      Duomenų masyvas.
     */
    
    public Memory getDataArray() {
        return this.dataArray;
    }
    
    /**
     * Duomenų masyvas išvedamas į standartinę išvestį. Naudojama diagnostikai.
     */
    
    public void printDataArray() {       
        for( int i = 0; i < dataArray.length; i++ ){
            System.out.println( i + " " + dataArray.getMemory( i ) );
        }
    }
}