package letters;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Test;

import citizensLoader.letters.PDFLetter;
import common.dto.User;

/**
 * Clase que prueba la implementación de las cartas
 * en documento de word
 * @author Carla, Sara, Claudia
 */

public class PdfLetterTest {
	
    @Test
    public void testCreateFileCorrectly() throws IOException {    
        PDFLetter letter = new PDFLetter();
        User prueba = new User();
        prueba.setDni("PruebaDNI");
        prueba.setEmail("pruebaEmail@email.com");
        prueba.setPassword("PruebaPassword");
        letter.write(prueba);
        
        String expected = "Gracias por registrarse! Su user es: "
        		+ "pruebaEmail@email.com y su contraseña: PruebaPassword";
        String actual = getText("generatedFiles/WelcomePruebaDNI.pdf");
        Assert.assertTrue (actual.trim().equals(expected.trim()));
        System.out.println(actual);
        System.out.println(expected);
    }
    
    static String getText(String filename) throws IOException {
    	File pdfFile = new File(filename);
        PDDocument doc = PDDocument.load(pdfFile);
        return new PDFTextStripper().getText(doc);
    }
    
	

}