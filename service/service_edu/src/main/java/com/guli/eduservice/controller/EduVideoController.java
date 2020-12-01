package com.guli.eduservice.controller;


import com.alibaba.excel.util.StringUtils;
import com.guli.commonutils.R;
import com.guli.eduservice.client.VodClient;
import com.guli.eduservice.entity.EduVideo;
import com.guli.eduservice.service.EduVideoService;
import com.guli.service_base.exceptionhandle.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-27
 */
@Api(description = "小节")
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin(origins = "*")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;


    @Autowired
    private VodClient vodClient;

    @ApiOperation(value = "添加小节")
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    @ApiOperation(value = "根据小节id查询")
    @GetMapping("getVideoInfo/{id}")
    public R getVideoInfo(@PathVariable String id) {
        EduVideo eduVideo = videoService.getById(id);
        return R.ok().data("eduVideo", eduVideo);
    }

    @ApiOperation(value = "修改小节")
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return R.ok();
    }

    //TODO 后面这个方法需要完善：删除小节时候，同时要把里面的视频删除
    @ApiOperation(value = "删除小节")
    @DeleteMapping("deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id) {


        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)) {

            R result = vodClient.removeAliyVideo(videoSourceId);
            if (result.getCode()==20001){
                throw new GuliException(20001,"删除视频失败，熔断器");
            }
            //根据视频id，远程调用实现视频删除

        }

        videoService.removeById(id);
        return R.ok();
    }
}
