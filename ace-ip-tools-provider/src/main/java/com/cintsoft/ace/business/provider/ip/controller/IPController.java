package com.cintsoft.ace.business.provider.ip.controller;

import com.cintsoft.ace.business.provider.ip.service.IPService;
import com.cintsoft.ace.business.provider.ip.view.IPInfo;
import com.cintsoft.common.web.ErrorCodeInfo;
import com.cintsoft.common.web.ResultBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "/ip", tags = "IP信息")
@RestController
@RequestMapping("/ip")
public class IPController {

    @Resource
    private IPService ipService;

    @GetMapping("/getInfo")
    public ResultBean<IPInfo> getInfo(String ip, String state) {
        return ResultBean.restResult(ipService.getInfo(ip, state), ErrorCodeInfo.OK);
    }
}
