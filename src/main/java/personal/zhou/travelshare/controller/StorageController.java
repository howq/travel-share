package personal.zhou.travelshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import personal.zhou.travelshare.common.storage.BaseStorageService;
import personal.zhou.travelshare.domain.entity.StorageEntity;
import personal.zhou.travelshare.service.StorageService;
import personal.zhou.travelshare.util.ResponseUtil;

import java.io.IOException;

/**
 * 对象存储服务
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private BaseStorageService baseStorageService;

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        StorageEntity StorageEntity = baseStorageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);
        return ResponseUtil.ok(StorageEntity);
    }

    /**
     * 访问存储对象
     *
     * @param key 存储对象key
     * @return
     */
    @GetMapping("/fetch/{key:.+}")
    public ResponseEntity<Resource> fetch(@PathVariable String key) {
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

    /**
     * 访问存储对象
     *
     * @param key 存储对象key
     * @return
     */
    @GetMapping("/download/{key:.+}")
    public ResponseEntity<Resource> download(@PathVariable String key) {
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
        return ResponseEntity.ok().contentType(mediaType).header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
