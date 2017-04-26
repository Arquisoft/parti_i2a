package jpa.services;

import jpa.model.Proposal;

/**
 * Created by Daniel on 10-Apr-17.
 */
public interface ProposalService {
    Proposal findProposalById(Long id);

}
