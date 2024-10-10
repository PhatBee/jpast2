package vn.phatbee.services;

import vn.phatbee.entity.Category;

import java.util.List;

public interface ICategoryService {
    void insert(Category category);

    void update(Category category);

    void delete(int cateid) throws Exception;

    Category findById(int id);

    List<Category> findAll();

    List<Category> findByCategoryName(String catName);

    List<Category> findAll(int page, int size);

    int count();
}
