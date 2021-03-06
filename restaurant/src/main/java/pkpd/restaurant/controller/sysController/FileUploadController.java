package pkpd.restaurant.controller.sysController;

import pkpd.restaurant.Enums.ResultEnum;
import pkpd.restaurant.entity.Result;
import pkpd.restaurant.entity.ResultUrl;
import pkpd.restaurant.utils.ImageUtil;
import pkpd.restaurant.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.annotation.MultipartConfig;
import java.io.File;

@Controller
@MultipartConfig
public class FileUploadController {
    private final static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    //
    @Value("${web.upload-image-path}")
    private String location;

    @PostMapping("/upload/img.do")
    @ResponseBody
    public Result upload(@RequestParam("image") MultipartFile multipartFile)throws Exception{
        if(multipartFile!=null&&!multipartFile.isEmpty()){
            //
            String rootFileName = multipartFile.getOriginalFilename();
            String contentType = multipartFile.getContentType();
            //
            logger.info("upload picture:name={},type={}",rootFileName,contentType);
            //
            String filePath = location+File.separator+"image";
            logger.info("picture saving path：={}",filePath);
            //，UUID
            String fileName = ImageUtil.saveImage(multipartFile,filePath);
            ResultUrl url = new ResultUrl();
            //
            String returnPath = "/image/"+fileName;
            url.setSrc(returnPath);
            return ResultUtil.success(url);
        }else{
            return ResultUtil.error(ResultEnum.IMAGE_UPLOAD_FAIL);
        }
    }
}
