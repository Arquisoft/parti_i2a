package participationSystem.persistence;

import citizensLoader.persistence.UserDao;
import citizensLoader.persistence.impl.UserDaoImpl;
import participationSystem.persistence.impl.CategoryDaoImpl;
import participationSystem.persistence.impl.CommentDaoImpl;
import participationSystem.persistence.impl.ProposalDaoImpl;
import participationSystem.persistence.impl.WordDaoImpl;

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
