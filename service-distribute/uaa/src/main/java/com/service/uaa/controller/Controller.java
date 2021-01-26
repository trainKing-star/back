package com.service.uaa.controller;

import com.service.uaa.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    UserMapper userMapperImpl;

    @RequestMapping("/login-success")
    public String loginSuccess() {

        //userMapperImpl.insetUser("lisi","456");
        System.out.println(userMapperImpl.getUserByName("zhangsan"));


        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object prinicipal = authentication.getPrincipal();
        if (prinicipal == null) username = "匿名";
        if (prinicipal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) prinicipal;
            username = userDetails.getUsername();
        } else username = prinicipal.toString();

        return "登录成功" + username;
    }

}
