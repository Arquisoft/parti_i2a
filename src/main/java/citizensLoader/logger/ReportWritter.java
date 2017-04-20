package citizensLoader.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class ReportWritter {

	BufferedWriter fichero;
	
	/**
	 * Constructor giving the name and directory of our log file
	 * @throws IOException
	 */
	public ReportWritter() {
		try {
			createLog("generatedFiles/errors.log");
		} catch (IOException e) {
			System.err.println("No se ha podido crear el log");
		}
	}
	
	/**
	 * Creates the log file
	 * @param name
	 * @throws IOException
	 */
	public void createLog(String name) throws IOException {
		fichero = new BufferedWriter(new FileWriter(name));
	}
	
	/**
	 * Writes in the log file all the errors that may occur
	 * @param message - error that happened
	 * @param filename - name of the file which produced the error
	 * @throws IOException
	 */
	public void record(String message, String filename) throws IOException {
		Date date = new Date();
		String linea = "";
		fichero.append(linea);
		fichero.append("Filename: " + filename + " ");
		fichero.append(date.toString() +" "+ message );

		fichero.newLine();
		fichero.append("--------------------------");
		fichero.newLine();
	}

	/**
	 * Closes the file
	 */
	public void close() {
		try {
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
        
}