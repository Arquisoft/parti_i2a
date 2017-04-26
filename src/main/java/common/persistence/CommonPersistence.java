package common.persistence;

import common.persistence.impl.UserDaoImpl;
import participationSystem.hello.persistence.CategoryDao;
import participationSystem.hello.persistence.CommentDao;
import participationSystem.hello.persistence.ProposalDao;
import participationSystem.hello.persistence.WordDao;
import participationSystem.hello.persistence.impl.CategoryDaoImpl;
import participationSystem.hello.persistence.impl.CommentDaoImpl;
import participationSystem.hello.persistence.impl.ProposalDaoImpl;
import participationSystem.hello.persistence.impl.WordDaoImpl;

public class CommonPersistence {
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
