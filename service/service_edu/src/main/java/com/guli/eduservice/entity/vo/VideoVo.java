package com.guli.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author tjy
 */

@ApiModel(value = "小节封装类",description = "小节封装类")
@Data
public class VideoVo {

    private String id;

    private String title;

}
