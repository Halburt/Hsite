package com.thinkgem.jeesite.common.swagger;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Api(value = "user", description = "用户相关接口")
public class User2Controller {
  
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "登录", notes = "用户登录", httpMethod = "POST", response = Object.class)
    public Object login(@ApiParam(value = "用户名", required = true) @RequestParam String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "200");  
        return map;  
    }  
  
    @RequestMapping(value = "/update", method = RequestMethod.POST)  
    @ResponseBody  
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "POST", response = Object.class)  
    public Object update(@ApiParam(value = "用户名", required = true) @RequestParam String username) {  
        Map<String, Object> map = new HashMap<>();
        map.put("status", "200");  
        return map;  
    }  
} 