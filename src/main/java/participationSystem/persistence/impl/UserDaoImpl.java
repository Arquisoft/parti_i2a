package participationSystem.persistence.impl;

import participationSystem.dto.User;
import participationSystem.persistence.Database;
import participationSystem.persistence.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {
	// private static String SQL_FIND_USER_BY_ID =
	// Conf.getInstance().getProperty("SQL_FIND_USER_BY_ID");

	private static String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE ID=?";
	private static String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE EMAIL=?";
	private static String SQL_FIND_ALL_EMAILS = "SELECT EMAIL FROM users";
	private static String SQL_INSERT_USER = "INSERT INTO users (dni, firstname, lastname, "
			+ "password, email, birthdate, address, nationality, pollingstation) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private Connection con = Database.getConnection();

	@Override
	public User getUserById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(SQL_FIND_USER_BY_ID);
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
				emails.add(rs.getString(1));
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
		// TODO de momento la password sera siempre "pass", 
		// luego se cambiara a la generada en la primera entrega
		
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL_INSERT_USER);
			pst.setString(1, user.getDni());
			pst.setString(2, user.getFirstName());
			pst.setString(3, user.getLastName());
			pst.setString(4, "pass");
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
			rs.next();

			Integer idBase = rs.getInt("id");
			String dni = rs.getString("dni");
			String name = rs.getString("firstName");
			String surname = rs.getString("lastName");
			String emailEste = rs.getString("email");
			Date birth = rs.getDate("birthDate");
			String nationality = rs.getString("nationality");
			String address = rs.getString("address");
			int polling = rs.getInt("pollingStation");
			String pass = rs.getString("password");

			User user = new User(dni, name, surname, birth, address, emailEste, nationality, polling);
			user.setId(idBase);
			user.setPassword(pass);

			return user;

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
