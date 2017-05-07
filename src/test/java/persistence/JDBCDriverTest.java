package persistence;

import common.persistence.Database;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class JDBCDriverTest {

	@Test
	public void test() {
		assertNotNull(Database.getConnection());
	}

}
