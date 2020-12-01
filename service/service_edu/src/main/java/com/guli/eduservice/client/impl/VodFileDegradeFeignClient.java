package com.guli.eduservice.client.impl;

import com.guli.commonutils.R;
import com.guli.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tjy
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    //出错之后执行的方法
    @Override
    public R removeAliyVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除视频出错了");
    }
}
