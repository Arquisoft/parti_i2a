package participationSystem.persistence;

import participationSystem.dto.Proposal;

import java.util.List;

public interface ProposalDao {

	Proposal getProposalById(Integer id);

	List<Proposal> getProposals();

	void deleteProposalById(Integer id);

	void createProposal(Proposal p) throws Exception;

	void voteProposal(Proposal proposal);
	
	void updateProposal(Proposal proposal);

}
