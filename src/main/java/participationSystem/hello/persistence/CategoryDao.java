package participationSystem.hello.persistence;

import java.util.List;

import participationSystem.hello.dto.Category;

public interface CategoryDao {

	public List<Category> findAllCategories();
	public void createCategory(Category cat);
	Category getCategoryById(Integer id);
}
