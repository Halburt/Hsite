package com.thinkgem.jeesite.common.swagger;

import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value="V1")
@Controller
@RequestMapping(value="/user")
public class User1Controller {
  
    @RequestMapping(value = "/getUser")
    @ResponseBody
    @ApiOperation(value="根据ID获取用户信息",httpMethod="GET",notes="get user by id",response=JSONObject.class)
    public JSONObject getUser(@ApiParam(required=true,value="用户ID",name="userId")@RequestParam(value="userId")Integer userId) {
        JSONObject json = new JSONObject();
        json.put("id",userId);
        return json;
    }  
      
} 