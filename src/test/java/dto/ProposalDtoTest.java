package dto;

import org.junit.Before;
import org.junit.Test;
import participationSystem.dto.Proposal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProposalDtoTest {
	private Proposal p;

	@Before
	public void setUp() throws Exception {
		p = new Proposal();
	}

	@Test
	public void test() {
		assertNotNull(p);
		
		p.setId(1);
		assertEquals(new Integer(1), p.getId());
		
		p.setCategoryId(new Integer(1));
		assertEquals(new Integer(1), p.getCategoryId());
		
		p.setContent("Blablabla");
		assertEquals("Blablabla", p.getContent());
		
		p.setUserId(1);
		assertEquals(new Integer(1), p.getUserId());
		
		p.setVotes(1);
		assertEquals(new Integer(1), p.getVotes());
		
		assertEquals("Proposal[Id: 1; Content: Blablabla; Votes: 1; Category: 1; User: 1]", p.toString());
		
		p = new Proposal("Blablabla", 1, new Integer(1), new Integer(1));
		p.setId(new Integer(1));
		assertEquals("Proposal[Id: 1; Content: Blablabla; Votes: 1; Category: 1; User: 1]", p.toString());
		
	}

}
