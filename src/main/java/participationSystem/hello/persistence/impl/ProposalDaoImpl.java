package participationSystem.hello.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.persistence.Database;
import common.persistence.CommonPersistence;
import participationSystem.hello.dto.Proposal;
import participationSystem.hello.persistence.ProposalDao;

public class ProposalDaoImpl implements ProposalDao {
	private static String SQL_FIND_BY_ID = "SELECT * FROM proposals WHERE ID=?";
	private static String SQL_FIND_ALL = "SELECT * FROM proposals";
	private static String SQL_INSERT = "INSERT INTO proposals(content, votes, user_id, category_id) VALUES (?,?,?, ?)";
	private static String SQL_UPDATE = "UPDATE proposals SET CONTENT = ?, VOTES = ?, CATEGORY_ID = ?" + "WHERE ID = ?";
	private static String SQL_DELETE_COMMENTS =  "DELETE FROM comments WHERE PROPOSAL_ID=?";
	private static String SQL_DELETE_PROPOSAL =  "DELETE FROM proposals WHERE ID=?";

	private Connection con = Database.getConnection();

	@Override
	public Proposal getProposalById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(SQL_FIND_BY_ID);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			while (rs.next()) {

				Integer idProp = rs.getInt("id");
				String content = rs.getString("content");
				Integer votes = rs.getInt("votes");
				Integer category_id = rs.getInt("category_id");
				Integer userID = rs.getInt("user_id");

				Proposal proposal = new Proposal();
				proposal.setVotes(votes);
				proposal.setUserId(userID);
				proposal.setContent(content);
				proposal.setCategoryId(category_id);
				proposal.setId(idProp);

				return proposal;
			}
			
			return null;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public List<Proposal> getProposals() {
		List<Proposal> proposals = new ArrayList<Proposal>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(SQL_FIND_ALL);

			rs = pst.executeQuery();
			while (rs.next()) {

				Integer idProp = rs.getInt("id");
				String content = rs.getString("content");
				Integer votes = rs.getInt("votes");
				Integer category_id = rs.getInt("category_id");
				Integer userID = rs.getInt("user_id");

				Proposal proposal = new Proposal(content, votes, category_id, userID);
				proposal.setId(idProp);

				proposals.add(proposal);
			}

			return proposals;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public void createProposal(Proposal p) throws Exception {
		PreparedStatement pst = null;
		try {

			if (CommonPersistence.getWordDao().checkContent(p.getContent())) {

				pst = con.prepareStatement(SQL_INSERT);
				pst.setString(1, p.getContent());
				pst.setInt(2, p.getVotes());
				pst.setInt(3, p.getUserId());
				pst.setInt(4, p.getCategoryId());

				pst.executeUpdate();
			} else {
				throw new Exception("Hay palabras no permitidas");
			}

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
	public void voteProposal(Proposal p) {
		p.setVotes(p.getVotes() + 1);
		updateProposal(p);

	}

	public void updateProposal(Proposal p) {
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL_UPDATE);
			pst.setString(1, p.getContent());
			pst.setInt(2, p.getVotes());
			pst.setInt(3, p.getCategoryId());
			pst.setInt(4, p.getId());

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
	public void deleteProposalById(Integer id) {
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL_DELETE_COMMENTS);
			pst.setInt(1, id);
			pst.executeUpdate();
			pst.close();

			pst = con.prepareStatement(SQL_DELETE_PROPOSAL);
			pst.setInt(1, id);
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

}
