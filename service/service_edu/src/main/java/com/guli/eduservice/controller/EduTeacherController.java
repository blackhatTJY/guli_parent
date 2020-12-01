package com.guli.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.commonutils.R;
import com.guli.eduservice.entity.EduTeacher;
import com.guli.eduservice.entity.vo.TeacherQuery;
import com.guli.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-12
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/eduteacher")
@CrossOrigin(origins = "*")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询讲师表所有数据

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){

        List<EduTeacher> list = eduTeacherService.list(null);

        return R.ok().data("items",list) ;
    }

    //逻辑删除

   @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师Id",required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
         boolean result=false;
         if (flag){
             return R.ok();
         }
       else {
           return  R.error();
         }
    }

    //分页查询
    //current 当前页
    //limit 每页记录数
    @ApiOperation(value = "分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    @CrossOrigin
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){


        Page<EduTeacher>  pageTeacher = new Page<>(current,limit);

        eduTeacherService.page(pageTeacher,null);

        //总记录数
        long total = pageTeacher.getTotal();
        //数据list集合
        List<EduTeacher> records = pageTeacher.getRecords();
        return  R.ok().data("total",total).data("rows",records);
    }



    //条件分页查询
    @ApiOperation(value = "条件分页查询")
    @PostMapping ("/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false)  TeacherQuery teacherQuery){

        Page<EduTeacher> pageTeacher =  new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){

            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){

            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(end)){

          wrapper.le("gmt_create",end);
        }
        if (!StringUtils.isEmpty(begin)){

            wrapper.ge("gmt_create",begin);
        }
        wrapper.orderByDesc("gmt_create");
        eduTeacherService.page(pageTeacher,wrapper);
        long total = pageTeacher.getTotal();
        //数据list集合
        List<EduTeacher> records = pageTeacher.getRecords();
        return  R.ok().data("total",total).data("rows",records);
    }

     @PostMapping("addTeacher")
    public  R addTeacher(@RequestBody EduTeacher eduTeacher){

        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return  R.ok();
        }
        else {
            return R.ok();
        }
     }

     @GetMapping("getTeacher/{id}")
    public  R getTeacher(@PathVariable String id){
         EduTeacher byId = eduTeacherService.getById(id);
         return R.ok().data("teacher",byId);
     }

     @PostMapping("updateTeacher")
    public  R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag){
            return R.ok();
        }
        else {
            return R.error();
        }
     }

}

