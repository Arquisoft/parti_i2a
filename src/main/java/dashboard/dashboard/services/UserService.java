package dashboard.dashboard.services;

import dashboard.dashboard.model.User;

/**
 * Created by Daniel on 10-Apr-17.
 */
public interface UserService {

    User findUserById(Long id);
}
