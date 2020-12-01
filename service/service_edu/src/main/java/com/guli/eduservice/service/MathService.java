package com.guli.eduservice.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author tjy
 */
@Service
public interface MathService {

    public Double harmonicMean(Double[] v);

    public Double geometricMean(Double[] v);

    public Double min(Double[] v);

    public Double max(Double[] v);

    public  Double[] range(Double[] v);

    public  Double variance(Double[] v);

    public  Double correctVariance(Double[] v);

    public Double standardDeviation(Double[] v);


    public  Double correctioStandardDeviation(Double[] v);

    public  Double standardError(Double[] v);

    public  Double coefficientVariation(Double[] v);

    public  Double averageDeviation(Double[] v);

    public  Double medianDeviation(Double[] v);

    public  Double skewness(Double[] v);
}
