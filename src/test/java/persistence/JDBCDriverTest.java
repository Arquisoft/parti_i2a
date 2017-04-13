package persistence;

import org.junit.Test;
import participationSystem.persistence.Database;

import static org.junit.Assert.assertNotNull;

public class JDBCDriverTest {

	@Test
	public void test() {
		assertNotNull(Database.getConnection());
	}

}
