package com.guli.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.fill.AnalysisCell;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.eduservice.entity.EduSubject;
import com.guli.eduservice.entity.excel.SubjectData;
import com.guli.eduservice.service.EduSubjectService;
import com.guli.service_base.exceptionhandle.GuliException;

import java.util.Map;

/**
 * @author tjy
 */

public class SubjectListener extends AnalysisEventListener<SubjectData> {


    public EduSubjectService eduSubjectService;


    public SubjectListener() {
    }

    public SubjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService =eduSubjectService ;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null) {
            throw new GuliException(20001,"文件数据为空");
        }
        //添加一级分类
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService,subjectData.getOneSujectName());
        if(existOneSubject == null) {//没有相同以及分类名称
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(subjectData.getOneSujectName());//一级分类名称
            existOneSubject.setParentId("0");
            eduSubjectService.save(existOneSubject);
        }

        //获取一级分类id值
        String pid = existOneSubject.getId();

        //添加二级分类
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService,subjectData.getTwoSujectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(subjectData.getTwoSujectName());//二级分类
            existTwoSubject.setParentId(pid);
            eduSubjectService.save(existTwoSubject);
        }
    }

    //判断一级分类是否重复
    private EduSubject existOneSubject(EduSubjectService subjectService,String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject OneSubject = subjectService.getOne(wrapper);
        return OneSubject;
    }

    //判断二级分类是否重复
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject TwoSubject = subjectService.getOne(wrapper);
        return TwoSubject;
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {}
}
