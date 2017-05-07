package participationSystem.hello.persistence.impl;

import common.persistence.Database;
import participationSystem.hello.dto.Category;
import participationSystem.hello.persistence.CategoryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

	private static String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM categories";
	private static String SQL_FIND_CATEGORY_BY_ID = "SELECT * FROM categories WHERE ID=?";
	private static String SQL_INSERT_CATEGORY = "INSERT INTO categories (NAME) VALUES (?)";

	@Override
	public List<Category> findAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(SQL_FIND_ALL_CATEGORIES);

			rs = pst.executeQuery();
			while (rs.next()) {

				Integer id = rs.getInt("id");
				String name = rs.getString("name");

				Category category = new Category(id, name);
				categories.add(category);
			}

			return categories;

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
	public void createCategory(Category cat) {
		
		PreparedStatement pst = null;
		Connection con=null;
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(SQL_INSERT_CATEGORY);
			pst.setString(1, cat.getName());

			pst.executeUpdate();

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
	public Category getCategoryById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con=null;
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(SQL_FIND_CATEGORY_BY_ID);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			rs.next();
			Integer idCat = rs.getInt("id");
			String name = rs.getString("name");

			Category category = new Category(idCat, name);

			return category;

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

}
