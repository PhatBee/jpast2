package vn.phatbee.jpast2.services.impl;

import vn.phatbee.jpast2.dao.IVideoDao;
import vn.phatbee.jpast2.dao.impl.VideoDao;
import vn.phatbee.jpast2.entity.Video;
import vn.phatbee.jpast2.services.IVideoService;

import java.util.List;

public class VideoService implements IVideoService {
    IVideoDao vidDao = new VideoDao();
    @Override
    public void insert(Video video) {
        vidDao.insert(video);
    }

    @Override
    public void update(Video video) {
        vidDao.update(video);
    }

    @Override
    public void delete(int videoid) throws Exception {
        vidDao.delete(videoid);
    }

    @Override
    public Video findById(int id) {
        return vidDao.findById(id);
    }

    @Override
    public List<Video> findAll() {
        return vidDao.findAll();
    }

    @Override
    public List<Video> findByTitle(String vidTitle) {
        return vidDao.findByTitle(vidTitle);
    }

    @Override
    public List<Video> findAll(int page, int size) {
        return vidDao.findAll(page, size);
    }

    @Override
    public int count() {
        return vidDao.count();
    }
}
