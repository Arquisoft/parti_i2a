package jpa.repositories;

import jpa.model.Proposal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Daniel on 10-Apr-17.
 */
@Repository("proposalRepository")
public interface ProposalRepository extends CrudRepository<Proposal,Long> {
}
