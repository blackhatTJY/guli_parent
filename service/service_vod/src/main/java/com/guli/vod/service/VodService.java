package com.guli.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author tjy
 */

public interface VodService {
    public String uploadAlyiVideo(MultipartFile file) ;

    void removeMoreAlyVideo(List videoIdList);
}
