package com.sccit.invectrl.controllers;

import com.sccit.invectrl.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    //将Service注入Web层
    @Autowired
    AppUserService userService;
/*
    @RequestMapping("/login")
    public String show(){
        return "login";
    }

    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
    public String login(String name,String password){
        AppUserBean userBean = userService.loginIn(name,password);
        if(userBean!=null){
            return "success";
        }else {
            return "error";
        }
    }*/
}