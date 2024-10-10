package vn.phatbee.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.phatbee.configs.JPAConfig;
import vn.phatbee.dao.ICategoryDao;
import vn.phatbee.entity.Category;

import java.util.List;

public class CategoryDao implements ICategoryDao {
    @Override
    public void insert(Category category) {
        EntityManager emma = JPAConfig.getEntityManager();
        EntityTransaction trans = emma.getTransaction();

        try {
            trans.begin();
            // Gọi phương thức để insert, update, delete
            emma.persist(category);
            trans.commit();

        } catch (Exception ex){
            ex.printStackTrace();
            trans.rollback();
            throw ex;
        } finally {
            emma.close();
        }
    }

    @Override
    public void update(Category category) {
        EntityManager emma = JPAConfig.getEntityManager();
        EntityTransaction trans = emma.getTransaction();

        try {
            trans.begin();
            // Gọi phương thức để insert, update, delete
            emma.merge(category);
            trans.commit();

        } catch (Exception ex){
            ex.printStackTrace();
            trans.rollback();
            throw ex;
        } finally {
            emma.close();
        }
    }

    @Override
    public void delete(int cateid) throws Exception {
        EntityManager emma = JPAConfig.getEntityManager();
        EntityTransaction trans = emma.getTransaction();

        try {
            trans.begin();
            // Gọi phương thức để insert, update, delete
            Category category = emma.find(Category.class, cateid);
            if (category != null) {
                emma.remove(category);
            } else {
                throw new Exception("Không tìm thấy");
            }
            trans.commit();

        } catch (Exception ex){
            ex.printStackTrace();
            trans.rollback();
            throw ex;
        } finally {
            emma.close();
        }
    }

    @Override
    public Category findById(int id) {
        EntityManager emma = JPAConfig.getEntityManager();
        Category category = emma.find(Category.class, id);
        emma.close();
        return category;
    }

    @Override
    public List<Category> findAll() {
        EntityManager emma = JPAConfig.getEntityManager();
        TypedQuery<Category> query = emma.createNamedQuery("Category.findAll", Category.class);
        return query.getResultList();
    }

    @Override
    public List<Category> findByCategoryName(String catName) {
        EntityManager emma = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Category c WHERE c.categoryname = :catName";
        TypedQuery<Category> query = emma.createQuery(jpql, Category.class);
        query.setParameter("catName", "%" + catName + "%");
        return query.getResultList();
    }

    @Override
    public List<Category> findAll(int page, int size) {
        EntityManager emma = JPAConfig.getEntityManager();
        TypedQuery<Category> query = emma.createNamedQuery("Category.findAll", Category.class);
        query.setFirstResult(page*size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public int count()
    {
        EntityManager emma = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(c) FROM Category c";
        Query query = emma.createQuery(jpql);
        return ((Long)query.getSingleResult()).intValue();
    }

}
