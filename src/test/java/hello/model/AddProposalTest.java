package hello.model;

import org.junit.Before;
import org.junit.Test;
import participationSystem.dto.Category;
import participationSystem.hello.model.AddProposal;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class AddProposalTest {
	
	private AddProposal c;
	private Category cat;

	@Before
	public void setUp() throws Exception {
		c = new AddProposal();
		cat = new Category("categoria");
	}
	
	@Test
	public void test() throws ParseException {
		assertNotNull(c);
		
		c.setText("El contenido");
		assertEquals("El contenido", c.getText());
		
		
		c.setCategory(cat);
		assertEquals(cat, c.getCategory());
		
		assertEquals("AddProposal [category=Category[Id: null; Name: categoria], text=El contenido]", c.toString());
		
		c.setText("");
		assertEquals("", c.getText());
		
		c.setCategory(null);
		assertEquals(null, c.getCategory());
	}

}