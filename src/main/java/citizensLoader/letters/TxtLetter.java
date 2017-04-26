package citizensLoader.letters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import common.dto.User;

public class TxtLetter implements Writtable {
	
	/**
	 * Writes in a txt file a letter for each citizen,
	 * specifying its own login email and password
	 * @param c - citizen for which the letter will be written
	 * @throws IOException
	 */
	@Override
	public void write(User c) throws IOException {
		BufferedWriter fichero;
		//give the name and directory
		String nombreFichero = "generatedFiles/Welcome"+c.getDni()+".txt";
		fichero = new BufferedWriter(new FileWriter(nombreFichero));
		//write the message
		String message = "Gracias por registrarse! Su user es: "
		        + c.getEmail()+ " y su contraseña: "+c.getPassword() ;
		fichero.write(message);
		fichero.close();
		
	}

}
