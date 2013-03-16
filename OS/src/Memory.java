package mos;

/**
 *
 * @author Antanas
 */
public class Memory {
    static public int BLOCK_SIZE = 10;
    static int CELL_SIZE = 4;
    static String FILL_SYMBOL = "";
    
    private String[][] memory;
    private boolean[] blockUsed = new boolean[ RealMachine.MEMORY_SIZE / BLOCK_SIZE ];
    public int length;
    
    public Memory( int size ) {
        this.length = size;
        memory = new String[ size / BLOCK_SIZE ][ BLOCK_SIZE ];
        for ( int i = 0; i < size / BLOCK_SIZE; i++ ) {
            blockUsed[ i ] = false;
            for ( int j = 0; j < BLOCK_SIZE; j ++ ) {
                memory[ i ][ j ] = FILL_SYMBOL;
            }
        }
    }
    
    public String getMemory( int position ) {
        return memory[ position / BLOCK_SIZE ][ position % BLOCK_SIZE ];
    }
    
    public void setMemory( int position, String content ) {
        if ( content.length() > CELL_SIZE ) {
            content = content.substring( 0, CELL_SIZE );
        }
        
        this.memory[ position / BLOCK_SIZE ][ position % BLOCK_SIZE ] = content;
    }

    public boolean getBlockUsed( int position ) {
        return blockUsed[ position ];
    }
    
    public void setBlockUsed( int position, boolean value ) {
        this.blockUsed[ position ] = value;
    }
    
    public String[][] getMemoryArray () {
        return this.memory;
    }
    
    
    /**
     * Konstruktorius suteikiantis atminties ląstelei nurodytą reikšmę. Jei 
     * žodis per ilgas, jis apkarpomas.
     * 
     * @param data 
     *      Atminties ląstelei norimas suteikti turinys.
     */

    /**
     * Atminties ląstelei suteikiamas nurodytas turinys. Jei žodis per ilgas,  
     * jis apkarpomas.
     * 
     * @param data
     *      Atminties ląstelei norimas suteikti turinys.
     */
    
    /**
     * Grąžinamas ląstelės turinys.
     * 
     * @return
     *      Ląstelės turinys.
     */
    
    /**
     * Nustatoma lauko used reikšmė.
     * 
     * @param used 
     *      Loginė reikšmė, kurią reikia priskirti.
     */

    
    /**
     * Grąžinama parametro used reikšmė.
     * 
     * @return 
     *      Lauko used loginė reikšmė.
     */
    
}