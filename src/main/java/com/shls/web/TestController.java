package com.shls.web;

import com.shls.ResponseBuilder;
import com.shls.db.po.Client;
import com.shls.db.service.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController
{
    @Resource
    ClientService clientService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test()
    {
        Client client = clientService.findByName("珠海格力电器股份有限公司");

        return new ResponseBuilder().success().message("ok").data(client).build();
    }
}
