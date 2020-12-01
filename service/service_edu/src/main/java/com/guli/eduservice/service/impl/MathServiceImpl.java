package com.guli.eduservice.service.impl;

import com.guli.eduservice.service.EduTeacherService;
import com.guli.eduservice.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tjy
 */
@Service
public class MathServiceImpl  implements MathService {


    @Autowired
    private EduTeacherService eduTeacherServicel;
    @Override
    public Double harmonicMean(Double[] V) {
        double sum=0.0;
        int l=V.length;
        for (int i=0;i<V.length;i++){
            sum+=(1/V[i]);
        }
        if (sum>0){
            return l/sum;
        }
        else {
            return 0.0;
        }
    }

    @Override
    public Double geometricMean(Double[] V) {
        double product=1.0;
        double l = V.length;
        for (int i=0;i<V.length;i++){
            product*=V[i];
        }
        Double v =Math.pow(product,1/l);
        return v;
    }

    @Override
    public Double min(Double[] V) {
        int l=V.length;
        for(int i=0;i<l-1;i++){
            if (V[i]>V[i+1]){
                Double tem=V[i];
                V[i]=V[i+1];
                V[i+1]=tem;
            }
        }
        return V[0];
    }

    @Override
    public Double max(Double[] V) {
        int l=V.length;
        for(int i=0;i<l-1;i++){
            if (V[i]>V[i+1]){
                Double tem=V[i];
                V[i]=V[i+1];
                V[i+1]=tem;
            }
        }
        return V[l-1];
    }

    @Override
    public Double[] range(Double[] V) {
        int l=V.length;
        if (l<=2){
            return V;
        }
        for(int i=0;i<l-1;i++){
            if (V[i]>V[i+1]){
                Double tem=V[i];
                V[i]=V[i+1];
                V[i+1]=tem;
            }
        }
        Double v[]={V[0],V[l-1]};
        return v;
    }

    @Override
    public Double variance(Double[] V) {
        int l=V.length;
        double avg=eduTeacherServicel.avg(V);
        double v = 0.0;
        for (int i =0;i<l;i++){
            v += (Math.pow(V[i]-avg,2));
        }
        double s = v/l;
        return s;
    }

    @Override
    public Double correctVariance(Double[] V) {
        double s =variance(V);
        double v = s/(V.length-1);
        return  v;

    }

    @Override
    public Double standardDeviation(Double[] V) {
        Double s = variance(V);
        double v = Math.pow(s,0.5);
        return v;
    }

    @Override
    public Double correctioStandardDeviation(Double[] V) {
        Double s = correctVariance(V);
        double v = Math.pow(s,0.5);
        return v;
    }

    @Override
    public Double standardError(Double[] V) {
        double s = standardDeviation(V);
        Integer l = V.length;
        double m = Math.pow(l,0.5);
        double v = s/m;
        return v;
    }
    @Override
    public Double coefficientVariation(Double[] V) {
        double v=	standardDeviation(V)/eduTeacherServicel.avg(V);
        return v;
    }

    @Override
    public Double averageDeviation(Double[] V) {
        double s =0.0;
        for (int i =0;i<V.length;i++){
            s +=Math.abs(V[i]-eduTeacherServicel.avg(V)) ;
        }
        double v = s/(V.length);
        return  v;
    }

    @Override
    public Double medianDeviation(Double[] V) {
        Double s[] = new Double[V.length];
        Double m = eduTeacherServicel.median(V);
        for (int i =0 ;i < V.length;i++){
            s[i]=Math.abs(V[i]-m);
        }
        Double v = eduTeacherServicel.median(s);
        return v;
    }

    @Override
    public Double skewness(Double[] V) {
        double mean = eduTeacherServicel.avg(V);
        double median = eduTeacherServicel.median(V);
        double SD = standardDeviation(V);
        return 3 * (mean-median)/SD;

    }



}
