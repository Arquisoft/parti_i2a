package dashboard.dashboard.services;

import dashboard.dashboard.model.Category;

import java.util.List;

/**
 * Created by Daniel on 10-Apr-17.
 */
public interface CategoryService {

    Category findCategoryById(Long id);
    List<Category> findAll();
}
