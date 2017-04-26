package participationSystem.hello.persistence;

import java.util.List;

import participationSystem.hello.dto.Comment;

public interface CommentDao {

	List<Comment> getCommentsFromProposalId(Integer id);

	void createComment(Comment p) throws Exception;
	
	Comment getCommentById(Integer parseInt);

	void voteComment(Comment comment);

	List<Comment> getCommentsFromProposalIdOrderedByPopularity(Integer idInt);

	List<Comment> getCommentsFromProposalIdOrderedByDate(Integer id);

}
