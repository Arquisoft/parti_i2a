package letters;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import citizensLoader.letters.WordLetter;
import participationSystem.dto.User;

/**
 * Clase que prueba la implementación de las cartas
 * en documento de word
 * @author Carla, Sara, Claudia
 */

public class WordLetterTest {
	
    @Test
    public void testCreateFileCorrectly() throws IOException {    
        WordLetter letter = new WordLetter();
        User prueba = new User();
        prueba.setDni("PruebaDNI");
        prueba.setEmail("pruebaEmail@email.com");
        prueba.setPassword("PruebaPassword");
        letter.write(prueba);
        
        String expected = "Gracias por registrarse! Su user es: "
        		+ "pruebaEmail@email.com y su contraseña: PruebaPassword";
        String actual = leerDocx("generatedFiles/WelcomePruebaDNI.docx");
        System.out.println(expected);
        System.out.println(actual);
        assertTrue(expected.trim().equals(actual.trim()));
    }
    
    private static String leerDocx(String archivoDocx) throws IOException {
		//Se crea un documento que la POI entiende pasandole el stream
		//instanciamos el obj para extraer contenido pasando el documento
    	InputStream docx = new FileInputStream(archivoDocx);
		XWPFWordExtractor xwpf_we = new XWPFWordExtractor(new XWPFDocument(docx)); 
		xwpf_we.close();
		return xwpf_we.getText();
	}

}