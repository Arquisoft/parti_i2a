package participationSystem.persistence;

import participationSystem.dto.Category;

import java.util.List;

public interface CategoryDao {

	public List<Category> findAllCategories();
	public void createCategory(Category cat);
	Category getCategoryById(Integer id);
}
