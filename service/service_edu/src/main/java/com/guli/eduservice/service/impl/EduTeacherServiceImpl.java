package com.guli.eduservice.service.impl;

import com.guli.eduservice.entity.EduTeacher;
import com.guli.eduservice.mapper.EduTeacherMapper;
import com.guli.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-12
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    @Override
    public Double[] transformation(String str) {
        String regex = ",|，|\\s+";
        String S[]=str.split(regex);
        Double V[] = new Double[S.length];
        for (int i = 0; i < S.length; i++) {
            V[i] = Double.parseDouble(S[i]);
        }
        return V ;
    }

    @Override
    public BigInteger sum(BigInteger[] V) {
        BigInteger s = BigInteger.ZERO ;
        for(BigInteger v : V) {
            s = s.add(v);
        }
        return s;
    }

    @Override
    public Double sum(Double[] V) {
        Double s = 0.0;
        for(Double v : V) {
            s = s+v;
        }
        return s;
    }



    @Override
    public Double avg(Double[] V) {
        Integer l=V.length;
        Double sum= sum(V);
        Double avg=sum/l;
        return  avg;
    }

    @Override
    public Double median(Double[] V) {
        Integer l=V.length;
        for(int i=0;i<l-1;i++){
            if (V[i]>V[i+1]){
                Double tem=V[i];
                V[i]=V[i+1];
                V[i+1]=tem;
            }
        }
        if (l %2==1){
            return V[(l-1)/2];
        }
        else {
            return (V[l/2-1]+V[l/2])/2;
        }
    }


}
