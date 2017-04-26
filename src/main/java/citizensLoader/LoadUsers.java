package citizensLoader;

import java.io.File;
import java.io.IOException;

import citizensLoader.letters.PDFLetter;
import citizensLoader.letters.TxtLetter;
import citizensLoader.letters.WordLetter;
import citizensLoader.letters.Writtable;
import citizensLoader.parser.Parser;
import citizensLoader.parser.XlsxParser;

/**
 * Main application
 * 
 * @author Claudia, Sara, Carla
 *
 */
public class LoadUsers {

	public static void main(String... args) throws IOException {
		final LoadUsers runner = new LoadUsers();
		runner.run(args);

	}

	/**
	 * Reads the file and output format of the letters specified
	 * 
	 * @param args
	 *            - file to parse and format of the letters that will be printed
	 *            to the citizens.
	 * @throws IOException
	 */
	void run(String... args) throws IOException {
		if (args.length < 2) {
			System.out.println("Argumentos: archivo a parsear, formato de las cartas");
		} else {
			File file = new File(args[0]);

			Writtable letters = getWrittable(args[1]);
			Parser parser = getParser(file, letters);

			if (parser == null) {
				System.out.println("Este formato de archivo no está soportado");
			} else {
				parser.readList();
			}
		}

	}

	/**
	 * Selects the class that matches the specified output type for the letters
	 * 
	 * @param string
	 *            - type of the output
	 * @return class of the specified output
	 */
	private Writtable getWrittable(String string) {
		Writtable writtable = null;
		if (string.equalsIgnoreCase("pdf")) {
			writtable = new PDFLetter();
		} else if (string.equalsIgnoreCase("docx")) {
			writtable = new WordLetter();
		} else if (string.equalsIgnoreCase("txt")) {
			writtable = new TxtLetter();
		}
		return writtable;
	}

	/**
	 * Method implemented in order to add in the future different input formats
	 * 
	 * @param file
	 *            - file that will be read by the parser
	 * @param letters
	 *            - type of the output
	 * @return parser of the type of the file extension
	 * @throws IOException
	 */
	private Parser getParser(File file, Writtable letters) throws IOException {
		Parser parser = null;
		String type = getFileExtension(file);
		if (type.equalsIgnoreCase("xlsx")) {
			parser = new XlsxParser(file, letters);
		}
		return parser;
	}

	/**
	 * Returns the extension of a file
	 * 
	 * @param file
	 *            - file for which we obtain the extension
	 * @return the extension of the file as a string
	 */
	private String getFileExtension(File file) {
		String name = file.getName();
		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}
}
