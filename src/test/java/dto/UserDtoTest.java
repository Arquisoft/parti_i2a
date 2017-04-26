package dto;

import org.junit.Before;
import org.junit.Test;

import common.dto.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDtoTest {
	private User u;

	@Before
	public void setUp() throws Exception {
		u = new User();
	}

	@Test
	public void test() throws ParseException {
		assertNotNull(u);

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		u.setId(new Integer(1));
		assertEquals(new Integer(1), u.getId());

		u.setName("name");
		assertEquals("name", u.getFirstName());

		u.setSurname("surname");
		assertEquals("surname", u.getLastName());

		u.setAddress("address");
		assertEquals("address", u.getAddress());

		u.setDni("dni");
		assertEquals("dni", u.getDni());

		u.setEmail("email");
		assertEquals("email", u.getEmail());

		u.setNationality("nation");
		assertEquals("nation", u.getNationality());

		u.setPassword("pass");
		assertEquals("pass", u.getPassword());

		u.setPollingStation(2);
		assertEquals(2, u.getPollingStation());

		u.setBirthdate(format.parse("17/03/1967"));
		assertEquals(format.parse("17/03/1967"), u.getBirthdate());

		assertEquals(
				"User[Id: 1; Name: name; Surname: surname; Email: email; Birth date: 17/03/1967;"
				+ " Address: address; Nationality: nation; DNI: dni; Polling station: 2]",
				u.toString());
		
		u = new User("dni", "name", "surname", format.parse("17/03/1967"), "address", "email", "nation", 2);
		u.setId(new Integer(1));
		assertEquals(
				"User[Id: 1; Name: name; Surname: surname; Email: email; Birth date: 17/03/1967;"
				+ " Address: address; Nationality: nation; DNI: dni; Polling station: 2]",
				u.toString());
	}

}
