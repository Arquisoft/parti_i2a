package dto;

import org.junit.Before;
import org.junit.Test;
import participationSystem.dto.Comment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommentDtoTest {
	private Comment c;

	@Before
	public void setUp() throws Exception {
		c = new Comment();
	}

	@Test
	public void test() throws ParseException {
		assertNotNull(c);

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		c.setId(1);
		assertEquals(new Integer(1), c.getId());

		c.setContent("blablabla");
		assertEquals("blablabla", c.getContent());

		c.setProposalId(new Integer(1));
		assertEquals(new Integer(1), c.getProposalId());

		c.setUserId(new Integer(1));
		assertEquals(new Integer(1), c.getUserId());

		c.setVotes(1);
		assertEquals(1, c.getVotes());

		c.setFecha(format.parse("17/03/2017"));
		assertEquals(format.parse("17/03/2017"), c.getFecha());

		assertEquals("Comment[Id: 1; Content: blablabla; Votes: 1; Date: 17/03/2017; Proposal: 1; User: 1]", c.toString());
		
		c = new Comment(new Integer(1), "blablabla", 1, format.parse("17/03/2017"), new Integer(1), new Integer(1));
		assertEquals("Comment[Id: 1; Content: blablabla; Votes: 1; Date: 17/03/2017; Proposal: 1; User: 1]", c.toString());
		

		c = new Comment("blablabla", 1, format.parse("17/03/2017"), new Integer(1), new Integer(1));
		c.setId(new Integer(1));
		assertEquals("Comment[Id: 1; Content: blablabla; Votes: 1; Date: 17/03/2017; Proposal: 1; User: 1]", c.toString());
		
		
	}

}
