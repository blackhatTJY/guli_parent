package com.guli.educms.controller;

import com.guli.commonutils.R;
import com.guli.educms.entity.CrmBanner;
import com.guli.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tjy
 */
@Api(description = "后台轮播图接口")
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin(origins = "*")
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "查询所有banner")
    @GetMapping("/getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.selectBanner();
        return R.ok().data("list",list);
    }
}
