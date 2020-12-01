package com.guli.vodtest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;

import java.util.List;

/**
 * @author tjy
 */

public class TestVod {

    public static void main(String[] args) throws ClientException {

        //根据视频 id获取视频凭证
        DefaultAcsClient client = InitObject.initVodClient("LTAI4GBDPyBTP5q3SDmgqWes","fLN3iiwR95QKBfbIrdP0RYAxflTuSh");
      GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        request.setVideoId("328dbf406dd444f289678db1d0bbd501");

        response = client.getAcsResponse(request);
        System.out.println("playauth:"+response.getPlayAuth());

    }


    //根据视频id获取视频播放地址
    public static void getPlayUrl() throws Exception{
        DefaultAcsClient client = InitObject.initVodClient("LTAI4GBDPyBTP5q3SDmgqWes","fLN3iiwR95QKBfbIrdP0RYAxflTuSh");

        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        request.setVideoId("328dbf406dd444f289678db1d0bbd501");

        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");

    }
}
