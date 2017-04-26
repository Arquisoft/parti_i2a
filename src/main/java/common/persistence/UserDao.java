package common.persistence;

import java.io.IOException;
import java.util.List;

import common.dto.User;

public interface UserDao {

	User getUserById(Integer id);
	
	void createUser(User user);

	List<String> findAllEmails();

	User getUserByEmail(String email);

	void sendToDB(List<User> users, String name) throws IOException;

	boolean userExists(User user);

	boolean userHasSameData(User user);

	List<User> findAllUsers();
}
