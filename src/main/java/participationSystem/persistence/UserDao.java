package participationSystem.persistence;

import participationSystem.dto.User;

import java.util.List;

public interface UserDao {

	User getUserById(Integer id);
	
	void createUser(User user);

	List<String> findAllEmails();

	User getUserByEmail(String email);
}
