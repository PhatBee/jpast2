package vn.phatbee.jpast2.services.impl;

import vn.phatbee.jpast2.dao.ICategoryDao;
import vn.phatbee.jpast2.dao.impl.CategoryDao;
import vn.phatbee.jpast2.entity.Category;
import vn.phatbee.jpast2.services.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {
    ICategoryDao cateDao = new CategoryDao();
    @Override
    public void insert(Category category) {
        cateDao.insert(category);

    }

    @Override
    public void update(Category category) {
        cateDao.update(category);

    }

    @Override
    public void delete(int cateid) throws Exception {
        cateDao.delete(cateid);

    }

    @Override
    public Category findById(int id) {
        return cateDao.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return cateDao.findAll();
    }

    @Override
    public List<Category> findByCategoryName(String catName) {
        return cateDao.findByCategoryName(catName);
    }

    @Override
    public List<Category> findAll(int page, int size) {
        return cateDao.findAll(page, size);
    }

    @Override
    public int count() {
        return cateDao.count();
    }
}
