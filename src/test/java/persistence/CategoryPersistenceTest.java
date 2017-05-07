package persistence;

import common.persistence.CommonPersistence;
import org.junit.Test;
import participationSystem.hello.dto.Category;
import participationSystem.hello.persistence.CategoryDao;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alex on 07/05/2017.
 */
public class CategoryPersistenceTest {
    private CategoryDao cDao = CommonPersistence.getCategoryDao();

    @Test
    public void testGetCategories(){
        List<Category> categories= cDao.findAllCategories();

        for(Category c : categories) {
            assertNotNull(cDao.getCategoryById(c.getId()));
        }
    }
}
