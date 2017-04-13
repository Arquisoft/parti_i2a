package persistence;

import org.junit.Test;
import participationSystem.persistence.Persistence;
import participationSystem.persistence.WordDao;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class WordsPersistenceTest {
	
	private WordDao dao = Persistence.getWordDao();
	

	@Test
	public void test() {
		List<String> words = new ArrayList<>();
		words.add("Shit");
		dao.add(words);
		
		assertEquals("Shit", dao.findAll().get(0));
		
		assertTrue(dao.checkContent("Just a text with shit"));
		
	}

}
