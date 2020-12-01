package com.guli.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.educms.entity.CrmBanner;
import com.guli.educms.mapper.CrmBannerMapper;
import com.guli.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-04
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Cacheable(key = "'selectIndexList'",value = "banner")
    @Override
    public List<CrmBanner> selectBanner() {

        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();

        wrapper.orderByDesc("id");
        wrapper.last("limit 2");
        List<CrmBanner> list =  baseMapper.selectList(null);
        return list;
    }
}
