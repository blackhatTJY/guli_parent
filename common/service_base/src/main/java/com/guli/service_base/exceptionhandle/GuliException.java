package com.guli.service_base.exceptionhandle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tjy
 */
@Data
@AllArgsConstructor //有参数构造
@NoArgsConstructor //无参构造
public class GuliException extends RuntimeException {

    private Integer code;

    private String msg;
}
