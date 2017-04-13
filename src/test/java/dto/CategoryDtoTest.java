package dto;

import org.junit.Before;
import org.junit.Test;
import participationSystem.dto.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CategoryDtoTest {
	private Category c;

	@Before
	public void setUp() throws Exception {
		c = new Category();
	}

	@Test
	public void test() {
		assertNotNull(c);
		
		c.setId(new Integer(1));
		assertEquals(new Integer(1), c.getId());
		
		c.setName("blablabla");
		assertEquals("blablabla", c.getName());
		
		assertEquals("Category[Id: 1; Name: blablabla]", c.toString());
		
		c = new Category("blablabla");
		c.setId(1);
		assertEquals("Category[Id: 1; Name: blablabla]", c.toString());
		

		c = new Category(new Integer(1), "blablabla");
		assertEquals("Category[Id: 1; Name: blablabla]", c.toString());
	}

}
