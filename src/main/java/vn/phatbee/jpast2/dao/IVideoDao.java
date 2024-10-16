package vn.phatbee.jpast2.dao;

import vn.phatbee.jpast2.entity.Video;

import java.util.List;

public interface IVideoDao {
    void insert(Video video);
    void update(Video video);
    void delete(int videoid) throws Exception;
    Video findById(int id);
    List<Video> findAll();
    List<Video> findByTitle(String vidTitle);
    List<Video> findAll(int page, int size);
    int count();
}
