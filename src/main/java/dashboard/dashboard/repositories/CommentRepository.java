package dashboard.dashboard.repositories;

import dashboard.dashboard.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Daniel on 10-Apr-17.
 */
@Repository
public interface CommentRepository  extends CrudRepository<Comment,Long> {
}