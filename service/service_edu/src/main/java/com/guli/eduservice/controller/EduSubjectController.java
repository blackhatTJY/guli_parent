package com.guli.eduservice.controller;

import com.guli.commonutils.R;
import com.guli.eduservice.entity.OneSubject;
import com.guli.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author tjy
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin(origins = "*")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public  R addSubject(MultipartFile file){

        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }


    @ApiOperation(value = "课程分类列表")
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        //list集合泛型是一级分类
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }
}
