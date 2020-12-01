package com.guli.oss.controller;

import com.guli.commonutils.R;
import com.guli.oss.service.OssService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tjy
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin(origins = "*")
public class OssController {


    @Autowired
    private OssService ossService;

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public  R uploadFile(@ApiParam(name = "file", value = "文件", required = true)
                             @RequestParam("file") MultipartFile file){

   String url = ossService.uploadFileAvator(file);

        return R.ok().data("url",url);
    }
}
