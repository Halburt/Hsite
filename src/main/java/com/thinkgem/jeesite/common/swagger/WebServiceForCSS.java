package com.thinkgem.jeesite.common.swagger;

import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebServiceForCSS {

    @ResponseBody
    @RequestMapping(value = "/soa/a/getUserById", method = RequestMethod.GET, produces = {"application/json; charset=utf-8","application/xml"})
    @ApiOperation(value = "通过ID查询USER信息", httpMethod = "GET", notes = "暂无")
    public String getUserById(
            @ApiParam(required = true, name = "id", value = "ID")
            @RequestParam(value = "id") String id, HttpServletRequest request) {
        User user = new User();
        user.setId(id);
        user.setName("测试人员");
        user.setAge(25);

        return JSONObject.toJSONString(user);
    }
}