package com.guli.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author tjy
 */

public interface OssService {

    String uploadFileAvator(MultipartFile file);
}
