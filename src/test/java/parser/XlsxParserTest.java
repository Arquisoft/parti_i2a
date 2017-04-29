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
import common.dto.User;
import common.persistence.CommonPersistence;
import common.persistence.UserDao;

/**
 * Clase que prueba la implementación del parseador de archivos en formato
 * excel.
 * 
 * @author Carla, Sara, Claudia
 */

public class XlsxParserTest {
	private UserDao uDao;

	private final static String JUAN = "User[Name: Juan; Surname: Torres Pardo; "
			+ "Email: juan@example.com; Birth date: 10/10/1985; "
			+ "Address: C/ Federico García Lorca 2; Nationality: Español; DNI: 90500084Y; Polling station: 1]";
	private final static String LUIS = "User[Name: Luis; Surname: López Fernando; "
			+ "Email: luis@example.com; Birth date: 02/03/1970; " + "Address: C/ Real Oviedo 2; Nationality: "
			+ "Español; DNI: 19160962F; Polling station: 2]";
	private final static String ANA = "User[Name: Ana; Surname: Torres Pardo; "
			+ "Email: ana@example.com; Birth date: 01/01/1960; " + "Address: Av. De la Constitución 8; Nationality: "
			+ "Español; DNI: 09940449X; Polling station: 3]";
	private final static String PEDRO = "User[Name: Pedro; Surname: Pérez García; "
			+ "Email: pedro@example.com; Birth date: 04/09/1979; " + "Address: C/ La playa 7; Nationality: "
			+ "Chileno; DNI: 56739582Y; Polling station: 4]";

	@Before
	public void setUp() throws IOException {
		this.uDao = CommonPersistence.getUserDao();
	}

	@Test
	public void testParseSmallFileCorrectly() throws IOException {
		File file = new File("src/test/resources/testSmall.xlsx");
		XlsxParser parser = new XlsxParser(file, new PDFLetter());
		List<User> users = parser.readList();

		assertEquals(JUAN, users.get(0).toStringNoId());

		assertEquals(LUIS, users.get(1).toStringNoId());

		assertEquals(ANA, users.get(2).toStringNoId());

		assertEquals(PEDRO, users.get(3).toStringNoId());
	}

	@Test
	public void testParseSmallFileDifferentInfo() throws IOException {
		File file = new File("src/test/resources/testSmall.xlsx");
		XlsxParser parser = new XlsxParser(file, new PDFLetter());
		parser.readList();

		// demostramos que la info en la bbdd no cambió
		User juan = uDao.getUserByEmail("juan@example.com");
		assertNotNull(juan);
		assertEquals(JUAN, juan.toStringNoId());

		User luis = uDao.getUserByEmail("luis@example.com");
		assertNotNull(luis);
		assertEquals(LUIS, luis.toStringNoId());

		User ana = uDao.getUserByEmail("ana@example.com");
		assertNotNull(ana);
		assertEquals(ANA, ana.toStringNoId());

		User pedro = uDao.getUserByEmail("pedro@example.com");
		assertNotNull(pedro);
		assertEquals(PEDRO, pedro.toStringNoId());

	}

}