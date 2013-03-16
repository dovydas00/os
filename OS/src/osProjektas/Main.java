package osProjektas;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException{
       
       boolean debug = false;
       Reader reader = new Reader();
       //reader.setFile( "C:\\Users\\Lukas\\Desktop\\OS\\os.git\\trunk\\OS" );
       //reader.parseFile();
       
      /* if ( debug ) {
           reader.printDataArray();
       }*/
       
       //Memory memory = reader.getDataArray();
       RM rm = new RM();
      // rm.loadProgram( memory );
       RMView rmWindow = new RMView( rm );
      // RealMachineController listener = new RealMachineController( rmWindow, rm );
       
       try {
          // machine.interpretProgram();
       } catch ( NumberFormatException e )  {
            System.out.println("Klaidingai Ä¯vestas skaiÄ�ius");
        }
    }
}

