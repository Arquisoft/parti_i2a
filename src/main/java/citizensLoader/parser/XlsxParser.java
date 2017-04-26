package citizensLoader.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import citizensLoader.letters.SendLetters;
import citizensLoader.letters.Writtable;
import common.dto.User;
import common.persistence.UserDao;
import common.persistence.impl.UserDaoImpl;

/**
 * Implementation of a Parser for Excel files
 * @author Carla, Sara, Claudia
 *
 */
public class XlsxParser implements Parser {

	private Writtable letter;
	private UserDao userDao;
	private File file;

	public XlsxParser(File file, Writtable letter) throws IOException {
		userDao = new UserDaoImpl();
		this.file = file;
		this.letter = letter;
	}

	/**
	 * Reads an excel file and inserts in the Citizen
	 * database data read from it.
	 */
	@Override
	public List<User> readList() {
		List<User> users = new ArrayList<User>();

		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			int cols = 0; // No of columns
			int tmp = 0;

			// This trick ensures that we get the data properly even if it
			// doesn't start from first few rows
			for (int i = 0; i < 10 || i < rows; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if (tmp > cols)
						cols = tmp;
				}
			}

			parseUsers(users, sheet, rows);
			wb.close();
			
			userDao.sendToDB(users, file.getName());

		} catch (Exception ioe) {
			ioe.printStackTrace();
		}

		return users;

	}
	
	public List<User> readListTest() {
		List<User> users = new ArrayList<User>();

		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			int cols = 0; // No of columns
			int tmp = 0;

			// This trick ensures that we get the data properly even if it
			// doesn't start from first few rows
			for (int i = 0; i < 10 || i < rows; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if (tmp > cols)
						cols = tmp;
				}
			}

			parseUsersTest(users, sheet, rows);
			wb.close();

		} catch (Exception ioe) {
			ioe.printStackTrace();
		}

		return users;

	}
	
	public void insert() throws IOException{
		List<User> citizens = readList();
		userDao.sendToDB(citizens, file.getName());
	}

	/**
	 * Adds new citizens (read from the excel file) to the
	 * specified list.
	 * @param users - citizens list
	 * @param sheet - xlsx sheet
	 * @param rows - number of rows to be read
	 * @throws IOException
	 */
	private void parseUsers(List<User> users, XSSFSheet sheet, int rows) throws IOException {
		XSSFRow row;// se salta la primera línea con la cabecera
		for (int r = 1; r < rows; r++) {
			row = sheet.getRow(r);
			if (row != null) {
				String nombre = row.getCell(0).getStringCellValue();
				String apellidos = row.getCell(1).getStringCellValue();
				String email = row.getCell(2).getStringCellValue();
				Date nacimiento = row.getCell(3).getDateCellValue();
				String direccion = row.getCell(4).getStringCellValue();
				String nacionalidad = row.getCell(5).getStringCellValue();
				String dni = row.getCell(6).getStringCellValue();
				int polling = (int) row.getCell(7).getNumericCellValue();

				User citizen = new User(dni, nombre, apellidos, nacimiento, direccion, email, nacionalidad,
						polling);

				if (userDao.getUserByEmail(citizen.getEmail()) == null) {
					// create a random alphanumeric password and persists the
					// user
					citizen.setPassword(RandomStringUtils.randomAlphanumeric(10));

					SendLetters.send(citizen, letter);
				} 

				users.add(citizen);
			}
		}
		
		
	}
	
	private void parseUsersTest(List<User> users, XSSFSheet sheet, int rows) throws IOException {
		XSSFRow row;// se salta la primera línea con la cabecera
		for (int r = 1; r < rows; r++) {
			row = sheet.getRow(r);
			if (row != null) {
				String nombre = row.getCell(0).getStringCellValue();
				String apellidos = row.getCell(1).getStringCellValue();
				String email = row.getCell(2).getStringCellValue();
				Date nacimiento = row.getCell(3).getDateCellValue();
				String direccion = row.getCell(4).getStringCellValue();
				String nacionalidad = row.getCell(5).getStringCellValue();
				String dni = row.getCell(6).getStringCellValue();
				int polling = (int) row.getCell(7).getNumericCellValue();

				User citizen = new User(dni, nombre, apellidos, nacimiento, direccion, email, nacionalidad,
						polling);


				SendLetters.send(citizen, letter);
				

				users.add(citizen);
			}
		}
		
		
	}
}
