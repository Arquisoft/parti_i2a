package dashboard.dashboard.services.impl;

import dashboard.dashboard.model.User;
import dashboard.dashboard.repositories.UserRepository;
import dashboard.dashboard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Daniel on 10-Apr-17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findUserById(Long id) {
        return repository.findOne(id);
    }
}
