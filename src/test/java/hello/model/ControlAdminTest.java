package hello.model;

import org.junit.Before;
import org.junit.Test;
import participationSystem.hello.model.ControlAdmin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ControlAdminTest {
	
	private ControlAdmin c;

	@Before
	public void setUp() throws Exception {
		c = new ControlAdmin();
	}
	
	@Test
	public void test() throws ParseException {
		assertNotNull(c);
		
		c.setText("A,b,c");
		assertEquals("A,b,c", c.getText());
		
		List<String> palabras = new ArrayList<String>();
		palabras.add("A");
		palabras.add("b");
		palabras.add("c");
		assertEquals(palabras.get(0), c.getPalabras().get(0));
		assertEquals(palabras.get(1), c.getPalabras().get(1));
		assertEquals(palabras.get(2), c.getPalabras().get(2));
		c.setPalabras(palabras);
		assertEquals(palabras, c.getPalabras());
		
		
		c.setCategory("category");
		assertEquals("category", c.getCategory());
		
		c.setProposal("proposal");
		assertEquals("proposal", c.getProposal());
	}

}