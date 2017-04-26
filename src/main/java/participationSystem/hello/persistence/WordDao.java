package participationSystem.hello.persistence;

import java.util.List;


public interface WordDao {

	void add(List<String> palabras);
	
	boolean checkContent(String content);

	List<String> findAll();

}
