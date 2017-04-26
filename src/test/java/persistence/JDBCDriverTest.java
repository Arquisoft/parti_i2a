package persistence;

import org.junit.Test;

import common.persistence.Database;

import static org.junit.Assert.assertNotNull;

public class JDBCDriverTest {

	@Test
	public void test() {
		assertNotNull(Database.getConnection());
	}

}
