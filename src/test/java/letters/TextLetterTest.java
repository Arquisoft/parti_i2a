package letters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import citizensLoader.letters.TxtLetter;
import common.dto.User;

/**
 * Clase que prueba la implementación de las cartas
 * en texto plano
 * @author Carla, Sara, Claudia
 */

public class TextLetterTest {
	
    @Test
    public void testCreateFileCorrectly() throws IOException {    
        TxtLetter letter = new TxtLetter();
        User prueba = new User();
        prueba.setDni("PruebaDNI");
        prueba.setEmail("pruebaEmail@email.com");
        prueba.setPassword("PruebaPassword");
        letter.write(prueba);
        
        String expected = "Gracias por registrarse! Su user es: "
        		+ "pruebaEmail@email.com y su contraseña: PruebaPassword";
        String actual = readTxt("generatedFiles/WelcomePruebaDNI.txt");
        Assert.assertEquals(expected, actual);
    }
    
	private String readTxt(String filename) throws IOException {
		BufferedReader fichero;
		fichero = new BufferedReader(new FileReader(filename));
		String result = fichero.readLine();
		fichero.close();
		return result;
		
	}

}