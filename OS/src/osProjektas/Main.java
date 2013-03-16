package osProjektas;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException{
    	Memory.createMemory();
    	for (int i=0; i<10;i++){
    		Processor.is = i;
    		Memory.push();
    		//Memory.memory[Memory.blocks-1][i] = i + "";
    	}
    //	Memory.assignStackToMemory(); // Nustato stekui atminties vietas
    	
    	for (int i=0; i<10;i++){
    		Memory.pop();
    		System.out.println(Processor.is);
    	}
    	
       
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
       RMController listener = new RMController( rmWindow, rm );
       
       try {
          // machine.interpretProgram();
       } catch ( NumberFormatException e )  {
            System.out.println("Klaidingai Ä¯vestas skaiÄ�ius");
        }
    }
}

