package vn.phatbee.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.phatbee.entity.Category;
import vn.phatbee.services.ICategoryService;
import vn.phatbee.services.impl.CategoryService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static vn.phatbee.utils.Constrant.UPLOAD_DIRECTORY;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB

@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/add", "/admin/category/insert","/admin/category/edit",
        "/admin/category/update", "/admin/category/delete", "/admin/category/search"})
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ICategoryService cateService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        if(url.contains("/admin/categories")){
            List<Category> list = cateService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);

        } else if (url.contains("/admin/category/add")) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
        } else if (url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));

            Category category = cateService.findById(id);

            req.setAttribute("cate", category);

            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                cateService.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect( req.getContextPath()+ "/admin/categories");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        if(url.contains("/admin/category/insert")){
            //  Lấy dữ liệu từ form
            String categoryname = req.getParameter("categoryname");
            String status = req.getParameter("status");
            int statuss = Integer.parseInt(status);
            String images = req.getParameter("images");

            // Đưa dữ liệu vào model
            Category category = new Category();
            category.setCategoryname(categoryname);
            category.setStatus(statuss);

            String fname = "";
            String uploadPath = UPLOAD_DIRECTORY; //upload vào thư mục bất kỳ

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
                uploadDir.mkdir();
            try {
                Part part = req.getPart("images1");
                if (part.getSize() > 0){
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    // Đổi tên file
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    // Upload file
                    part.write(uploadPath + "/" + fname);
                    // Ghi tên file vào data
                    category.setImages(fname);
                } else if (images != null) {
                    category.setImages(images);

                } else {
                    category.setImages("avatar.png");
                }


            } catch (FileNotFoundException fne) {
                req.setAttribute("message", "There was an error: " + fne.getMessage());
            }

            // Gọi phương thức insert và truyền model vào
            cateService.insert(category);

            // Trả về view
            resp.sendRedirect(req.getContextPath() + "/admin/categories");

        } else if (url.contains("/admin/category/update")) {
            // Lấy dữ liệu từ form
            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            String categoryname = req.getParameter("categoryname");
            int status = Integer.parseInt(req.getParameter("status"));
            String images = req.getParameter("images");

            // Đưa dữ liệu vào model
            Category category = new Category();
            category.setCategoryid(categoryid);
            category.setCategoryname(categoryname);
            category.setStatus(status);

            // Lưu hình cũ
            Category cateold = cateService.findById(categoryid);
            String fileold = cateold.getImages();

            // Xử lý images
            String fname = "";
            String uploadPath = UPLOAD_DIRECTORY; //upload vào thư mục bất kỳ

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
                uploadDir.mkdir();
            try {
                Part part = req.getPart("images1");
                if (part.getSize() > 0){
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    part.write(uploadPath + "/" + fname);
                    category.setImages(fname);
                } else if (images != null) {
                    category.setImages(images);
                } else {
                    category.setImages(fileold);
                }


            } catch (FileNotFoundException fne) {
                req.setAttribute("message", "There was an error: " + fne.getMessage());
            }

            // Gọi phương thức insert và truyền model vào
            cateService.update(category);

            // Trả về view
            resp.sendRedirect(req.getContextPath() + "/admin/categories");

        }
    }
}

