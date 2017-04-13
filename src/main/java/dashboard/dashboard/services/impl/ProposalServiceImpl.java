package dashboard.dashboard.services.impl;

import dashboard.dashboard.model.Proposal;
import dashboard.dashboard.repositories.ProposalRepository;
import dashboard.dashboard.services.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Daniel on 10-Apr-17.
 */
@Service
public class ProposalServiceImpl implements ProposalService {

    @Qualifier("proposalRepository")
    @Autowired
    private ProposalRepository proposalRepository;


    @Override
    public Proposal findProposalById(Long id) {
        return proposalRepository.findOne(id);
    }
}
