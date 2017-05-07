package persistence;

import common.persistence.CommonPersistence;
import org.junit.Test;
import participationSystem.hello.dto.Comment;
import participationSystem.hello.persistence.CommentDao;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommentPersistenceTest {
	private CommentDao cDao = CommonPersistence.getCommentaryDao();

	@Test
	public void testVoteComment() {
		Comment c = cDao.getCommentById(1);
		int previousVotes = c.getVotes();
		cDao.voteComment(c);
		assertTrue(c.getVotes() == previousVotes + 1);
	}
	
	@Test
	public void testCreateComment() {
		Comment c = new Comment("TEST", 0, new Date(), 1, 1);
		try {
			cDao.createComment(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Comment> comments = cDao.getCommentsFromProposalId(1);
		c.setId(comments.get(comments.size()-1).getId());
		assertEquals(c, comments.get(comments.size()-1));
		
		List<Comment> pop = cDao.getCommentsFromProposalIdOrderedByPopularity(1);
		List<Comment> dat = cDao.getCommentsFromProposalIdOrderedByDate(1);
		
		assertTrue(pop.contains(c));
		assertTrue(dat.contains(c));
	}

}
