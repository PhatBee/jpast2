package vn.phatbee.jpast2.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.phatbee.jpast2.entity.Category;
import vn.phatbee.jpast2.entity.Video;
import vn.phatbee.jpast2.services.ICategoryService;
import vn.phatbee.jpast2.services.IVideoService;
import vn.phatbee.jpast2.services.impl.CategoryService;
import vn.phatbee.jpast2.services.impl.VideoService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static vn.phatbee.jpast2.utils.Constant.UPLOAD_DIRECTORY;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB

@WebServlet(urlPatterns = {"/admin/videos", "/admin/video/add","/admin/video/insert", "/admin/video/edit", "/admin/video/update", "/admin/video/delete"})
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IVideoService vidService = new VideoService();
    ICategoryService cateService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();
        if (url.contains("/admin/videos")){
            List<Video> list = vidService.findAll();
            req.setAttribute("listvideo", list);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        }
        else if (url.contains("/admin/video/add")){
            // Lấy danh sách Category từ Database
            List<Category> list = cateService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        } else if (url.contains("/admin/video/edit")){
            int id = Integer.parseInt(req.getParameter("id"));
            Video video = vidService.findById(id);
            req.setAttribute("video", video);
            List<Category> list = cateService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();
        if (url.contains("/admin/video/insert")){
            // Lấy dữ liệu từ form

            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            int active = Integer.parseInt(req.getParameter("active"));
            String description = req.getParameter("description");
            String poster = req.getParameter("poster");
            String title = req.getParameter("title");
            int views = Integer.parseInt(req.getParameter("views"));

            // Đưa dữ liệu vào model
            Video video = new Video();
            video.setDescription(description);
            video.setTitle(title);
            video.setViews(views);
            video.setActive(active);
            video.setCategory(cateService.findById(categoryid));


            String fname = "";
            String uploadPath = UPLOAD_DIRECTORY; //upload vào thư mục bất kỳ

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
                uploadDir.mkdir();
            try {
                Part part = req.getPart("poster1");
                if (part.getSize() > 0){
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    // Đổi tên file
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    // Upload file
                    part.write(uploadPath + "/" + fname);
                    // Ghi tên file vào data
                    video.setPoster(fname);
                } else if (poster != null) {
                    video.setPoster(poster);

                } else {
                    video.setPoster("avatar.png");
                }


            } catch (FileNotFoundException fne) {
                req.setAttribute("message", "There was an error: " + fne.getMessage());
            }

            // Gọi phương thức insert và truyền model vào
            vidService.insert(video);

            // Trả về view
            resp.sendRedirect(req.getContextPath() + "/admin/videos");

        } else if (url.contains("/admin/video/update")) {
            int id = Integer.parseInt(req.getParameter("videoId"));
            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            int active = Integer.parseInt(req.getParameter("active"));
            String description = req.getParameter("description");
            String poster = req.getParameter("poster");
            String title = req.getParameter("title");
            int views = Integer.parseInt(req.getParameter("views"));

            Video video = vidService.findById(id);

            // Đưa dữ liệu vào model
            video.setDescription(description);
            video.setTitle(title);
            video.setViews(views);
            video.setActive(active);
            video.setCategory(cateService.findById(categoryid));


            String fname = "";
            String uploadPath = UPLOAD_DIRECTORY; //upload vào thư mục bất kỳ

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
                uploadDir.mkdir();
            try {
                Part part = req.getPart("poster1");
                if (part.getSize() > 0){
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    // Đổi tên file
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    // Upload file
                    part.write(uploadPath + "/" + fname);
                    // Ghi tên file vào data
                    video.setPoster(fname);
                } else if (poster != null) {
                    video.setPoster(poster);

                } else {
                    video.setPoster("avatar.png");
                }


            } catch (FileNotFoundException fne) {
                req.setAttribute("message", "There was an error: " + fne.getMessage());
            }

            // Gọi phương thức insert và truyền model vào
            vidService.update(video);

            // Trả về view
            resp.sendRedirect(req.getContextPath() + "/admin/videos");

        }


    }
}
