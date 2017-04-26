package participationSystem.hello.persistence;

import java.util.List;

import participationSystem.hello.dto.Proposal;

public interface ProposalDao {

	Proposal getProposalById(Integer id);

	List<Proposal> getProposals();

	void deleteProposalById(Integer id);

	void createProposal(Proposal p) throws Exception;

	void voteProposal(Proposal proposal);
	
	void updateProposal(Proposal proposal);

}
