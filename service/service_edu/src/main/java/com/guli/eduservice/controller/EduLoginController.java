package com.guli.eduservice.controller;

import com.guli.commonutils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author tjy
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {


    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public  R info(){
        return  R.ok().data("roles","[admin]").data("name","admin").data("avatar"," https://s.meet99.cn/newimg/loading.gif");
    }
}
