package citizensLoader.letters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import participationSystem.dto.User;

public class WordLetter implements Writtable{
	

	/**
	 * Writes in a word file a letter for each citizen,
	 * specifying its own login email and password
	 * @param c - citizen for which the letter will be written
	 * @throws IOException
	 */
	public void write(User c) throws IOException {
	    XWPFDocument document = new XWPFDocument();
        //Write the Document in file system
	    FileOutputStream out = new FileOutputStream(
	    		new File("generatedFiles/Welcome"+c.getDni()+".docx"));

        //create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("Gracias por registrarse! Su user es: "
        + c.getEmail()+ " y su contraseña: "
        		+c.getPassword() );
        document.write(out);
       
        //Close document
        out.close();
        document.close();
    }

	
}
