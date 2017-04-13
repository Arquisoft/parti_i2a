package persistence;

import org.junit.Test;
import participationSystem.dto.Proposal;
import participationSystem.persistence.Persistence;
import participationSystem.persistence.ProposalDao;

import java.util.List;

import static org.junit.Assert.*;

public class ProposalPersistenceTest {
	private ProposalDao pDao = Persistence.getProposalDao();

	@Test
	public void testVoteProposal() {
		Proposal p = pDao.getProposalById(1);
		int previousVotes = p.getVotes();
		pDao.voteProposal(p);
		assertTrue(p.getVotes() == previousVotes + 1);
	}
	
	@Test
	public void testGetProposals() {
		List<Proposal> proposals = pDao.getProposals();
		
		for(Proposal p : proposals) {
			assertNotNull(pDao.getProposalById(p.getId()));
		}
	}
	
	@Test
	public void testCreateProposal() {
		Proposal p = new Proposal("TEST", 0, 1, 1);
		
		try {
		pDao.createProposal(p); } catch(Exception e) {
			
		}
		
		List<Proposal> proposals = pDao.getProposals();
		Proposal created = proposals.get(proposals.size()-1);
		
		assertEquals(p.getContent(), created.getContent());
		
		Integer id = created.getId();
		pDao.deleteProposalById(id);
		assertNull(pDao.getProposalById(id));
		
	}

}
