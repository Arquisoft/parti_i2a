package participationSystem.persistence;

import participationSystem.persistence.impl.*;

public class Persistence {
	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}

	public static ProposalDao getProposalDao() {
		return new ProposalDaoImpl();
	}

	public static CommentDao getCommentaryDao() {
		return new CommentDaoImpl();
	}
	
	public static CategoryDao getCategoryDao() {
		return new CategoryDaoImpl();
	}
	
	public static WordDao getWordDao() {
		return new WordDaoImpl();
	}
}
