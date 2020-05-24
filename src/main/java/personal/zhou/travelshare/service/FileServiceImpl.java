package personal.zhou.travelshare.service;

import org.springframework.stereotype.Service;
import personal.zhou.travelshare.service.inter.FileService;
import personal.zhou.travelshare.util.FileUtil;
import personal.zhou.travelshare.util.Result;
import personal.zhou.travelshare.util.ResultCode;
import personal.zhou.travelshare.util.TimeUtil;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl extends BaseService implements FileService {

    @Override
    public Result uploadFile(String oriFileName, InputStream in, long size,
                             String uploadFilePath) {
        Result result = new Result();
        result.setCode(ResultCode.FAILURE);

        Date curDate = new Date();
        String year = TimeUtil.getYear(curDate);
        String month = TimeUtil.getMonth(curDate);
        String day = TimeUtil.getDay(curDate);

        //保存相对路径地址
        String filePath = year + File.separator + month + File.separator + day;
        FileUtil.mkdirs(uploadFilePath + File.separator + filePath);

        String fileName = UUID.randomUUID().toString() + FileUtil.getFilePrex(oriFileName);
        try {
            FileUtil.copyFile(in, uploadFilePath + File.separator + filePath, fileName);
        } catch (Exception e) {

            e.printStackTrace();
            //logger.error("文件上传异常： ",e);
            result.setMessage("上传失败");
            result.setSuccess(false);
            return result;
        }
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("上传成功");
        result.setObject(uploadFilePath + File.separator + filePath + File.separator + fileName);
        System.out.println(result.getObject());
        result.setSuccess(true);
        return result;
    }

}


