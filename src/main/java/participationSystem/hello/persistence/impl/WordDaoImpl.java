package participationSystem.hello.persistence.impl;

import common.persistence.Database;
import participationSystem.hello.persistence.WordDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDaoImpl implements WordDao {

	private static String SQL_INSERT_WORD = "INSERT INTO PUBLIC.WORDS (WORD) VALUES (?)";
	private static String SQL_FIND_ALL = "SELECT * FROM PUBLIC.WORDS";


	@Override
	public void add(List<String> palabras) {

		PreparedStatement pst = null;
		Connection con=null;
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(SQL_INSERT_WORD);

			for (String palabra : palabras) {
				pst.setString(1, palabra);

				pst.executeUpdate();
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}

	}

	@Override
	public List<String> findAll() {
			List<String> palabras = new ArrayList<String>();
			PreparedStatement pst = null;
			ResultSet rs = null;
			Connection con=null;
			try {
				con=Database.getConnection();
				pst = con.prepareStatement(SQL_FIND_ALL);

				rs = pst.executeQuery();
				while (rs.next()) {
					palabras.add(rs.getString("word"));
				}

				return palabras;

			} catch (SQLException e) {
				System.err.println(e);
				return null;
			} finally {
				try {
					rs.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					System.err.println(e);
				}
			}
		}

	@Override
	public boolean checkContent(String content) {
		for(String palabra : findAll()) {
			if(content.contains(palabra))
				return false;
		}
		return true;
	}

}
