package osProjektas;

public class Memory {

    static public int BLOCK_SIZE = 16;
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

}
