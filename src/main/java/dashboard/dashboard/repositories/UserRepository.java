package dashboard.dashboard.repositories;

import dashboard.dashboard.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Daniel on 10-Apr-17.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
