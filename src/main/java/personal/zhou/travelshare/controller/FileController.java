package personal.zhou.travelshare.controller;

import com.google.common.reflect.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import personal.zhou.travelshare.common.storage.BaseStorageService;
import personal.zhou.travelshare.domain.entity.StorageEntity;
import personal.zhou.travelshare.service.StorageService;
import personal.zhou.travelshare.service.inter.FileService;
import personal.zhou.travelshare.util.MethodResourceDesc;
import personal.zhou.travelshare.util.Result;
import personal.zhou.travelshare.util.ResultCode;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

@Controller
public class FileController {
    @Autowired
    FileService fileService;

    @Autowired
    private BaseStorageService baseStorageService;

    @Autowired
    private StorageService storageService;

    @MethodResourceDesc(name = "头像上传")
    @ResponseBody
    @RequestMapping(value = "/trip/upload_head.do")
    public Result uploadFile(HttpServletRequest request) {
        Result result = new Result();
        result.setCode(ResultCode.FAILURE);
        result.setMessage(null);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator iter = multipartRequest.getFileNames();
        InputStream in = null;
        try {
            while (iter.hasNext()) {
                String param = (String) iter.next();
                MultipartFile file = multipartRequest.getFile(param);
                String originalFilename = file.getOriginalFilename();
                StorageEntity storageEntity = baseStorageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);
                result.setCode(ResultCode.SUCCESS);
                result.setMessage("上传成功");
                result.setObject(storageEntity.getUrl());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return result;
    }

    @MethodResourceDesc(name = "blog图像上传")
    @ResponseBody
    @RequestMapping(value = "/trip/uploadBlogPhoto.do")
    public Result uploadBlogPhoto(HttpServletRequest request) {
        Result result = new Result();
        result.setCode(ResultCode.FAILURE);
        result.setMessage(null);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator iter = multipartRequest.getFileNames();
        InputStream in = null;
        try {
            while (iter.hasNext()) {
                String param = (String) iter.next();
                CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(param);
                in = file.getFileItem().getInputStream();
                String oriFileName = file.getOriginalFilename();
                result = fileService.uploadFile(oriFileName, in, file.getSize(), "/blog");
                in.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return result;
    }

    @MethodResourceDesc(name = "查看图片文件")
    @RequestMapping(value = "/trip/imgFileView.do")
    @ResponseBody
    public  ResponseEntity<Resource> imgFileView(HttpServletRequest request, HttpServletResponse response, String fileName) {
//        String fileName = "http://localhost:8080/trip/imgFileView.do?fileName=http://127.0.0.1:8077/storage/fetch/uoz0lzdh7t6qgo6ic5hh.jpg";
        int i = fileName.lastIndexOf("fetch/");

        if (-1 == i) {
            ServletOutputStream stream = null;
            BufferedInputStream fif = null;
            try {
                String realPath = request.getServletContext().getRealPath(fileName);
                File file = new File(realPath);
                if (!file.exists()) {
                    return null;
                }
                stream = response.getOutputStream();
                response.reset();
                response.setContentType("image/jpeg");
                response.setHeader("Content-Length", String.valueOf(file.length()));
                fif = new BufferedInputStream(new FileInputStream(file));
                int d;
                byte[] buf = new byte[10240];
                while ((d = fif.read(buf)) != -1) {
                    stream.write(buf, 0, d);
                }
                stream.flush();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    if (stream != null) {
                        stream.close();
                    }
                    if (fif != null) {
                        fif.close();
                    }

                } catch (Exception e11) {
                }
            }
            return null;
        }

        String key = fileName.substring(i).substring("fetch/".length());
        StorageEntity StorageEntity = storageService.findByKey(key);
        if (key == null) {
            return ResponseEntity.notFound().build();
        }
        if (key.contains("../")) {
            return ResponseEntity.badRequest().build();
        }
        String type = StorageEntity.getType();
        MediaType mediaType = MediaType.parseMediaType(type);

        Resource file = baseStorageService.loadAsResource(key);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(mediaType).body(file);
    }

    public static void main(String[] args) {
        String fileName = "http://localhost:8080/trip/imgFileView.do?fileNamedh7t6qgo6ic5hh.jpg";
        int i = fileName.lastIndexOf("fetch/");
        System.out.println(i);
    }

}
