package parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import citizensLoader.letters.PDFLetter;
import citizensLoader.parser.XlsxParser;
import citizensLoader.persistence.UserDao;
import citizensLoader.persistence.impl.UserDaoImpl;
import participationSystem.dto.User;

/**
 * Clase que prueba la implementación del parseador de archivos en formato
 * excel.
 * 
 * @author Carla, Sara, Claudia
 */

public class XlsxParserTest {
	private UserDao uDao;
	private final static String JUAN = "User[Id: null; Name: Juan; Surname: Torres Pardo; "
			+ "Email: juan@example.com; Birth date: 10/10/1985; "
			+ "Address: C/ Federico García Lorca 2; Nationality: Español; DNI: 90500084Y; Polling station: 1]";
	private final static String LUIS = "User[Id: null; Name: Luis; Surname: López Fernando; "
			+ "Email: luis@example.com; Birth date: 02/03/1970; " + "Address: C/ Real Oviedo 2; Nationality: "
			+ "Español; DNI: 19160962F; Polling station: 2]";
	private final static String ANA = "User[Id: null; Name: Ana; Surname: Torres Pardo; "
			+ "Email: ana@example.com; Birth date: 01/01/1960; " + "Address: Av. De la Constitución 8; Nationality: "
			+ "Español; DNI: 09940449X; Polling station: 3]";
	private final static String PEDRO = "User[Id: null; Name: Pedro; Surname: Pérez García; "
			+ "Email: pedro@example.com; Birth date: 04/09/1979; " + "Address: C/ La playa 7; Nationality: "
			+ "Chileno; DNI: 56739582Y; Polling station: 4]";

	@Before
	public void setUp() throws IOException {
		this.uDao = new UserDaoImpl();
	}

	@Test
	public void testParseSmallFileCorrectly() throws IOException {
		File file = new File("src/test/resources/testSmall.xlsx");
		XlsxParser parser = new XlsxParser(file, new PDFLetter());
		List<User> users = parser.readListTest();

		assertEquals(JUAN, users.get(0).toString());

		assertEquals(LUIS, users.get(1).toString());

		assertEquals(ANA, users.get(2).toString());

		assertEquals(PEDRO, users.get(3).toString());
	}

	@Test
	public void testParseSmallFileDifferentInfo() throws IOException {
		// File file = new File("src/test/resources/testSmallDifferent.xlsx");
		File file = new File("src/test/resources/testSmall.xlsx");
		XlsxParser parser = new XlsxParser(file, new PDFLetter());
		List<User> users = parser.readListTest();

		// demostramos que la info en la bbdd no cambió
		// Citizen juan = db.findByDNI("90500084Y");
		// assertNotNull(juan);
		// assertEquals(JUAN, juan.toString());
		User juan = users.get(0);
		assertNotNull(juan);
		assertEquals(JUAN, juan.toString());

		// Citizen luis = db.findByDNI("19160962F");
		// assertNotNull(luis);
		// assertEquals(LUIS, luis.toString());
		User luis = users.get(1);
		assertNotNull(luis);
		assertEquals(LUIS, luis.toString());

		// Citizen ana = db.findByDNI("09940449X");
		// assertNotNull(ana);
		// assertEquals(ANA, ana.toString());
		User ana = users.get(2);
		assertNotNull(ana);
		assertEquals(ANA, ana.toString());

		// Citizen pedro = db.findByDNI("56739582Y");
		// assertNotNull(pedro);
		// assertEquals(PEDRO, pedro.toString());
		User pedro = users.get(3);
		assertNotNull(pedro);
		assertEquals(PEDRO, pedro.toString());
	}

}