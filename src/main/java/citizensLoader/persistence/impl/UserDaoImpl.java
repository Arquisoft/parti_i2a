package citizensLoader.persistence.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import citizensLoader.logger.ReportWritter;
import participationSystem.persistence.Database;
import citizensLoader.persistence.UserDao;
import participationSystem.dto.User;

public class UserDaoImpl implements UserDao {
	// private static String SQL_FIND_USER_BY_ID =
	// Conf.getInstance().getProperty("SQL_FIND_USER_BY_ID");

	private ReportWritter log;

	private static String SQL_FIND_USER_BY_ID = "SELECT * FROM PUBLIC.USERS WHERE ID=?";
	private static String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM PUBLIC.USERS WHERE EMAIL=?";
	private static String SQL_FIND_ALL_EMAILS = "SELECT EMAIL FROM PUBLIC.USERS";
	private static String SQL_FIND_ALL_USERS = "SELECT * FROM PUBLIC.USERS";
	private static String SQL_INSERT_USER = "INSERT INTO PUBLIC.USERS (dni, firstName, lastName, "
			+ "password, email, birthDate, address, nationality, pollingStation) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private Connection con = Database.getConnection();

	public UserDaoImpl() {
		log = new ReportWritter();
	}

	@Override
	public User getUserById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(SQL_FIND_USER_BY_ID);
			if (id != null) {
				pst.setInt(1, id);

				rs = pst.executeQuery();
				rs.next();

				Integer idBase = rs.getInt("id");
				String dni = rs.getString("dni");
				String name = rs.getString("firstName");
				String surname = rs.getString("lastName");
				String email = rs.getString("email");
				Date birth = rs.getDate("birthDate");
				String nationality = rs.getString("nationality");
				String address = rs.getString("address");
				int polling = rs.getInt("pollingStation");
				String pass = rs.getString("password");

				User user = new User(dni, name, surname, birth, address, email, nationality, polling);
				user.setId(idBase);
				user.setPassword(pass);

				return user;
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public List<String> findAllEmails() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> emails = new ArrayList<String>();
		try {
			pst = con.prepareStatement(SQL_FIND_ALL_EMAILS);

			rs = pst.executeQuery();
			while (rs.next()) {
				emails.add(rs.getString("email"));
			}

			return emails;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public void createUser(User user) {
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL_INSERT_USER);
			pst.setString(1, user.getDni());
			pst.setString(2, user.getFirstName());
			pst.setString(3, user.getLastName());
			pst.setString(4, user.getPassword());
			pst.setString(5, user.getEmail());
			pst.setDate(6, new java.sql.Date(user.getBirthdate().getTime()));
			pst.setString(7, user.getAddress());
			pst.setString(8, user.getNationality());
			pst.setInt(9, user.getPollingStation());

			pst.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}

	}

	@Override
	public User getUserByEmail(String email) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(SQL_FIND_USER_BY_EMAIL);
			pst.setString(1, email);

			rs = pst.executeQuery();
			if (rs.next()) {

				Integer idBase = rs.getInt("id");
				String dni = rs.getString("dni");
				String name = rs.getString("firstName");
				String surname = rs.getString("lastName");
				Date birth = rs.getDate("birthDate");
				String nationality = rs.getString("nationality");
				String address = rs.getString("address");
				int polling = rs.getInt("pollingStation");
				String pass = rs.getString("password");

				User user = new User(dni, name, surname, birth, address, email, nationality, polling);
				user.setId(idBase);
				user.setPassword(pass);

				return user;
			}
			return null;

		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	/**
	 * Method that inserts in the database all the parsed users. Prints a log if
	 * there is some type of error.
	 * 
	 * @param list
	 *            - list of citizens that have been parsed
	 * @param filename
	 *            - name of the document parsed which will appear in the log
	 */
	@Override
	public void sendToDB(List<User> list, String filename) throws IOException {

		for (User c : list) {
			// if the user is not in the database, persist
			if (!citizenExists(c)) {
				createUser(c);
			} else {
				log.record("The citizen " + c.getFirstName() + " " + c.getLastName() + " has already an user",
						filename);
				// and if the data is different we put that error in the log
				if (!citizenHasSameData(c)) {
					log.record("The citizen " + c.getFirstName() + " " + c.getLastName() + " has different data in the"
							+ " database and in the document", filename);
				}
			}
		}
		log.close();
	}

	/**
	 * Checks if a citizen already exists in the database
	 * 
	 * @param c
	 *            - citizen to check
	 * @return true if it exists, false if not
	 */
	@Override
	public boolean citizenExists(User user) {
		User userDB = getUserByEmail(user.getEmail());
		return userDB != null;
	}

	/**
	 * Checks if there is some user already in the database with wrong data
	 * 
	 * @param c
	 *            Citizen
	 * @return true if there is no user with different data, false otherwise
	 */
	@Override
	public boolean citizenHasSameData(User user) {
		User userDB = getUserByEmail(user.getEmail());
		return !userDB.equals(user);
	}

	@Override
	public List<User> findAllUsers() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			pst = con.prepareStatement(SQL_FIND_ALL_USERS);

			rs = pst.executeQuery();
			while (rs.next()) {
				Integer idBase = rs.getInt("id");
				String dni = rs.getString("dni");
				String name = rs.getString("firstName");
				String surname = rs.getString("lastName");
				Date birth = rs.getDate("birthDate");
				String email = rs.getString("email");
				String nationality = rs.getString("nationality");
				String address = rs.getString("address");
				int polling = rs.getInt("pollingStation");
				String pass = rs.getString("password");

				User user = new User(dni, name, surname, birth, address, email, nationality, polling);
				user.setId(idBase);
				user.setPassword(pass);
			}

			return users;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
}
