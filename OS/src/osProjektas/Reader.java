package osProjektas;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Reader {

	public static void readFromFile() {
		{
			try {
				// Open the file that is the first
				// command line parameter
				FileInputStream fstream = new FileInputStream("Program_1.txt");
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				String strLine;
				String segment = "";
				// Read File Line By Line
				while ((strLine = br.readLine()) != null) {
					// Print the content on the console
					if (strLine.equals("CS:")) {
						segment = "CS";
					}
					if (strLine.equals("DS:")) {
						segment = "DS";
					}

					if (segment.equals("CS")) {
						int i = 0;
						int j = 0;
						while (VMMemory.VMMemory[i][j] != null) {
							if (j == 9) {
								i++;
								j = -1;
							}
							j++;
						}
						if (i == 9) {
							Processor.ap = 1;
							System.out.println("Overloaded pertraukimas");
						}
						if (i != 9 && !strLine.equals("CS:")) {
							VMMemory.VMMemory[i][j] = strLine;
						}

					}
					if (segment.equals("DS")) {
						int i = 6;
						int j = 0;
						while (VMMemory.VMMemory[i][j] != null) {
							if (j == 9) {
								i++;
								j = -1;
							}
							j++;
						}
						if (i == 9) {
							Processor.ap = 1;
							System.out.println("Overloaded pertraukimas");
						}
						if (i != 9 && !strLine.equals("DS:")) {
							VMMemory.VMMemory[i][j] = strLine;
						}

					}
					
					

				}

			
				// Close the input stream
				in.close();
			} catch (Exception e) {// Catch exception if any
				System.err.println("Error: " + e.getMessage());

			}
		}
	}
}