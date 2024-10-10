package vn.phatbee.jpast2.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.phatbee.jpast2.configs.JPAConfig;
import vn.phatbee.jpast2.dao.IVideoDao;
import vn.phatbee.jpast2.entity.Video;

import java.util.List;

public class VideoDao implements IVideoDao {

    @Override
    public void insert(Video video) {
        EntityManager emma = JPAConfig.getEntityManager();
        EntityTransaction trans = emma.getTransaction();

        try {
            trans.begin();
            // Gọi phương thức insert
            emma.persist(video);
            trans.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emma.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager emma = JPAConfig.getEntityManager();
        EntityTransaction trans = emma.getTransaction();

        try {
            trans.begin();
            // Gọi phương thức update
            emma.merge(video);
            trans.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emma.close();
        }
    }

    @Override
    public void delete(int videoid) throws Exception {
        EntityManager emma = JPAConfig.getEntityManager();
        EntityTransaction trans = emma.getTransaction();

        try {
            trans.begin();
            Video video = emma.find(Video.class, videoid);
            if (video != null) {
                // Gọi phương thức delete
                emma.remove(video);
            } else {
                throw new Exception("Không tìm thấy");
            }
            trans.commit();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            emma.close();
        }
    }

    @Override
    public Video findById(int id) {
        EntityManager emma = JPAConfig.getEntityManager();
        Video video = emma.find(Video.class, id);
        emma.close();
        return video;
    }

    @Override
    public List<Video> findAll() {
        EntityManager emma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = emma.createNamedQuery("Video.findAll", Video.class);
        return query.getResultList();
    }

    @Override
    public List<Video> findByTitle(String vidTitle) {
        EntityManager emma = JPAConfig.getEntityManager();
        String jpql = "select v from Video v where v.title = :vidTitle";
        TypedQuery<Video> query = emma.createQuery(jpql, Video.class);
        query.setParameter("vidTitle", vidTitle);
        return query.getResultList();
    }

    @Override
    public List<Video> findAll(int page, int size) {
        EntityManager emma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = emma.createNamedQuery("Video.findAll", Video.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager emma = JPAConfig.getEntityManager();
        String jpql = "select count(*) from Video";
        Query query = emma.createQuery(jpql);
        return (Integer) query.getSingleResult();
    }
}
