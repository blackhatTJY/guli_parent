package com.guli.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.commonutils.R;
import com.guli.educms.entity.CrmBanner;
import com.guli.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {



        @Autowired
        private CrmBannerService bannerService;

        @ApiOperation(value = "获取Banner分页列表")
        @GetMapping("pageBanner/{page}/{limit}")
        public R pageBanner(@PathVariable long page, @PathVariable long limit) {
            Page<CrmBanner> pageBanner = new Page<>(page,limit);
            bannerService.page(pageBanner,null);
            return R.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
        }

        @ApiOperation(value = "添加Banner")
        @PostMapping("addBanner")
        public R addBanner(@RequestBody CrmBanner crmBanner) {
            bannerService.save(crmBanner);
            return R.ok();
        }

        @ApiOperation(value = "获取banner")
        @GetMapping("get/{id}")
        public R get(@PathVariable String id) {
            CrmBanner banner = bannerService.getById(id);
            return R.ok().data("item",banner);
        }

        @ApiOperation(value = "更新Banner")
        @PutMapping("updateBanner")
        public R updateBanner(@RequestBody CrmBanner crmBanner) {
            bannerService.updateById(crmBanner);
            return R.ok();
        }

        @ApiOperation(value = "删除Banner")
        @DeleteMapping("removeBanner/{id}")
        public R removeBanner(@PathVariable String id) {
            bannerService.removeById(id);
            return R.ok();
        }

    }



