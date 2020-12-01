package com.guli.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.eduservice.entity.EduSubject;
import com.guli.eduservice.entity.OneSubject;
import com.guli.eduservice.entity.TwoSubject;
import com.guli.eduservice.entity.excel.SubjectData;
import com.guli.eduservice.listener.SubjectListener;
import com.guli.eduservice.mapper.EduServiceMapper;
import com.guli.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tjy
 */
@Service
public class EduSubjectServiceImpl  extends ServiceImpl<EduServiceMapper, EduSubject> implements EduSubjectService  {
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
         try{

             InputStream in = file.getInputStream();
             EasyExcel.read(in, SubjectData.class,new SubjectListener(eduSubjectService)).sheet().doRead();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1 查询所有一级分类   parent_id = 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id",0);
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //2 查询所有二级分类   parent_id != 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id",0);
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //3 封装一级分类
        //查询出来所有一级分类list集合集合，得到每一个一级分类对象，回去每一个一级分类对象值，
        //封装到要求的list集合里面  List<OneSubject> findSubjectList
        for (int i = 0; i < oneSubjectList.size(); i++) {//遍历oneSubjectList集合
            //得到oneSubjectList每个eduSubject对象
            EduSubject eduSubject = oneSubjectList.get(i);

            //把eduSubject里面值获取出来，放到OneSubject对象里面
            //多个OneSubject放到findSubjectList里面
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());

            //把eduSubject里面获取出来的值，放到oneSubject对象里面
            BeanUtils.copyProperties(eduSubject,oneSubject);
            //多个OneSubject放到findSubjectList里面
            finalSubjectList.add(oneSubject);

            //在一级分类循环遍历查询所有的二级分类
            //创建list集合封装每一个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            //遍历二级分类list集合
            for (int m = 0; m < twoSubjectList.size(); m++) {
                //获取每个二级分类list集合
                EduSubject tSubject = twoSubjectList.get(m);
                //判断二级分类parentid和一级分类id是否一样
                if(tSubject.getParentId().equals(eduSubject.getId())) {
                    //把tSubject值复制到TwoSubject里面，放到twoFinalSubjectList里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            //把一级下面所有二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);
        }

        return finalSubjectList;
    }
}
