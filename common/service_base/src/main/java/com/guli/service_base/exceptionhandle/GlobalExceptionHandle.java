package com.guli.service_base.exceptionhandle;



import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.guli.commonutils.R;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tjy
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {


    //全局异常

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");

    }

       //特定异常

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody

    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了特定异常处理");

    }

    //自定义异常

    @ExceptionHandler(GuliException.class)
    @ResponseBody

    public R error(GuliException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().code(e.getCode()).message(e.getMsg());

    }


}
