package vn.phatbee.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.phatbee.entity.Category;

public class Test {
    public static void main(String[] args) {
        EntityManager emma = JPAConfig.getEntityManager();
        EntityTransaction trans = emma.getTransaction();

        // Chạy thử
        Category cate = new Category();
        cate.setCategoryname("Iphone");
        cate.setImages("abc.jpg");
        cate.setStatus(1);
        //
//50801
        try {
            trans.begin();
            // Gọi phương thức để insert, update, delete
            emma.persist(cate);
            trans.commit();
            System.out.println("Complete");

        } catch (Exception ex){
            ex.printStackTrace();
            trans.rollback();
            throw ex;
        } finally {
            emma.close();
        }
    }
}
