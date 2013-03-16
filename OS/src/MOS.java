


import java.io.IOException;

/**
 *
 * @author Antanas, Paulius, Saulė
 */
public class MOS {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException{
       
       boolean debug = false;
       Reader reader = new Reader();
       reader.setFile( "C:\\Users\\Dovydas\\Documents\\SVN OS\\trunk\\OS\\Program_2.txt" );
       reader.parseFile();
       
       if ( debug ) {
           reader.printDataArray();
       }
       
       Memory memory = reader.getDataArray();
       RealMachine rm = new RealMachine();
      // rm.loadProgram( memory );
       RealMachineView rmWindow = new RealMachineView( rm );
       RealMachineController listener = new RealMachineController( rmWindow, rm );
       
       try {
          // machine.interpretProgram();
       } catch ( NumberFormatException e )  {
            System.out.println("Klaidingai įvestas skaičius");
        }
    }
}
