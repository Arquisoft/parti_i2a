package participationSystem.hello.persistence.impl;

import common.persistence.CommonPersistence;
import common.persistence.Database;
import participationSystem.hello.dto.Comment;
import participationSystem.hello.persistence.CommentDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDaoImpl implements CommentDao {

	private static String SQL_COMMENT_ORDER_BY_POPULARITY = "SELECT * FROM comments WHERE PROPOSAL_ID=? ORDER BY VOTES DESC";
	private static String SQL_INSERT_COMMENT = "INSERT INTO comments (content, votes, date, user_id, proposal_id) "
			+ "VALUES (?, ?, ?, ?, ?)";
	private static String SQL_PROPOSAL_COMMENT = "SELECT * FROM comments WHERE PROPOSAL_ID=?";
	private static String SQL_COMMENT_ORDER_BY_DATE = "SELECT * FROM comments WHERE PROPOSAL_ID=? ORDER BY date DESC";
	private static String SQL_FIND_COMMENT_BY_ID = "SELECT * FROM comments WHERE ID=?";

	@Override
	public List<Comment> getCommentsFromProposalId(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Comment> comments = new ArrayList<Comment>();
		Connection con=null;
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(SQL_PROPOSAL_COMMENT);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			while (rs.next()) {

				Integer idComment = rs.getInt("id");
				String content = rs.getString("content");
				Integer votes = rs.getInt("votes");
				Date fecha = rs.getDate("date");
				Integer userID = rs.getInt("user_id");
				Integer proposalID = rs.getInt("proposal_id");

				Comment comment = new Comment(idComment, content, votes, fecha, userID, proposalID);

				comments.add(comment);
			}
			return comments;

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
	public void createComment(Comment p) throws Exception {
		PreparedStatement pst = null;
		Connection con=null;
		try {
			con=Database.getConnection();
			if (CommonPersistence.getWordDao().checkContent(p.getContent())) {
				pst = con.prepareStatement(SQL_INSERT_COMMENT);
				pst.setString(1, p.getContent());
				pst.setInt(2, p.getVotes());
				pst.setDate(3, new java.sql.Date(p.getFecha().getTime()));
				pst.setInt(4, p.getUserId());
				pst.setInt(5, p.getProposalId());

				pst.executeUpdate();
			} else {
				throw new Exception("Hay palabras no permitidas");
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
	public List<Comment> getCommentsFromProposalIdOrderedByDate(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Comment> comments = new ArrayList<Comment>();
		Connection con=null;
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(SQL_COMMENT_ORDER_BY_DATE);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {

				Integer idComment = rs.getInt("id");
				String content = rs.getString("content");
				Integer votes = rs.getInt("votes");
				Date fecha = rs.getDate("date");
				Integer userID = rs.getInt("user_id");
				Integer proposalID = rs.getInt("proposal_id");

				Comment comment = new Comment(idComment, content, votes, fecha, userID, proposalID);

				comments.add(comment);
			}
			return comments;

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
	public Comment getCommentById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con=null;

		try {
			con=Database.getConnection();
			pst = con.prepareStatement(SQL_FIND_COMMENT_BY_ID);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			rs.next();
			Integer idComment = rs.getInt("id");
			String content = rs.getString("content");
			Integer votes = rs.getInt("votes");
			Date fecha = rs.getDate("date");
			Integer userID = rs.getInt("user_id");
			Integer proposalID = rs.getInt("proposal_id");

			Comment comment = new Comment(idComment, content, votes, fecha, userID, proposalID);

			return comment;

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
	public void voteComment(Comment c) {
		c.setVotes(c.getVotes() + 1);
		updateComment(c);
	}

	public void updateComment(Comment c) {
		PreparedStatement pst = null;
		Connection con=null;
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(
					"UPDATE comments SET CONTENT = ?, " + "VOTES = ?, date = ? WHERE ID = ?");
			pst.setString(1, c.getContent());
			pst.setInt(2, c.getVotes());
			pst.setDate(3, new java.sql.Date(c.getFecha().getTime()));
			pst.setInt(4, c.getId());

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
	public List<Comment> getCommentsFromProposalIdOrderedByPopularity(Integer idInt) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Comment> comments = new ArrayList<Comment>();
		Connection con=null;
		try {
			con=Database.getConnection();
			pst = con.prepareStatement(SQL_COMMENT_ORDER_BY_POPULARITY);
			pst.setInt(1, idInt);
			rs = pst.executeQuery();
			while (rs.next()) {

				Integer idComment = rs.getInt("id");
				String content = rs.getString("content");
				Integer votes = rs.getInt("votes");
				Date fecha = rs.getDate("date");
				Integer userID = rs.getInt("user_id");
				Integer proposalID = rs.getInt("proposal_id");

				Comment comment = new Comment(idComment, content, votes, fecha, userID, proposalID);

				comments.add(comment);
			}
			return comments;

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
