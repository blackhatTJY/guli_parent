package com.guli.eduservice.service;

import com.guli.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigInteger;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-10-12
 */
public interface EduTeacherService extends IService<EduTeacher> {

    public Double[] transformation(String V);


    public BigInteger sum(BigInteger[] v);


    public Double sum(Double[] v);


    public Double avg(Double[] v);

    public Double median(Double[] v);

}
