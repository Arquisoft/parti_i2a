package citizensLoader.letters;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import common.dto.User;

public class PDFLetter implements Writtable{

	@Override
	
	/**
	 * Writes in a pdf file a letter for each citizen,
	 * specifying its own login email and password
	 * @param c - citizen for which the letter will be written
	 * @throws IOException
	 */
	public void write(User c) throws FileNotFoundException {
		//Initialize PDF writer
        PdfWriter writer = new PdfWriter("generatedFiles/Welcome"+c.getDni()+".pdf");
 
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
 
        // Initialize document
        Document document = new Document(pdf);
 
        //Add paragraph to the document
        document.add(new Paragraph("Gracias por registrarse! Su user es: "
        + c.getEmail()+ " y su contraseña: "+c.getPassword()));
 
        //Close document
        document.close();
	}

	

}
