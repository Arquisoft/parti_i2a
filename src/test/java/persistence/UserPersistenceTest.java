package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import citizensLoader.persistence.UserDao;
import participationSystem.dto.User;
import participationSystem.persistence.Persistence;

public class UserPersistenceTest {
	private UserDao dao = Persistence.getUserDao();

	@Test
	public void testFindById() throws ParseException {
		Date simpleDate = new SimpleDateFormat("dd/MM/yyyy").parse("25/03/1950");
		
		User user = new User("12345678A", "Pepe", "Calleja", simpleDate, "Oviedo", "calleja@email.com", "Spanish", 2);
		user.setId(1);
		user.setPassword("password");
		
		User found = dao.getUserById(1);
		System.out.println(found);
		System.out.println(user);
		assertEquals(user, found);
	}

	@Test
	public void createUser() throws ParseException {
		Date simpleDate = new SimpleDateFormat("dd/MM/yyyy").parse("25/03/1950");
		
		User user = new User("987654312A", "Pepe", "Calleja", simpleDate, "Oviedo", "calleja@email.com", "Spanish", 2);
		user.setPassword("password");
		
		dao.createUser(user);
		user.setId(6);
		
		User found = dao.getUserById(6);
		System.out.println(found);
		System.out.println(user);
		
		assertEquals(user, found);
	}
	
	@Test
	public void findAllEmails() throws ParseException {
		List<String> emails = dao.findAllEmails();
		
		for(String email : emails) {
			assertNotNull(dao.getUserByEmail(email));
		}
		
	}

}
