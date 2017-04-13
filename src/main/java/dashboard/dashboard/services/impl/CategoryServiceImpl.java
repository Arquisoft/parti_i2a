package dashboard.dashboard.services.impl;

import dashboard.dashboard.model.Category;
import dashboard.dashboard.repositories.CategoryRepository;
import dashboard.dashboard.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Daniel on 10-Apr-17. Category service implementation
 */
@Component
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll(){return (List<Category>) repository.findAll();}

    @Override
    public Category findCategoryById(Long id) {
        return repository.findOne(id);
    }
}
