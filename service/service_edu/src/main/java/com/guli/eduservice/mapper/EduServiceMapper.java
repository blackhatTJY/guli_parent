package com.guli.eduservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.eduservice.entity.EduSubject;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-02-29
 */
@Mapper
public interface EduServiceMapper extends BaseMapper<EduSubject> {

}

