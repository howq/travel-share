package personal.zhou.travelshare.service.inter;


import personal.zhou.travelshare.util.Result;

import java.io.InputStream;


public interface FileService {
    public Result uploadFile(String oriFileName, InputStream in, long size, String uploadFilePath);

}
